package eapli.base.persistence.impl.inmemory;

import eapli.base.course.domain.Course;
import eapli.base.course.repository.CourseRepository;
import eapli.base.exam.domain.Exam;
import eapli.base.profile.domain.Profile;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryCourseRepository extends InMemoryDomainRepository<Course, Long> implements CourseRepository {

    public InMemoryCourseRepository() {
    }

    public InMemoryCourseRepository(Function<? super Course, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public <S extends Course> S save(S entity) {
        return null;
    }

    @Override
    public void delete(Course entity) {

    }

    @Override
    public List<Course> getAllACourses() {
        Iterable<Course> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
