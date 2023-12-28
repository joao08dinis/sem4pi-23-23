package eapli.base.persistence.impl.jpa;

import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.StudentRegistration.repository.StudentRegistrationRepository;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaStudentRegistrationRepository extends JpaAutoTxRepository<StudentRegistration,Long,Long> implements StudentRegistrationRepository {

    public JpaStudentRegistrationRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaStudentRegistrationRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaStudentRegistrationRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<StudentRegistration> getAll() {
        Iterable<StudentRegistration> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

    }


}
