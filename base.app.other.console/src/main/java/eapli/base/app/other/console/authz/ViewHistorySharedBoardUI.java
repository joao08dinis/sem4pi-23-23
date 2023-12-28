package eapli.base.app.other.console.authz;

import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.Modification;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.base.sharedboard.utils.BoardLock;
import eapli.base.sharedboard.utils.BoardLockController;
import eapli.base.sharedboard.utils.LockStatus;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;
import java.util.Scanner;

public class ViewHistorySharedBoardUI extends AbstractUI {
    SharedBoardController controller = new SharedBoardController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProfileController profileController = new ProfileController();
    BoardLockController lockController = new BoardLockController();
    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {

        int option;

        TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

        List<SharedBoard> sharedBoardList = controller.getSharedBoardsByUser(currentUserProfile);

        if (sharedBoardList.size() == 0){
            System.out.println("You are not registered in any SharedBoard or there are none created!");
        }else {
            System.out.println("Please choose one of the SharedBoards:");
            for (int i = 0; i < sharedBoardList.size(); i++) {
                System.out.printf("SharedBoard %d. : %s\n" , i + 1, sharedBoardList.get(i).getShrdtitle().toString());
            }
            option = sc.nextInt();
            SharedBoard sharedBoard = sharedBoardList.get(option - 1);
            BoardLock lock = lockController.getBoardLock(sharedBoard.getId()).get();
            if (lock.getLockStatus().equals(LockStatus.UNLOCKED)){
                lock.lockStatus = LockStatus.LOCKED;
                lockController.save(lock);
                if (sharedBoard.getAuditLog().getAuditLogs().size() == 0){
                    System.out.println("This SharedBoard hasn't been updated!");
                }else {
                    System.out.printf("History of %s:\n", sharedBoard.getShrdtitle());
                    for (Modification modification : sharedBoard.getAuditLog().getAuditLogs()) {
                        System.out.println(modification);
                        System.out.println("------------------------------------------------------");
                    }
                }
                lock.lockStatus = LockStatus.UNLOCKED;
                lockController.save(lock);


            } else System.out.println("Board can't be updated right now");
        }

        return true;
    }

    @Override
    public String headline() {
        return "View the history of updates on a Shared Board";
    }
}