package eapli.base.usermanagement.application;

import eapli.base.course.domain.Course;
import eapli.base.course.domain.EnrollmentsState;
import eapli.base.course.service.CourseService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

public class OpenCloseCourseEnrollmentsController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseService service = new CourseService();
    public void OpenCloseCourseEnrollments(Course course, EnrollmentsState state) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);
        course.setEnrollmentsState(state);
        service.addNewCourse(course);
    }

    public List<Course> getAllCourses(){return service.getAllCourses();}
}
