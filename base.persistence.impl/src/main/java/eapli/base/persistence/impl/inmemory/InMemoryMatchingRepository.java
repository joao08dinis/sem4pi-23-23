package eapli.base.persistence.impl.inmemory;

import eapli.base.question.domain.Matching;
import eapli.base.question.repository.MatchingRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryMatchingRepository extends InMemoryDomainRepository<Matching, Long> implements MatchingRepository {

        public InMemoryMatchingRepository() {

        }
        public InMemoryMatchingRepository(Function<? super Matching, Long> identityGenerator) {
            super(identityGenerator);
        }

        @Override
        public List<Matching> getAll() {
            Iterable<Matching> iterable = this.findAll();
            return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        }
}
