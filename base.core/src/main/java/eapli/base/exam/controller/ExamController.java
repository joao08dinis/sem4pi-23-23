package eapli.base.exam.controller;

import eapli.base.course.domain.Course;
import eapli.base.course.service.CourseService;
import eapli.base.exam.domain.*;
import eapli.base.exam.service.ExamService;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.question.domain.*;
import eapli.base.question.domain.ANTLRExam.ExamANTLRLexer;
import eapli.base.question.domain.ANTLRExam.ExamANTLRParser;
import eapli.base.question.service.*;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Set;

@UseCaseController
public class ExamController {

    private final ExamService service = new ExamService();
    ProfileController profileController=new ProfileController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    CourseService courseService =new CourseService();
    private final QuestionService questionService = new QuestionService();

    private final ShortAnswerService shortAnswerService = new ShortAnswerService();
    private final MatchingService matchingService= new MatchingService();
    private final TrueOrFalseService trueOrFalseService=new TrueOrFalseService();
    private final MultipleChoiceService multipleChoiceService= new MultipleChoiceService();
    private final MissingWordsService missingWordsService= new MissingWordsService();
    private final NumericalService numericalService= new NumericalService();

    public static boolean isValid = true;

    public Exam addExam(String title, Course course, Set<Section> sections, OpenDate openDate, CloseDate closeDate, String path) throws Exception{
        TeacherProfile profile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

        return Exam.from(null,new Title(title),profile,null,course,sections,openDate,closeDate,path);
    }

    public boolean examValidation(String path) throws IOException {
        ParserRuleContext tree = null;

        try {
            CharStream charStream = CharStreams.fromFileName(path);
            ExamANTLRLexer lexer = new ExamANTLRLexer(charStream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new ANTLRErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) throws ParseCancellationException {
                    System.out.println("line " + i + ":" + i1 + " " + s);
                    isValid = false;
                }

                @Override
                public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
                    isValid = false;
                    return;
                }

                @Override
                public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
                    isValid = false;
                    return;
                }

                @Override
                public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
                    isValid = false;
                    return;
                }
            });
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExamANTLRParser parser = new ExamANTLRParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new ANTLRErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) throws ParseCancellationException {
                    isValid = false;
                    System.out.println("line " + i + ":" + i1 + " " + s);
                }

                @Override
                public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
                    isValid = false;
                    return;
                }

                @Override
                public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
                    isValid = false;
                    return;
                }

                @Override
                public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
                    isValid = false;
                    return;
                }
            });

            tree = parser.prog();
        } catch (ParseCancellationException e) {
            System.out.println("Questionnaire is not valid:\n" + e.getMessage());
        }

        return isValid;
    }

    public List<Exam> getExamsByCourse(Course course){
        List<Exam> allExams = getAll();
        List<Exam> examsByCourse = new ArrayList<>();
        for (Exam exam : allExams) {
            if(exam.getCourse().getId().equals(course.getId())){
                examsByCourse.add(exam);
            }
        }
        return examsByCourse;
    }

    public void saveExam(Exam exam){
        service.addNewExam(exam);
    }

    public List<Exam> getAll(){
        return service.getAll();
    }

    public List<Course> getCourseService() {
        return courseService.getAllCourses();
    }

    public Iterable<Question> getAllQuestions(){
        return questionService.getAll();
    }

    public List<ShortAnswer> getShortAnswerQuestions(){return shortAnswerService.getAll(); }

    public List<Matching> getMatchingQuestions(){return matchingService.getAll();}
    public List<TrueOrFalse> getTrueOrFalseQuestions(){return trueOrFalseService.getAll();}
    public List<MultipleChoice> getMultipleChoiceQuestions(){return multipleChoiceService.getAll();}
    public List<MissingWords> getMissingWordsQuestions(){return missingWordsService.getAll();}
    public List<Numerical> getNumericalQuestions(){return numericalService.getAll();}
}
