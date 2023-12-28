package eapli.base.app.other.console.authz;

import eapli.base.TeacherRegistration.controller.TeacherRegistrationController;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.course.domain.Course;
import eapli.base.exam.controller.ExamController;
import eapli.base.exam.domain.Exam;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ViewCoursesExamsUi extends AbstractUI {

    Scanner sc = new Scanner(System.in);

    ExamController examController = new ExamController();
    TeacherRegistrationController controller = new TeacherRegistrationController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    ProfileController profileController = new ProfileController();
    TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {
        List<TeacherRegistration> teacherRegistrationList = controller.getTeacherRegistrations();
        List<Course> availableCourseList = new ArrayList<>();
        for (TeacherRegistration regist : teacherRegistrationList){
            if (regist.getTeacherProfile().equals(currentUserProfile)){
                availableCourseList.add(regist.getCourse());
            }
        }

        System.out.println("Choose course to view exams: ");
        for (int i = 0; i < availableCourseList.size(); i++) {
            System.out.println(i+1 + ": " + availableCourseList.get(i));
            
        }

        int option = sc.nextInt();

        Course choosenCourse = availableCourseList.get(option-1);

        List<Exam> examSet = examController.getAll();

        List<Exam> choosenCourseExams = new ArrayList<>();

        for (Exam exam : examSet) {
            if (exam.getCourse().equals(choosenCourse)){
                choosenCourseExams.add(exam);
            }
        }

        System.out.println("The Course you chose has the following exams: ");

        for (Exam exam : choosenCourseExams) {
            System.out.println(exam);
        }


        return true;
    }

    @Override
    public String headline() {
        return "View Courses Exams";
    }
}
