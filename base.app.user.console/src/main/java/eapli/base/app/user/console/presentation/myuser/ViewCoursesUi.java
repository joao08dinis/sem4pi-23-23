package eapli.base.app.user.console.presentation.myuser;

import eapli.base.StudentRegistration.controller.StudentRegistrationController;
import eapli.base.StudentRegistration.domain.Status;
import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.course.domain.Course;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class ViewCoursesUi extends AbstractUI {

    StudentRegistrationController controller = new StudentRegistrationController();
    private final AuthorizationService authz =
            AuthzRegistry.authorizationService();
    ProfileController profileController = new ProfileController();
    StudentProfile currentUserProfile = (StudentProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {
        List<StudentRegistration> studentRegistrationsList = controller.getStudentRegistrations();
        List<Course> availableCourseList = new ArrayList<>();
        for (StudentRegistration regist : studentRegistrationsList){
            if (regist.getStudentProfile().equals(currentUserProfile) && regist.getStatus() == Status.ACCEPT){
                availableCourseList.add(regist.getCourse());
            }
        }

        System.out.println("You have access to the following courses: ");
        for (Course course: availableCourseList){
            System.out.println(course.getTitle());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Available Courses";
    }
}
