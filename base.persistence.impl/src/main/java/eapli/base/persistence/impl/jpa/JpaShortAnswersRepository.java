package eapli.base.persistence.impl.jpa;

import eapli.base.exam.domain.Exam;
import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.QuestionRepository;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaShortAnswersRepository extends JpaAutoTxRepository<ShortAnswer, Long, Long> implements ShortAnswerRepository {
    public JpaShortAnswersRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaShortAnswersRepository(String persistenceUnitName, Map properties, String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaShortAnswersRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<ShortAnswer> getAll() {
        Iterable<ShortAnswer> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
