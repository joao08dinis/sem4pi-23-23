package eapli.base.usermanagement.application;

import eapli.base.course.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AddCourseControllerTest {

    private AddCourseController controller;
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        controller = new AddCourseController();
        courseService = new CourseService();
        controller.service = courseService;
    }

    @Test
    public void testCreateCourses() throws Exception {
        Course course = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"));
        Course course1 = controller.addCourse("ARQCP",State.OPEN,10,200,EnrollmentsState.OPEN);
        controller.saveCourse(course1);
        assertEquals("ARQCP", course.getTitle().toString());
        assertTrue(courseService.isAddNewCourseCalled());
    }

    @Test
    public void testGetAllCourses() {
        Course course1 = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP1"));
        Course course2 = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new Title("ARQCP2"));
        courseService.addCourse(course1);
        courseService.addCourse(course2);

        List<Course> courses = controller.service.getAllCourses();
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

    public class AddCourseController {
        private CourseService service = new CourseService();

        public Course addCourse(final String title, final State state, final int capacity1, final int capacity2,
                                final EnrollmentsState enrollmentsState) throws Exception {

            return Course.from(null,state, new Capacity(capacity1, capacity2), enrollmentsState, new Title(title));
        }

        public void saveCourse(Course course){
            service.addNewCourse(course);
        }

    }
}