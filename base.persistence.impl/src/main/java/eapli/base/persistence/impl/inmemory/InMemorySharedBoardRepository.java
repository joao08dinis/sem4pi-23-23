package eapli.base.persistence.impl.inmemory;

import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.StudentRegistration.repository.StudentRegistrationRepository;
import eapli.base.exam.domain.Exam;
import eapli.base.profile.domain.Profile;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.base.sharedboard.repository.SharedBoardRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemorySharedBoardRepository extends InMemoryDomainRepository<SharedBoard,Long> implements SharedBoardRepository  {


        public InMemorySharedBoardRepository() {
        }

        public InMemorySharedBoardRepository(Function<? super SharedBoard, Long> identityGenerator) {
            super(identityGenerator);
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
