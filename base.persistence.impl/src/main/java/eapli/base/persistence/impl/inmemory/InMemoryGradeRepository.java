package eapli.base.persistence.impl.inmemory;

import eapli.base.exam.domain.Exam;
import eapli.base.grade.domain.Grade;
import eapli.base.grade.repository.GradeRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryGradeRepository extends InMemoryDomainRepository<Grade, Long> implements GradeRepository {

    public InMemoryGradeRepository() {
    }

    public InMemoryGradeRepository(Function<? super Grade, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public <S extends Grade> S save(S entity) {
        return null;
    }

    @Override
    public void delete(Grade entity) {
    }

    @Override
    public Set<Grade> getAll() {
        Iterable<Grade> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toSet());
    }
}
