package eapli.base.persistence.impl.jpa;

import eapli.base.exam.domain.Exam;
import eapli.base.question.domain.Question;
import eapli.base.question.repository.QuestionRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaQuestionRepository extends JpaAutoTxRepository<Question, Long, Long> implements QuestionRepository {
    public JpaQuestionRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaQuestionRepository(String persistenceUnitName, Map properties, String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaQuestionRepository(TransactionalContext tx) {
        super(tx, "id");
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
