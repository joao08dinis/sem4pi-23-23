package eapli.base.persistence.impl.inmemory;

import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.exam.domain.Exam;
import eapli.base.exam.repository.ExamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryExamRepository extends InMemoryDomainRepository<Exam,Long> implements ExamRepository {

    public InMemoryExamRepository(){


    }

    public InMemoryExamRepository(Function<? super Exam, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public <S extends Exam> S save(S entity) {
        return null;
    }

    @Override
    public void delete(Exam entity) {

    }

    @Override
    public List<Exam> getAll() {
        Iterable<Exam> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
