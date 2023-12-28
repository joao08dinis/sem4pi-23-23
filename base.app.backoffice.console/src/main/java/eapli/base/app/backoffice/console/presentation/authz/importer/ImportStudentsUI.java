package eapli.base.app.backoffice.console.presentation.authz.importer;

import eapli.base.StudentRegistration.controller.StudentRegistrationController;
import eapli.base.StudentRegistration.domain.Status;
import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.course.domain.Course;
import eapli.base.course.domain.EnrollmentsState;
import eapli.base.course.domain.State;
import eapli.base.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.DateOfBirth;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TaxPayerNumber;
import eapli.base.usermanagement.application.AddUserController;
import eapli.base.usermanagement.application.ListUsersController;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ImportStudentsUI extends AbstractUI implements Importer{

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);
    private final AddUserController theController = new AddUserController();
    private final ProfileController profileController = new ProfileController();
    final ListUsersController listUserController = new ListUsersController();
    private final StudentRegistrationController controller = new StudentRegistrationController();
    Scanner sc = new Scanner(System.in);

    @Override
    public void importFile(String file) throws FileNotFoundException {

        Scanner fileScanner = new Scanner(new File(file));
        Status status;
        status = Status.ACCEPT;
        int option;
        StudentRegistration newStudentRegistration;

        List<Course> courseList = controller.getCourses();

        System.out.println("Choose the course you want to request enrollment:");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("Course %d. : %s\n",i + 1, courseList.get(i).toString());
        }

        option = sc.nextInt();
        Course course = courseList.get(option - 1);

        List<StudentRegistration> studentRegistrationsByCourse = controller.getStudentRegistrationsByCourse(course);

        try {
            if (course.getState().equals(State.CLOSE)) {
                System.out.println("It was not possible to request your enrollment, because the course is closed");
            } else if (course.getEnrollmentsState().equals(EnrollmentsState.CLOSE)) {
                System.out.println("It was not possible to request your enrollment, because the enrollments for this course are closed");
            } else if (course.getCapacity().getMaxCapacity() == studentRegistrationsByCourse.size()) {
                System.out.println("We are sorry, the course is full!");
            } else {
                int lineNumber = 1;
                fileScanner.nextLine(); //Ignorar cabeçalho
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] elements;
                    String username;
                    String password;
                    String firstName;
                    String lastName;
                    String email;
                    String number;
                    String mechanographic;
                    int day;
                    int month;
                    int year;
                    elements = line.split(",");
                    username = elements[0];
                    password = elements[1];
                    firstName = elements[2];
                    lastName = elements[3];
                    email = elements[4];
                    mechanographic = elements[5];
                    number = elements[6];
                    day = Integer.parseInt(elements[7]);
                    month = Integer.parseInt(elements[8]);
                    year = Integer.parseInt(elements[9]);

                    DateOfBirth dateOfBirth = DateOfBirth.from(day, month, year);
                    MecanographicNumber mecanographicNumber = new MecanographicNumber(mechanographic);
                    TaxPayerNumber taxPayerNumber = TaxPayerNumber.from(number);

                    try {
                        try {
                            final Set<Role> roles = new HashSet<>();
                            roles.add(BaseRoles.STUDENT);
                            SystemUser user = registerUser(username, password, firstName, lastName, email, roles);
                            StudentProfile profile = (StudentProfile) profileController.createStudentProfile(user,null, taxPayerNumber, dateOfBirth, mecanographicNumber);
                            newStudentRegistration = controller.addStudentRegistration(profile, status, course);
                            controller.saveStudentRegistration(newStudentRegistration);
                        }catch (IllegalArgumentException e){
                            e.printStackTrace();
                            System.out.println("An error has occurred while registering and enrolling a new student");
                        }

                    } catch (final IntegrityViolationException | ConcurrencyException e) {
                        System.out.println("That username is already in use.");
                        System.out.println(e.getMessage());
                    }


                    System.out.println("Student enrolled successfully!");

                }


                List<StudentRegistration> studentRegistrations = controller.getStudentRegistrationsByCourse(course);
                for (StudentRegistration studentRegistration : studentRegistrations) {
                    System.out.println(studentRegistration);
                }
                System.out.println("The course enrollments requests you requested have been created!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error requesting enrollment in course.");
        }


    }

    @Override
    protected boolean doShow() {

        System.out.println("Please specify the path of the file that has the students data: ");

        String filePath = sc.nextLine();

        try {
            importFile(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    protected SystemUser registerUser(final String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles) {
        SystemUser u = null;
        try {
            u = theController.addUser(username, password, firstName, lastName, email, roles);
            LOGGER.debug("»»» %s", username);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            u = listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
        }
        return u;
    }

    @Override
    public String headline() {
        return "Enroll Students using csv file";
    }
}
