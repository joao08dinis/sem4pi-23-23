package eapli.base.app.user.console.presentation.myuser;
import eapli.base.StudentRegistration.controller.StudentRegistrationController;
import eapli.base.StudentRegistration.domain.Status;
import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.TeacherRegistration.controller.TeacherRegistrationController;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.classe.controller.ClasseController;
import eapli.base.course.domain.Course;
import eapli.base.exam.controller.ExamController;
import eapli.base.exam.domain.Exam;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class ListExamsUI extends AbstractUI {


    Scanner sc = new Scanner(System.in);

    ExamController examController = new ExamController();
    StudentRegistrationController controller = new StudentRegistrationController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    ProfileController profileController = new ProfileController();
    StudentProfile currentUserProfile = (StudentProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {
        List<StudentRegistration> studentRegistrationList = controller.getStudentRegistrations();
        List<Course> availableCourseList = new ArrayList<>();
        for (StudentRegistration regist : studentRegistrationList){
            if (regist.getStudentProfile().equals(currentUserProfile) && regist.getStatus().equals(Status.ACCEPT)){
                availableCourseList.add(regist.getCourse());
            }
        }

        List<Exam> examSet = examController.getAll();

        List<Exam> choosenCourseExams = new ArrayList<>();

        for (Exam exam : examSet) {
            if (availableCourseList.contains(exam.getCourse())){
                choosenCourseExams.add(exam);
            }
        }

        System.out.println("You have the following exams: ");

        for (Exam exam : choosenCourseExams) {
            System.out.println(exam);
        }


        return true;
    }
    @Override
    public String headline() {
        return "List Exams";
    }
}