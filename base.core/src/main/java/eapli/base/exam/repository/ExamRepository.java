package eapli.base.exam.repository;

import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.exam.domain.Exam;
import eapli.base.question.domain.Question;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExamRepository extends DomainRepository<Long, Exam> {

    public List<Exam> getAll();

}
