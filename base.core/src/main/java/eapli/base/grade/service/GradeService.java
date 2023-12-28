package eapli.base.grade.service;

import eapli.base.grade.domain.Grade;
import eapli.base.grade.repository.GradeRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.Set;

public class GradeService {
    private final GradeRepository repository = PersistenceContext.repositories().grade();

    public Grade addGrade(Grade grade){
        return repository.save(grade);
    }

    public Set<Grade> getAll(){
        return repository.getAll();
    }
}
