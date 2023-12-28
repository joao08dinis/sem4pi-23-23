package eapli.base.usermanagement.application;

import eapli.base.course.domain.*;
import eapli.base.course.service.CourseService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OpenCloseCourseEnrollmentsControllerTest {

    private OpenCloseCourseEnrollmentsController controller;
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        controller = new OpenCloseCourseEnrollmentsController();
        courseService = new CourseService();
        controller.service = courseService;
    }

    @Test
    public void testOpenCloseEnrollmentsCourse() {
        Course course = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"));
        controller.OpenCloseCourseEnrollments(course, EnrollmentsState.CLOSE);

        assertEquals(EnrollmentsState.CLOSE, course.getEnrollmentsState());
        assertTrue(courseService.isAddNewCourseCalled());
    }

    @Test
    public void testGetAllCourses() {
        Course course1 = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP1"));
        Course course2 = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new Title("ARQCP2"));
        courseService.addCourse(course1);
        courseService.addCourse(course2);

        List<Course> courses = controller.getAllCourses();
        assertNotNull(courses);
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));
    }

    public class CourseService{
        private List<Course> courses;
        private boolean addNewCourseCalled;

        public CourseService() {
            courses = new ArrayList<>();
            addNewCourseCalled = false;
        }

        public Course addNewCourse(Course course) {
            courses.add(course);
            addNewCourseCalled = true;
            return course;
        }

        public List<Course> getAllCourses() {
            return courses;
        }

        public void addCourse(Course course) {
            courses.add(course);
        }

        public boolean isAddNewCourseCalled() {
            return addNewCourseCalled;
        }
    }

    public class OpenCloseCourseEnrollmentsController {
        private CourseService service = new CourseService();
        public void OpenCloseCourseEnrollments(Course course, EnrollmentsState state) {
            course.setEnrollmentsState(state);
            service.addNewCourse(course);
        }

        public List<Course> getAllCourses(){return service.getAllCourses();}
    }
}

