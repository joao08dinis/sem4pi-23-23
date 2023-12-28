package eapli.base.usermanagement.application;

import eapli.base.course.domain.Course;
import eapli.base.course.domain.State;
import eapli.base.course.service.CourseService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.util.List;


public class OpenCloseCourseController {

    public CourseService service = new CourseService();
    public void openCloseCourse(Course course, State state) {
        course.setState(state);
        service.addNewCourse(course);
    }

    public List<Course> getAllCourses(){
        return service.getAllCourses();
    }
}
