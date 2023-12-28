package eapli.base.persistence.impl.jpa;

import eapli.base.course.domain.Course;
import eapli.base.course.repository.CourseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaCourseRepository extends JpaAutoTxRepository<Course, Long, Long> implements CourseRepository{

    public JpaCourseRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaCourseRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaCourseRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<Course> getAllACourses() {
        Iterable<Course> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
