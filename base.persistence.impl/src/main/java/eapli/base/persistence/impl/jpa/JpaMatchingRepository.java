package eapli.base.persistence.impl.jpa;

import eapli.base.question.domain.Matching;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.MatchingRepository;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaMatchingRepository extends JpaAutoTxRepository<Matching, Long, Long> implements MatchingRepository {
    public JpaMatchingRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaMatchingRepository(String persistenceUnitName, Map properties, String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaMatchingRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<Matching> getAll() {
        Iterable<Matching> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
