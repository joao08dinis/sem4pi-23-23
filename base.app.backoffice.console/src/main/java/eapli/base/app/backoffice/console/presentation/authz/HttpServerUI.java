package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.AdminProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.Participant;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import http_server.HttpServerAjaxVoting;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class HttpServerUI extends AbstractUI {
    private final SharedBoardController controller = new SharedBoardController();
    private final ProfileController profileController = new ProfileController();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AdminProfile currentUserProfile = (AdminProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();


    @Override
    protected boolean doShow() {
        Set<SharedBoard> userBoards = getUserBoards();
        SharedBoard newSharedBoard = getSharedBoard(userBoards);

        try {
            HttpServerAjaxVoting.start(newSharedBoard);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public Set<SharedBoard> getUserBoards(){
        String name = currentUserProfile.getUser().toString();
        List<SharedBoard> boards = controller.getAll();
        Set<SharedBoard> userBoards = new HashSet<>();

        for (SharedBoard board : boards) {
            Set<Participant> participants = board.getParticipants();
            for (Participant participant : participants) {
                if (participant.profile.getUser().toString().equals(name)){
                    userBoards.add(board);
                }
            }
        }
        return userBoards;
    }

    public SharedBoard getSharedBoard(Set<SharedBoard> boards){
        if(boards.isEmpty()){
            System.out.println("This user does not has Shared Boards.");
            return null;
        } else {
            System.out.println("Which Board do you want to change?");

            Set<Long> ids = new HashSet<>();
            boolean isValidOption = false;
            Long option = 0L;
            do {
                try {
                    for (SharedBoard board : boards) {
                        System.out.printf("Id:%d Title:%s\n", board.getId(), board.getShrdtitle());
                        ids.add(board.getId());
                    }
                    option = Console.readLong("Option(ID): ");
                    System.out.println();
                    if (ids.contains(option)) {
                        isValidOption = true;
                    } else {
                        System.out.println("Invalid Option!");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred on select the SharedBoard: " + e.getMessage());
                }
            } while (!isValidOption);

            for (SharedBoard board : boards) {
                if (Objects.equals(board.getId(), option)) {
                    return board;
                }
            }
        }
        return null;
    }
    @Override
    public String headline() {
        return null;
    }
}
