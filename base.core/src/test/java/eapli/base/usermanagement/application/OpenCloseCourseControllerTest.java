package eapli.base.usermanagement.application;

import eapli.base.course.domain.Capacity;
import eapli.base.course.domain.*;
import eapli.base.course.domain.EnrollmentsState;
import eapli.base.course.domain.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class OpenCloseCourseControllerTest {

    private OpenCloseCourseController controller;
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        controller = new OpenCloseCourseController();
        courseService = new CourseService();
        controller.service = courseService;
    }

    @Test
    public void testOpenCloseCourse() {
        Course course = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new Title("ARQCP"));
        controller.openCloseCourse(course, State.CLOSE);

        assertEquals(State.CLOSE, course.getState());
        assertTrue(courseService.isAddNewCourseCalled());
    }

    @Test
    public void testGetAllCourses() {
        Course course1 = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new Title("ARQCP1"));
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
}
