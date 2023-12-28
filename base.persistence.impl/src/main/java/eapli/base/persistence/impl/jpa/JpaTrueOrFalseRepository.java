package eapli.base.persistence.impl.jpa;

import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.domain.TrueOrFalse;
import eapli.base.question.repository.TrueOrFalseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaTrueOrFalseRepository extends JpaAutoTxRepository<TrueOrFalse, Long, Long> implements TrueOrFalseRepository {
    public JpaTrueOrFalseRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaTrueOrFalseRepository(String persistenceUnitName, Map properties, String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaTrueOrFalseRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<TrueOrFalse> getAll() {
        Iterable<TrueOrFalse> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
