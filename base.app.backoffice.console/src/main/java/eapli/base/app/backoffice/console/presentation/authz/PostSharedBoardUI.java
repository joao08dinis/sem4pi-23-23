
package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.notification.domain.Notification;
import eapli.base.notification.domain.TypeOfNotification;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.AdminProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.*;
import eapli.base.sharedboard.utils.BoardLock;
import eapli.base.sharedboard.utils.BoardLockController;
import eapli.base.sharedboard.utils.LockStatus;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class PostSharedBoardUI extends AbstractUI {

    private final BoardLockController lockController = new BoardLockController();

    private final SharedBoardController controller = new SharedBoardController();
    private final ProfileController profileController = new ProfileController();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AdminProfile currentUserProfile = (AdminProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();


    @Override
    protected boolean doShow() {
        Set<SharedBoard> userBoards = getUserBoards();
        SharedBoard newSharedBoard = getSharedBoard(userBoards);

        BoardLock lock = lockController.getBoardLock(newSharedBoard.getId()).get();

        if (lock.getLockStatus().equals(LockStatus.UNLOCKED)) {
            lock.lockStatus = LockStatus.LOCKED;
            lockController.save(lock);

            if (newSharedBoard == null) {
                return false;
            } else {

                Cell cell = getCell(newSharedBoard);
                if (cell == null) {
                    return false;
                }

                boolean isValid = false;
                do {
                    System.out.printf("Type of Post:\n[1]Text\n[2]Image\n");
                    int option = Console.readInteger("Option :");
                    if (option == 1) {
                        isValid = true;
                        try {
                            postText(newSharedBoard, cell);
                            controller.addUpdateToHistory(new Date(), currentUserProfile, new Change("Cell " + cell.getSharedBoardRows().getNum() + ":" + cell.getColumn().getNum() + " with the content: " + cell.getPost().getDescription().toString()), newSharedBoard);
                        } catch (BusinessRuleException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (option == 2) {
                        isValid = true;
                        try {
                            postImage(newSharedBoard, cell);
                            controller.addUpdateToHistory(new Date(), currentUserProfile, new Change("Cell " + cell.getSharedBoardRows().getNum() + ":" + cell.getColumn().getNum() + " with the content: " + cell.getPost().getDescription().toString()), newSharedBoard);
                        } catch (BusinessRuleException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Invalid Option.");
                        isValid = false;
                    }
                } while (!isValid);

                System.out.println("Post made with success!");
            }
            lock.lockStatus = LockStatus.UNLOCKED;
            lockController.save(lock);
        } else System.out.println("Board can't be updated right now");
        return true;
    }


    public Set<SharedBoard> getUserBoards(){
        String name = currentUserProfile.getUser().toString();
        List<SharedBoard> boards = controller.getAll();
        Set<SharedBoard> userBoards = new HashSet<>();

        for (SharedBoard board : boards) {
            Set<Participant> participants = board.getParticipants();
            for (Participant participant : participants) {
                if (participant.profile.getUser().toString().equals(name) && participant.getPermission()!= Permission.READ){
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

    public Cell getCell(SharedBoard sharedBoard){
        System.out.println("Which cell do you want to change?");

        Set<Long> ids= new HashSet<>();
        boolean isValidOption = false;
        Long option = 0L;
        do {
            try {
                for (Cell cell : sharedBoard.getCells()) {
                    if(cell.getCellState() == CellState.FREE){
                        System.out.printf("Id:%d \n\tRow:%s \n\tColumn:%s\n",cell.getId(),cell.getSharedBoardRows(),cell.getColumn());
                        ids.add(cell.getId());
                    }
                }
                if(ids.isEmpty()){
                    System.out.println("This Shared Board does not has free cells to post.");
                    return null;
                }
                option = Console.readLong("Option(ID): ");
                System.out.println();
                if (ids.contains(option)){
                    isValidOption=true;
                }else{
                    System.out.println("Invalid Option!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the Cell: " + e.getMessage());
            }
        }while (!isValidOption);

        for (Cell cell : sharedBoard.getCells()) {
            if(Objects.equals(cell.getId(), option)){
                return cell;
            }
        }

        return null;

    }

    public void postText(SharedBoard sharedBoard, Cell cell) throws BusinessRuleException {
        System.out.println("Change Cell(Text):");
        String content = Console.readLine("Content: ");
        cell.setCellState(CellState.OCCUPIED);
        controller.addPosts(sharedBoard,cell,new Post(null,new Notification(null, TypeOfNotification.from("SharedBoard")),new Description(content),TypeOfPost.TEXT,null));
    }


    public void postImage(SharedBoard sharedBoard, Cell cell) throws BusinessRuleException {
        System.out.println("Change Cell(Image):");
        String content = Console.readLine("Image Path: ");
        cell.setCellState(CellState.OCCUPIED);
        controller.addPosts(sharedBoard,cell,new Post(null,new Notification(null,TypeOfNotification.from("SharedBoard")),new Description(content),TypeOfPost.IMAGE,null));
    }

    @Override
    public String headline() {
        return "Make a post in a SharedBoard";
    }
}