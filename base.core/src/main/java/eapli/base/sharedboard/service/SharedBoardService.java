package eapli.base.sharedboard.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.sharedboard.domain.Post;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.base.sharedboard.domain.SharedBoardRows;
import eapli.base.sharedboard.repository.SharedBoardRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public class SharedBoardService {
    private final SharedBoardRepository repository= PersistenceContext.repositories().sharedboards();

    public SharedBoard addNewSharedBoard(SharedBoard sharedboard){
        return repository.save(sharedboard);
    }

    public List<SharedBoard> getAllSharedBoard(){
        return repository.getAllSharedBoard();
    }

    public void removeSharedBoard(SharedBoard sharedBoard) {
        repository.remove(sharedBoard);
    }


    public SharedBoard getBoardById(Long id){
        return repository.findBoardById(id).get();
    }
}
