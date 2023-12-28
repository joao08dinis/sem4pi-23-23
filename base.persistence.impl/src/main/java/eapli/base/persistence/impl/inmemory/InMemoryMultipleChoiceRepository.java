package eapli.base.persistence.impl.inmemory;

import eapli.base.question.domain.MultipleChoice;
import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.MultipleChoiceRepository;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryMultipleChoiceRepository extends InMemoryDomainRepository<MultipleChoice, Long> implements MultipleChoiceRepository {

    public InMemoryMultipleChoiceRepository() {

    }
    public InMemoryMultipleChoiceRepository(Function<? super Question, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public List<MultipleChoice> getAll() {
        Iterable<MultipleChoice> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
