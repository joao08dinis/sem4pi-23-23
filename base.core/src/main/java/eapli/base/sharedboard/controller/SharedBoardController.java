package eapli.base.sharedboard.controller;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.meeting.domain.Meeting;
import eapli.base.meeting.domain.MeetingParticipant;
import eapli.base.meeting.domain.MeetingState;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.Profile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.base.sharedboard.domain.*;
import eapli.base.sharedboard.repository.BoardLockRepository;
import eapli.base.sharedboard.service.SharedBoardService;
import eapli.base.sharedboard.utils.BoardLock;
import eapli.base.sharedboard.utils.BoardLockController;
import eapli.base.sharedboard.utils.LockStatus;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.File;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBoardController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    ProfileController profileController = new ProfileController();

    public SharedBoardService service = new SharedBoardService();

    private final BoardLockController lockController = new BoardLockController();

    public SharedBoard addSharedBoard(SharedBoardState state, int maxnumrows, int maxnumcolluns, Set<SharedBoardRows> rows, Set<SharedBoardColumns> columns, String path, String title, Set<Cell> cells) throws Exception {
        Profile adminProfile = profileController.getUserProfile(authz.session().get().authenticatedUser()).get();
        Set<Participant> participants = new HashSet<>();
        participants.add(new Participant(null, Permission.READANDWRITE, adminProfile, null));
        return SharedBoard.from(null, state, new BoardConfig(null, maxnumrows, maxnumcolluns, rows, columns), new AuditLog(null, new TreeSet<Modification>()), new View(null, new File(path)), new SharedBoardTitle(title), cells, new Owner(null, Permission.READANDWRITE, adminProfile, null), participants);
    }

    public void saveSharedBoard(SharedBoard sharedBoard) {
        service.addNewSharedBoard(sharedBoard);
        List<SharedBoard> boards = service.getAllSharedBoard();
        Long id = boards.get(boards.size()-1).getId();
        if (lockController.getBoardLock(id).isEmpty()){
            lockController.save(new BoardLock(null, id, LockStatus.UNLOCKED));
        }


    }

    public void removeBoard(SharedBoard sharedBoard) {
        service.removeSharedBoard(sharedBoard);
    }

    public List<SharedBoard> getAll() {
        return service.getAllSharedBoard();
    }

    public void addPosts(SharedBoard board, Cell cell, Post post) {
        cell.setPost(post);
        saveSharedBoard(board);
    }

    public void addUpdateToHistory(Date date, Profile profile, Change change, SharedBoard sharedBoard) throws BusinessRuleException {
        Modification newModification = Modification.from(null, date, profile, change);
        sharedBoard.getAuditLog().getAuditLogs().add(newModification);
        saveSharedBoard(sharedBoard);
    }

    public SharedBoard getBoardId(Long id) {
        List<SharedBoard> sharedBoards = service.getAllSharedBoard();
        for (int i = 0; i < sharedBoards.size(); i++) {
            if (sharedBoards.get(i).getId() == id)
                return sharedBoards.get(i);
        }
        return null;
    }

    public List<SharedBoard> getSharedBoardsByUser(Profile userProfile) {
        List<SharedBoard> sharedBoards = getAll();
        List<SharedBoard> sharedBoardsByUser = new ArrayList<>();
        for (SharedBoard sharedBoard : sharedBoards) {
            if (sharedBoard.getOwner().getOwnerProfile().equals(userProfile) || isParticipant(userProfile,sharedBoard)) {
                sharedBoardsByUser.add(sharedBoard);
            }
        }
        return sharedBoardsByUser;
    }

    public boolean isParticipant(Profile userProfile, SharedBoard sharedBoard){
        boolean flag = false;
        for (Participant participant : sharedBoard.getParticipants()){
            if (participant.getProfile().equals(userProfile)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
