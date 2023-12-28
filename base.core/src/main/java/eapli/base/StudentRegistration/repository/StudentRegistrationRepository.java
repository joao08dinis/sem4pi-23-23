package eapli.base.StudentRegistration.repository;

import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Set;

public interface StudentRegistrationRepository extends DomainRepository<Long, StudentRegistration> {

    public List<StudentRegistration> getAll();
}
