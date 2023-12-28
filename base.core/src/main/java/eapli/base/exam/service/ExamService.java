package eapli.base.exam.service;


import eapli.base.exam.domain.Exam;
import eapli.base.exam.repository.ExamRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ExamService {
    private final ExamRepository repository = PersistenceContext.repositories().exams();

    public Exam addNewExam(Exam exam){
        return repository.save(exam);
    }

    public List<Exam> getAll(){
        return repository.getAll();
    }
}
