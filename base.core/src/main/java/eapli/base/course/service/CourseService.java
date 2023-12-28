package eapli.base.course.service;

import eapli.base.course.domain.Course;
import eapli.base.course.repository.CourseRepository;

import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.List;

public class CourseService {
    private final CourseRepository repository = PersistenceContext.repositories().courses();

    public Course addNewCourse(Course course) {
        return repository.save(course);
    }
    public List<Course> getAllCourses(){return repository.getAllACourses();}
}
