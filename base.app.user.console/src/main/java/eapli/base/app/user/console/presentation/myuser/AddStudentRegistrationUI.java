package eapli.base.app.user.console.presentation.myuser;

import eapli.base.StudentRegistration.controller.StudentRegistrationController;
import eapli.base.StudentRegistration.domain.Status;
import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.TeacherRegistration.domain.Role;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.course.domain.Course;
import eapli.base.course.domain.EnrollmentsState;
import eapli.base.course.domain.State;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;
import java.util.Scanner;

public class AddStudentRegistrationUI extends AbstractUI {

    private final StudentRegistrationController controller = new StudentRegistrationController();

    private final ProfileController profileController = new ProfileController();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final StudentProfile currentUserProfile = (StudentProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {

        int option;

        StudentRegistration newStudentRegistration;

        List<Course> courseList = controller.getCourses();

        System.out.println("Choose the course you want to request enrollment:");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("Course %d. : %s\n",i + 1, courseList.get(i).toString());
        }
        option = sc.nextInt();
        Course course = courseList.get(option - 1);

        Status status;
        status = Status.PENDING;

        List<StudentRegistration> studentRegistrationsByCourse = controller.getStudentRegistrationsByCourse(course);

        try {
            if (course.getState().equals(State.CLOSE)) {
                System.out.println("It was not possible to request your enrollment, because the course is closed");
            } else if (course.getEnrollmentsState().equals(EnrollmentsState.CLOSE)) {
                System.out.println("It was not possible to request your enrollment, because the enrollments for this course are closed");
            } else if (course.getCapacity().getMaxCapacity() == studentRegistrationsByCourse.size()) {
                System.out.println("We are sorry, the course is full!");
            } else{
                newStudentRegistration = controller.addStudentRegistration(currentUserProfile, status, course);
                controller.saveStudentRegistration(newStudentRegistration);
                System.out.println("The course enrollment request you requested has been created!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error requesting enrollment in course.");
        }

        System.out.println();

        System.out.println("Student Enrollments:");
        studentRegistrationsByCourse = controller.getStudentRegistrationsByCourse(course);
        for (StudentRegistration studentRegistration: studentRegistrationsByCourse) {
            System.out.println(studentRegistration);
        }

        return true;


    }

    @Override
    public String headline() {
        return "Request Enrollment in Course";
    }
}

