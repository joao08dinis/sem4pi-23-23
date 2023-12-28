package eapli.base.persistence.impl.inmemory;

import eapli.base.profile.domain.AdminProfile;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.profile.repository.ProfileRepository;
import eapli.base.sharedboard.repository.BoardLockRepository;
import eapli.base.sharedboard.utils.BoardLock;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryBoardLockRepository extends InMemoryDomainRepository<BoardLock, Long> implements BoardLockRepository {

    public InMemoryBoardLockRepository() {
    }

    public InMemoryBoardLockRepository(Function<? super BoardLock, Long> identityGenerator) {
        super(identityGenerator);
    }


    @Override
    public Optional<BoardLock> ofIdentity(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(Long entityId) {

    }


    @Override
    public Optional<BoardLock> getBoardLock(Long boardId) {
        return this.matchOne(boardLock -> boardLock.getBoardId() == boardId);
    }
}
