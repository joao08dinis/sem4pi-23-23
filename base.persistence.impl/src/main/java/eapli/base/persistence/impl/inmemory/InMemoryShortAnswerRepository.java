package eapli.base.persistence.impl.inmemory;

import eapli.base.exam.domain.Exam;
import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.QuestionRepository;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryShortAnswerRepository extends InMemoryDomainRepository<ShortAnswer, Long> implements ShortAnswerRepository {

    public InMemoryShortAnswerRepository() {

    }
    public InMemoryShortAnswerRepository(Function<? super Question, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public List<ShortAnswer> getAll() {
        Iterable<ShortAnswer> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
