package eapli.base.persistence.impl.jpa;

import eapli.base.exam.domain.Exam;
import eapli.base.profile.domain.Profile;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.base.sharedboard.repository.SharedBoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaSharedBoardRepository extends JpaAutoTxRepository<SharedBoard, Long, Long> implements SharedBoardRepository {

    public JpaSharedBoardRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaSharedBoardRepository(TransactionalContext tx) {
        super(tx, "id");
    }


    @Override
    public List<SharedBoard> getAllSharedBoard() {
        Iterable<SharedBoard> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
    @Override
    public Optional<SharedBoard> findBoardById(Long id) {
        return this.findById(id);

    }
}
