package eapli.base.app.other.console.authz;

import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.base.sharedboard.domain.SharedBoardState;
import eapli.base.sharedboard.utils.BoardLockController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class ArchiveBoardUi extends AbstractUI {
    SharedBoardController controller = new SharedBoardController();
    ProfileController profileController = new ProfileController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {

        List<SharedBoard> sharedBoards = controller.getAll();

        List<SharedBoard> userBoardList = new ArrayList<>();

        for (SharedBoard board : sharedBoards){
            if (board.getOwner().getOwnerProfile().equals(currentUserProfile) && board.getSharedBoardState().equals(SharedBoardState.OPEN)){
                userBoardList.add(board);
            }
        }

        System.out.println("Choose a board to archive: ");

        for (int i = 0; i < userBoardList.size(); i++) {
            System.out.println(i+1 + ": " + userBoardList.get(i));
        }

        Scanner sc = new Scanner(System.in);

        int option = sc.nextInt();

        SharedBoard choosenBoard = userBoardList.get(option-1);

        SharedBoardState state = choosenBoard.getSharedBoardState();

        state = SharedBoardState.ARCHIVED;

        choosenBoard.setSharedBoardState(state);

        controller.saveSharedBoard(choosenBoard);

        System.out.println("Board Archived!");

        return false;
    }

    @Override
    public String headline() {
        return "Archive a Board";
    }
}
