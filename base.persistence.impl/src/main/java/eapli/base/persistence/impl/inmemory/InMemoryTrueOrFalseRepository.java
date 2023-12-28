package eapli.base.persistence.impl.inmemory;

import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.domain.TrueOrFalse;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.base.question.repository.TrueOrFalseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryTrueOrFalseRepository extends InMemoryDomainRepository<TrueOrFalse, Long> implements TrueOrFalseRepository {

    public InMemoryTrueOrFalseRepository() {

    }
    public InMemoryTrueOrFalseRepository(Function<? super Question, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public List<TrueOrFalse> getAll() {
        Iterable<TrueOrFalse> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
