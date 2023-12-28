package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.course.domain.Course;
import eapli.base.course.domain.EnrollmentsState;
import eapli.base.course.domain.State;
import eapli.base.usermanagement.application.AddCourseController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Scanner;

public class AddCourseUI extends AbstractUI {

    private final AddCourseController theController = new AddCourseController();
    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        Course addCourse =null;

        final String title = Console.readLine("Title");
        final String capacity1 = Console.readLine("Capacity(Min)");
        final String capacity2 = Console.readLine("Capacity(Max)");


        EnrollmentsState enrollmentsState1 = null;
        State state1 = null;

        boolean isValidOption = false;
        int input;
        do {
            System.out.println("State");
            System.out.println("1. OPEN");
            System.out.println("2. CLOSE");

            try {
                input = Console.readInteger("Option: ");
                switch (input) {
                    case 1:
                        System.out.println("You selected OPEN.");
                        state1=State.OPEN;
                        isValidOption = true;
                        break;
                    case 2:
                        System.out.println("You selected CLOSE.");
                        state1=State.CLOSE;
                        isValidOption = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the state: " + e.getMessage());
            }
        } while (!isValidOption);


        isValidOption = false;
        do {
            System.out.println("Enrollments State");
            System.out.println("1. OPEN");
            System.out.println("2. CLOSE");

            try {
                input = Console.readInteger("Option: ");
                switch (input) {
                    case 1:
                        System.out.println("You selected OPEN.");
                        enrollmentsState1=EnrollmentsState.OPEN;
                        isValidOption = true;
                        break;
                    case 2:
                        System.out.println("You selected CLOSE.");
                        enrollmentsState1=EnrollmentsState.CLOSE;
                        isValidOption = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the enrollments state: " + e.getMessage());
            }
        } while (!isValidOption);


        try {
            addCourse = this.theController.addCourse(title, state1, Integer.parseInt(capacity1), Integer.parseInt(capacity2), enrollmentsState1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating a course.");
        }
        theController.saveCourse(addCourse);

        System.out.println("Course created!");

        return true;
    }

    @Override
    public String headline() {
        return "Add Course";
    }
}

