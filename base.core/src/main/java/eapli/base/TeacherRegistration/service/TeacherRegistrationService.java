package eapli.base.TeacherRegistration.service;

import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.TeacherRegistration.repository.TeacherRegistrationRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.List;
import java.util.Set;

public class TeacherRegistrationService {

    private final TeacherRegistrationRepository repository = PersistenceContext.repositories().teacherRegistrations();

    public TeacherRegistration addNewTeacherRegistration(TeacherRegistration teacherRegistration){
        return repository.save(teacherRegistration);
    }

    public List<TeacherRegistration> getAll(){
        return repository.getAll();
    }
}
