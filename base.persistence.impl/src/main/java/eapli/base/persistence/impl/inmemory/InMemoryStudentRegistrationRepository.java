package eapli.base.persistence.impl.inmemory;

import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.StudentRegistration.repository.StudentRegistrationRepository;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryStudentRegistrationRepository extends InMemoryDomainRepository<StudentRegistration,Long> implements StudentRegistrationRepository {

    public InMemoryStudentRegistrationRepository(){


    }

    public InMemoryStudentRegistrationRepository(Function<? super StudentRegistration, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public <S extends StudentRegistration> S save(S entity) {
        return null;
    }

    @Override
    public void delete(StudentRegistration entity) {

    }

    @Override
    public List<StudentRegistration> getAll() {
        Iterable<StudentRegistration> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
