package eapli.base.course.repository;

import eapli.base.course.domain.Course;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface CourseRepository extends DomainRepository<Long, Course> {
    public List<Course> getAllACourses();
}
