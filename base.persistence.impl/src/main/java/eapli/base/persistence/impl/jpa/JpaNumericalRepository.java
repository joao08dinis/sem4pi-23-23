package eapli.base.persistence.impl.jpa;

import eapli.base.question.domain.Numerical;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.NumericalRepository;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaNumericalRepository extends JpaAutoTxRepository<Numerical, Long, Long> implements NumericalRepository {
    public JpaNumericalRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaNumericalRepository(String persistenceUnitName, Map properties, String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaNumericalRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<Numerical> getAll() {
        Iterable<Numerical> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
