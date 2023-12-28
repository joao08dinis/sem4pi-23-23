package eapli.base.persistence.impl.inmemory;

import eapli.base.question.domain.Numerical;
import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.NumericalRepository;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryNumericalRepository extends InMemoryDomainRepository<Numerical, Long> implements NumericalRepository {

    public InMemoryNumericalRepository() {

    }
    public InMemoryNumericalRepository(Function<? super Question, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public List<Numerical> getAll() {
        Iterable<Numerical> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
