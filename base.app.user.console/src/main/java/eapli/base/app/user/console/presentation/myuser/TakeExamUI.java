package eapli.base.app.user.console.presentation.myuser;

import eapli.base.course.domain.Course;
import eapli.base.exam.controller.ExamController;
import eapli.base.exam.domain.Exam;
import eapli.base.grade.controller.GradeController;
import eapli.base.grade.domain.Grade;
import eapli.base.grade.domain.TypeOfGrade;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.question.domain.ANTLRExam.ExamANTLRLexer;
import eapli.base.question.domain.ANTLRExam.ExamANTLRParser;
import eapli.base.question.domain.ExamVisitor;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TakeExamUI extends AbstractUI {

    private final ExamController examController = new ExamController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final GradeController gradeController = new GradeController();
    private final ProfileController profileController = new ProfileController();
    StudentProfile currentUserProfile = (StudentProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {

        int option;

        System.out.println("Choose the Course of the Exam you want to take:");
        Course course = getCourse();

        List<Exam> exams = examController.getExamsByCourse(course);

        if (exams.size() == 0){
            System.out.println("There are no exams for that course yet!");
        }else {
            System.out.println("Please choose one of the Exams:");
            for (int i = 0; i < exams.size(); i++) {
                System.out.printf("Exam %d. : %s\n" , i + 1, exams.get(i).getTitle().getTitle());
            }
            option = sc.nextInt();
            Exam exam = exams.get(option-1);

            try {
                String path = exam.getPath();
                ExamANTLRLexer lexer = new ExamANTLRLexer(CharStreams.fromFileName(path));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                ExamANTLRParser parser = new ExamANTLRParser(tokens);
                ParseTree tree = parser.prog(); // parse

                ExamVisitor eval = new ExamVisitor();
                eval.visit(tree);

                int auxGrade = eval.getGrade();
                TypeOfGrade typeOfGrade;
                if (auxGrade<10){
                    typeOfGrade = TypeOfGrade.DISAPPROVED;
                }else {
                    typeOfGrade = TypeOfGrade.APPROVED;
                }
                Grade grade = gradeController.addNewGrade(exam,auxGrade,typeOfGrade);
                profileController.addGrade(grade,currentUserProfile);
            } catch (IOException e){
                System.out.println(e.getMessage());
            } catch (BusinessRuleException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
            System.out.println("You have successfully taken your exam!");
        }
        return true;
    }

    public Course getCourse(){
        List<Course> courses = examController.getCourseService();
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
    public String headline() {
        return "Take and Exam";
    }
}
