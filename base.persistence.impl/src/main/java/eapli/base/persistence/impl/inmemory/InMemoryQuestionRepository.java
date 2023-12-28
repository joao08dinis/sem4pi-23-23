package eapli.base.persistence.impl.inmemory;

import eapli.base.exam.domain.Exam;
import eapli.base.profile.domain.Profile;
import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.QuestionRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryQuestionRepository extends InMemoryDomainRepository<Question, Long> implements QuestionRepository {

    public InMemoryQuestionRepository() {
    }

    public InMemoryQuestionRepository(Function<? super Question, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public Optional<Question> findQuestionByExam(Exam exam) {
        return Optional.empty();
    }

    @Override
    public Set<Question> getAll() {
        Iterable<Question> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toSet());
    }

}
