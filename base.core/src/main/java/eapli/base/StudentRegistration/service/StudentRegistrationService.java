package eapli.base.StudentRegistration.service;

import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.StudentRegistration.repository.StudentRegistrationRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.List;
import java.util.Set;

public class StudentRegistrationService {

    private final StudentRegistrationRepository repository = PersistenceContext.repositories().studentRegistrations();


    public StudentRegistration addNewStudentRegistration(StudentRegistration studentRegistration){
        return repository.save(studentRegistration);
    }
    public List<StudentRegistration> getAll(){
        return repository.getAll();
    }

    public StudentRegistrationRepository getRepository() {
        return repository;
    }
}
