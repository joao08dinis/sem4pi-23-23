package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.course.domain.Course;
import eapli.base.course.domain.EnrollmentsState;
import eapli.base.course.domain.State;
import eapli.base.usermanagement.application.OpenCloseCourseEnrollmentsController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class OpenCloseCourseEnrollmentsUI extends AbstractUI {
    private final OpenCloseCourseEnrollmentsController theController = new OpenCloseCourseEnrollmentsController();
    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        System.out.println("Which course do you want to change the enrollments state?");
        List<Course> courses=theController.getAllCourses();
        int option = -1;

        if(courses.isEmpty()){
            System.out.println("Does not exist any course created!");
            return true;
        }

        boolean isValidOption = false;
        do {
            try {
                for (int i = 0; i <  courses.size(); i++) {
                    System.out.printf("[%d] -> Id:%d Title:%s Enrollments State:%s\n",i+1,courses.get(i).getId(),courses.get(i).getTitle(),courses.get(i).getEnrollmentsState());
                }
                option = Console.readInteger("Option: ");
                System.out.println();
                if (option>0 || option <courses.size()){
                    isValidOption=true;
                }else{
                    System.out.println("Invalid Option!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the course: " + e.getMessage());
            }
        }while (!isValidOption);

        Course course = courses.get(option-1);

        EnrollmentsState enrollmentsState = course.getEnrollmentsState();

        if (enrollmentsState.equals(EnrollmentsState.CLOSE)) {
            enrollmentsState = EnrollmentsState.OPEN;
        } else if (enrollmentsState.equals(EnrollmentsState.OPEN)) {
            enrollmentsState = EnrollmentsState.CLOSE;
        }

        try {
            this.theController.OpenCloseCourseEnrollments(course, enrollmentsState);
        } catch (Exception e) {
            throw new RuntimeException("Error changing the enrollments state of the course.");
        }

        System.out.printf("The enrollments state of the course %s is now %s\n",course.getTitle(),course.getEnrollmentsState());

        return true;
    }

    @Override
    public String headline() {
        return "Open/Close Course Enrollments";
    }
}
