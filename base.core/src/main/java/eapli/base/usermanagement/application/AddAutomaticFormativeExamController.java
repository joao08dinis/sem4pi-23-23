package eapli.base.usermanagement.application;

import eapli.base.course.domain.*;
import eapli.base.course.service.CourseService;
import eapli.base.exam.domain.*;
import eapli.base.exam.domain.Exam;
import eapli.base.exam.domain.OpenDate;
import eapli.base.exam.domain.Section;
import eapli.base.exam.domain.Title;
import eapli.base.exam.service.ExamService;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.question.domain.Question;
import eapli.base.question.service.QuestionService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
import java.util.Set;

public class AddAutomaticFormativeExamController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ExamService service = new ExamService();
    private final QuestionService questionService = new QuestionService();
    private final CourseService courseService = new CourseService();
    ProfileController profileController = new ProfileController();


    public Exam addAutoExam(final Title title, final TeacherProfile teacherProfile, final Set<StudentProfile> students, final Course course,
                            final Set<Section> sections, final OpenDate openDate, final CloseDate closeDate, String path) throws Exception {

        return Exam.from(null,title,teacherProfile, students, course, sections, openDate, closeDate,path);
    }

    public void saveExam(Exam exam){
        service.addNewExam(exam);
    }

    public Iterable<Question> getAllQuestions(){
        return questionService.getAll();
    }

    public List<Course> getAllCourses(){return courseService.getAllCourses();}

    public TeacherProfile getTeacher(){
        return (TeacherProfile)profileController.getUserProfile(authz.session().get().authenticatedUser()).get();
    }

}
