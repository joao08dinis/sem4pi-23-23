package eapli.base.app.other.console.authz;

import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.*;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;


public class UndoPostItUI extends AbstractUI {

    private final SharedBoardController controller = new SharedBoardController();
    private final ProfileController profileController = new ProfileController();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {
        Set<SharedBoard> userBoards = getUserBoards();
        SharedBoard newSharedBoard = getSharedBoard(userBoards);
        SharedBoard oldSharedBoard = newSharedBoard;
        Set<Cell> cells=newSharedBoard.getCells();

        Cell cell = getCell(newSharedBoard);
        if(cell==null){
            return false;
        }
        try {
            Post(newSharedBoard, cell);
            controller.addUpdateToHistory(new Date(),currentUserProfile,new Change("Cell "+cell.getSharedBoardRows().getNum()+":"+cell.getColumn().getNum()+" with the content: "+cell.getPost().getDescription().toString()),newSharedBoard);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Post was changed with success!");
        return true;
    }
    @Override
    public String headline() {
        return null;
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

        Set<Long> ids= new LinkedHashSet<>();
        boolean isValidOption = false;
        Long option = 0L;
        do {
            try {
                for (Cell cell : sharedBoard.getCells()) {
                    if(cell.getCellState() == CellState.OCCUPIED){
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

    public void Post(SharedBoard sharedBoard, Cell cell) throws BusinessRuleException {
        System.out.println("Undo Post:");
        String content=cell.getPost().getLastPost();
        String[] contents =content.split("-");
        String description=contents[0];
        String typeOfPost=contents[1];
        if(Objects.equals(typeOfPost, "TEXT")){
            cell.getPost().setTypeOfPost(TypeOfPost.TEXT);
        }else if (Objects.equals(typeOfPost, "IMAGE"))
            cell.getPost().setTypeOfPost(TypeOfPost.IMAGE);
        cell.getPost().setDescription(new Description(description));
        cell.getPost().setLastPost("");
        controller.saveSharedBoard(sharedBoard);

    }


    public void postImage(SharedBoard sharedBoard, Cell cell) throws BusinessRuleException {
        System.out.println("Change Cell(Image):");
        cell.getPost().setLastPost(cell.getPost().getDescription().getDescription()+"-"+cell.getPost().getTypeOfPost().toString());
        String content = Console.readLine("Image Path: ");
        cell.getPost().setTypeOfPost(TypeOfPost.IMAGE);
        cell.getPost().setDescription(new Description(content));
        controller.saveSharedBoard(sharedBoard);
    }

}

