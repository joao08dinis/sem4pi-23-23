package eapli.base.persistence.impl.jpa;

import eapli.base.profile.domain.AdminProfile;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.profile.repository.ProfileRepository;
import eapli.base.sharedboard.repository.BoardLockRepository;
import eapli.base.sharedboard.utils.BoardLock;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.*;

public class JpaBoardLockRepository extends JpaAutoTxRepository<BoardLock, Long, Long> implements BoardLockRepository {

    public JpaBoardLockRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaBoardLockRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaBoardLockRepository(TransactionalContext tx) {
        super(tx, "id");
    }


    @Override
    public Optional<BoardLock> getBoardLock(Long boardId) {
        return this.matchOne("e.boardId = '" + boardId +"'");
    }
}
