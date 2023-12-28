package eapli.base.sharedboard.repository;

import eapli.base.sharedboard.domain.Post;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.framework.domain.repositories.DomainRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SharedBoardRepository extends DomainRepository<Long, SharedBoard> {
    public List<SharedBoard> getAllSharedBoard();

    public Optional<SharedBoard> findBoardById(Long id);
}