package eapli.base.persistence.impl.inmemory;

import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.TeacherRegistration.repository.TeacherRegistrationRepository;
import eapli.base.course.domain.Course;
import eapli.base.meeting.domain.Meeting;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryTeacherRegistrationRepository extends InMemoryDomainRepository<TeacherRegistration, Long> implements TeacherRegistrationRepository {

    public InMemoryTeacherRegistrationRepository(){


    }

    public InMemoryTeacherRegistrationRepository(Function<? super TeacherRegistration, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public <S extends TeacherRegistration> S save(S entity) {
        return null;
    }

    @Override
    public void delete(TeacherRegistration entity) {

    }

    @Override
    public List<TeacherRegistration> getAll() {
        Iterable<TeacherRegistration> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
