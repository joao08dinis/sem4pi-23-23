package eapli.base.usermanagement.application;

import eapli.base.course.domain.*;
import eapli.base.course.service.CourseService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AddCourseController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseService service = new CourseService();

    public Course addCourse(final String title, final State state, final int capacity1, final int capacity2,
                            final EnrollmentsState enrollmentsState) throws Exception {

        return Course.from(null,state, new Capacity(capacity1, capacity2), enrollmentsState, new Title(title));
    }

    public void saveCourse(Course course){
        service.addNewCourse(course);
    }



}