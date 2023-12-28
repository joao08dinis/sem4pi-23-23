package eapli.base.persistence.impl.jpa;

import eapli.base.question.domain.MultipleChoice;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.MultipleChoiceRepository;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaMultipleChoiceRepository extends JpaAutoTxRepository<MultipleChoice, Long, Long> implements MultipleChoiceRepository {
    public JpaMultipleChoiceRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaMultipleChoiceRepository(String persistenceUnitName, Map properties, String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaMultipleChoiceRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<MultipleChoice> getAll() {
        Iterable<MultipleChoice> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
