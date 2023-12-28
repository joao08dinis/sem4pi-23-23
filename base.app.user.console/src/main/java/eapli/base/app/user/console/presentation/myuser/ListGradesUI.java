package eapli.base.app.user.console.presentation.myuser;

import eapli.base.grade.controller.GradeController;
import eapli.base.grade.domain.Grade;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Set;

public class ListGradesUI extends AbstractUI {

    GradeController controller = new GradeController();
    ProfileController profileController = new ProfileController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    StudentProfile currentUserProfile = (StudentProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {
        System.out.println("List of Grades\n");
        Set<Grade> gradeSet = currentUserProfile.getGrades();
        int cont=0;

        for (Grade grade : gradeSet) {
            System.out.printf("Course : %s \nExam : %s \nGrade : %d\n", grade.getExam().getCourse().getTitle().getTitle(), grade.getExam().getTitle().getTitle(), grade.getGrade());
            System.out.println();
            cont++;
        }

        if (cont==0) System.out.println("This student does not has exams.");


        return true;
    }

    @Override
    public String headline() {
        return "List Grades";
    }


}
