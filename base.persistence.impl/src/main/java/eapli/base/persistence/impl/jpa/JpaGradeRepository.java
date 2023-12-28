package eapli.base.persistence.impl.jpa;

import eapli.base.grade.domain.Grade;
import eapli.base.grade.repository.GradeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaGradeRepository extends JpaAutoTxRepository<Grade, Long, Long> implements GradeRepository {

    public JpaGradeRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaGradeRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaGradeRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public Set<Grade> getAll() {
        Iterable<Grade> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toSet());

    }
}
