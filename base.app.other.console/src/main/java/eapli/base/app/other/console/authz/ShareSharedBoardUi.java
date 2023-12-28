package eapli.base.app.other.console.authz;

import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.*;
import eapli.base.sharedboard.utils.BoardLock;
import eapli.base.sharedboard.utils.BoardLockController;
import eapli.base.sharedboard.utils.LockStatus;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class ShareSharedBoardUi extends AbstractUI {

    BoardLockController lockController = new BoardLockController();
    SharedBoardController controller = new SharedBoardController();
    ProfileController profileController = new ProfileController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    Map<SharedBoard, Object> boardLocks = new HashMap<>();


    @Override
    protected synchronized boolean doShow() {

        List<SharedBoard> sharedBoards = controller.getAll();

        List<SharedBoard> userBoardList = new ArrayList<>();

        for (SharedBoard board : sharedBoards){
            if (board.getOwner().getOwnerProfile().equals(currentUserProfile) && board.getSharedBoardState().equals(SharedBoardState.OPEN)){
                userBoardList.add(board);
            }
        }

        System.out.println("Choose a board to share: ");

        for (int i = 0; i < userBoardList.size(); i++) {
            System.out.println(i+1 + ": " + userBoardList.get(i));
        }

        Scanner sc = new Scanner(System.in);

        int option = sc.nextInt();

        SharedBoard choosenBoard = userBoardList.get(option-1);

        BoardLock lock = lockController.getBoardLock(choosenBoard.getId()).get();

        if (lock.getLockStatus().equals(LockStatus.UNLOCKED)){
            lock.lockStatus = LockStatus.LOCKED;
            lockController.save(lock);
            List<Profile> currentBoardProfiles = new ArrayList<>();
            Set<Participant> participants = choosenBoard.getParticipants();

            for (Participant participant : participants){
                Profile participantProfile = participant.getProfile();
                currentBoardProfiles.add(participantProfile);
            }
            List<Profile> profilesList = profileController.getStudentProfiles();
            profilesList.addAll(profileController.getTeacherProfiles());

            List<Profile> availableProfileList = new ArrayList<>();
            for (Profile profile : profilesList){
                if (!currentBoardProfiles.contains(profile)){
                    availableProfileList.add(profile);
                }
            }

            System.out.println("Choose a user to share your board with: ");

            for (int i = 0; i <  availableProfileList.size(); i++) {
                System.out.println(i+1 + ": " + availableProfileList.get(i));
            }

            option = sc.nextInt();

            Profile choosenProfile = availableProfileList.get(option-1);

            Set<Post> newPosts = new HashSet<>();
            participants.add(new Participant(null, Permission.READANDWRITE, choosenProfile, newPosts));

            controller.saveSharedBoard(choosenBoard);
            System.out.println("Participant Added!");

            lock.lockStatus = LockStatus.UNLOCKED;
            lockController.save(lock);


        } else System.out.println("Board can't be updated right now");






        return false;
    }

    @Override
    public String headline() {
        return "Share SharedBoard";
    }
    private Object getBoardLock(SharedBoard board) {
        synchronized (boardLocks) {
            return boardLocks.computeIfAbsent(board, key -> new Object());
        }
    }

}
