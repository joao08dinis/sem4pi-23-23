package eapli.base.persistence.impl.jpa;

import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.TeacherRegistration.repository.TeacherRegistrationRepository;
import eapli.base.course.domain.Course;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaTeacherRegistrationRepository extends JpaAutoTxRepository<TeacherRegistration, Long, Long> implements TeacherRegistrationRepository {

    public JpaTeacherRegistrationRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaTeacherRegistrationRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaTeacherRegistrationRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<TeacherRegistration> getAll() {
        Iterable<TeacherRegistration> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

    }
}
