package eapli.base.app.other.console.authz;

import eapli.base.course.domain.Course;
import eapli.base.exam.controller.ExamController;
import eapli.base.exam.domain.*;
import eapli.base.profile.domain.Profile;
import eapli.base.question.domain.Question;
import eapli.base.usermanagement.application.AddAutomaticFormativeExamController;
import eapli.framework.io.util.Console;
import java.util.AbstractList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddAutomaticFormativeExamUI extends AbstractList {

    private ExamController theController = new ExamController();

    protected boolean doShow() throws Exception {
        int sections, questions,difficulty=0;

        String path = Console.readLine("Insert the file path of the exam");

        Title title = new Title(Console.readLine("- Exam Title: "));
        System.out.println("- Course:");
        Course course = getCourse();

        sections = Console.readInteger("- Number of sections: ");

        Set<Question> questionSet = (Set<Question>) theController.getAllQuestions();
        Set<Section> sectionsSet = new HashSet<>();
        for (int i = 0; i < sections; i++) {
            questions = Console.readInteger(String.format("- Number of questions(Section %d): ", i+1));
            for (int j = 0; j < questions; j++) {
                Question question = questionSet.iterator().next();
                difficulty+=question.getDifficulty().getDifficulty();
            }
            TextualDescription des = new TextualDescription(Console.readLine("- Section Description: "));

            Section section = new Section(null,des,questionSet,new Difficulty(difficulty));
            difficulty=0;
            sectionsSet.add(section);
        }
        System.out.println("- Open Date: ");
        int day = Console.readInteger("- Day: ");
        int month = Console.readInteger("- Month: ");
        int year = Console.readInteger("- Year: ");
        int hour = Console.readInteger("- Hour: ");
        int minute = Console.readInteger("- Minute: ");
        OpenDate openDate = new OpenDate(day,month,year,hour,minute);
        System.out.println("- Close Date: ");
        day = Console.readInteger("- Day: ");
        month = Console.readInteger("- Month: ");
        year = Console.readInteger("- Year: ");
        hour = Console.readInteger("- Hour: ");
        minute = Console.readInteger("- Minute: ");
        CloseDate closeDate = new CloseDate(day,month,year,hour,minute);

        try{
            Exam exam = theController.addExam(title.toString(),course,sectionsSet,openDate,closeDate,path);
            theController.saveExam(exam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        System.out.println("Exam Created with Success!");
        return true;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    public Course getCourse(){
        List<Course> courses=theController.getCourseService();
        int option = -1;

        if(courses.isEmpty()){
            System.out.println("Does not exist any course created!");
            return null;
        }

        boolean isValidOption = false;
        do {
            try {
                for (int i = 0; i <  courses.size(); i++) {
                    System.out.printf("[%d] -> Id:%d Title:%s\n",i+1,courses.get(i).getId(),courses.get(i).getTitle());
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

        return courses.get(option-1);

    }

    @Override
    public int size() {
        return 0;
    }
}
