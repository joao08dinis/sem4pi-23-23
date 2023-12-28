package eapli.base.persistence.impl.jpa;

import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.exam.domain.Exam;
import eapli.base.exam.repository.ExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaExamRepository extends JpaAutoTxRepository<Exam, Long, Long> implements ExamRepository {


    public JpaExamRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaExamRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaExamRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<Exam> getAll() {
        Iterable<Exam> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

    }
}
