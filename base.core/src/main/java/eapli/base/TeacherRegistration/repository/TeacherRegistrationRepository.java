package eapli.base.TeacherRegistration.repository;

import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Set;

public interface TeacherRegistrationRepository extends DomainRepository<Long, TeacherRegistration> {

    public List<TeacherRegistration> getAll();
}
