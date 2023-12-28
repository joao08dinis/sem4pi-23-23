package eapli.base.app.user.console.presentation.myuser;

import eapli.base.exam.controller.ExamController;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

public class ViewGradesUI extends AbstractUI {
    private final ExamController controller = new ExamController();
    private final ProfileController profileController = new ProfileController();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final StudentProfile currentUserProfile = (StudentProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return null;

    }
}
