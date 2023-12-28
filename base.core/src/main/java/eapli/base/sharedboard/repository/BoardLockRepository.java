package eapli.base.sharedboard.repository;

import eapli.base.sharedboard.utils.BoardLock;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface BoardLockRepository extends DomainRepository <Long, BoardLock> {

    public Optional<BoardLock> getBoardLock(Long boardId);

}
