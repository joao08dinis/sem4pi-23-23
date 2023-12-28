package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.AdminProfile;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.*;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.*;

public class ShareSharedBoardUi extends AbstractUI {

    SharedBoardController controller = new SharedBoardController();
    ProfileController profileController = new ProfileController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    AdminProfile currentUserProfile = (AdminProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {

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



        return false;
    }

    @Override
    public String headline() {
        return "Share SharedBoard";
    }
}
