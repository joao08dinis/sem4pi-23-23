package eapli.base.sharedboard.utils;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.sharedboard.repository.BoardLockRepository;
import eapli.base.sharedboard.repository.SharedBoardRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public class BoardLockController {
    private final BoardLockRepository repository = PersistenceContext.repositories().locks();


    public Optional<BoardLock> getBoardLock(Long boardId){
        return repository.getBoardLock(boardId);
    }

    public void save(BoardLock lock){
        repository.save(lock);
    }

}
