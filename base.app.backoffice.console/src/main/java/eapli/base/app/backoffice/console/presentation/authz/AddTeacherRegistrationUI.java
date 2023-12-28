package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.TeacherRegistration.controller.TeacherRegistrationController;
import eapli.base.TeacherRegistration.domain.Role;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.course.domain.Course;
import eapli.base.course.domain.EnrollmentsState;
import eapli.base.course.domain.State;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;
import java.util.Scanner;

public class AddTeacherRegistrationUI extends AbstractUI {

    private final TeacherRegistrationController controller = new TeacherRegistrationController();

    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {

        int option;

        TeacherRegistration newTeacherRegistration = null;

        List<Course> courseList = controller.getCourses();

        List<Profile> teacherList = controller.getTeachersProfiles();

        System.out.println("Choose the course you want to set teachers:");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("Course %d. : %s\n" , i + 1, courseList.get(i).toString());
        }
        option = sc.nextInt();
        Course course = courseList.get(option - 1);

        System.out.println("Choose the teacher you want to set:");
        for (int i = 0; i < teacherList.size(); i++) {
            System.out.printf("Teacher %d. : %s\n", i + 1, teacherList.get(i).toString());
        }
        option = sc.nextInt();
        TeacherProfile teacherProfile = (TeacherProfile) teacherList.get(option - 1);

        System.out.println("Choose the Role that the teacher will have:");
        System.out.println("1. REGENT");
        System.out.println("2. ASSISTANT");

        Role role=null;
        boolean isValidOption = false;

        do{
            try {
                option = Console.readInteger("Option: ");
                switch (option) {
                    case 1:
                        System.out.println("You selected REGENT.");
                        role = Role.REGENT;
                        isValidOption = true;
                        break;
                    case 2:
                        System.out.println("You selected ASSISTANT.");
                        role = Role.ASSISTANT;
                        isValidOption = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the state: " + e.getMessage());
            }
        } while(!isValidOption);

        try {
            if(course.getState().equals(State.CLOSE)) {
                System.out.println("It was not possible to register this teacher because the course is closed");
            }else if(course.getEnrollmentsState().equals(EnrollmentsState.CLOSE)) {
                System.out.println("It was not possible to register this teacher because the enrollments for this course are closed");
            }else if (controller.hasRegentInCourse(course) && role.equals(Role.REGENT)) {
                System.out.println("This Course already has a regent!");
            }else {
                newTeacherRegistration = controller.addTeacherRegistration(teacherProfile, role, course);
                controller.saveTeacherRegistration(newTeacherRegistration);
                System.out.println("The teacher registration you requested has been created!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating a teacher registration.");
        }

        System.out.println();
        System.out.println("Teacher Registrations in this Course:");
        System.out.println();
        List<TeacherRegistration> teacherRegistrationsList = controller.getTeacherRegistrationsByCourse(course);
        for (TeacherRegistration teacherRegistration: teacherRegistrationsList) {
            System.out.println(teacherRegistration);
        }

        return true;

    }

    @Override
    public String headline() {
        return "Set Teachers of a Course";
    }
}
