package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.course.domain.Course;
import eapli.base.course.domain.State;
import eapli.base.usermanagement.application.OpenCloseCourseController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class OpenCloseCourseUI extends AbstractUI {
    private final OpenCloseCourseController theController = new OpenCloseCourseController();
    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        System.out.println("Which course do you want to change the state?");
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
                    System.out.printf("[%d] -> Id:%d Title:%s State:%s\n",i+1,courses.get(i).getId(),courses.get(i).getTitle(),courses.get(i).getState());
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

        State state = course.getState();

        if (state.equals(State.CLOSE)) {
            state = State.OPEN;
        } else if (state.equals(State.OPEN)) {
            state = State.CLOSE;
        }

        try {
            this.theController.openCloseCourse(course, state);
        } catch (Exception e) {
            throw new RuntimeException("Error changing the state of the course.");
        }

        System.out.printf("The state of the course %s is now %s\n",course.getTitle(),course.getState());

        return true;
    }


    @Override
    public String headline() {
        return "Open/Close Course";
    }
}
