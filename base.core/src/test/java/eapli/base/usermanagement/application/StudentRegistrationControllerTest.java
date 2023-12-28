package eapli.base.usermanagement.application;

import eapli.base.StudentRegistration.domain.Status;
import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.course.domain.*;
import eapli.base.profile.domain.DateOfBirth;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TaxPayerNumber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StudentRegistrationControllerTest {
/*

    private StudentRegistrationService studentRegistrationService;

    private StudentRegistrationController studentRegistrationController;

    public  StudentRegistrationControllerTest(){
        studentRegistrationController = new StudentRegistrationController();
        studentRegistrationService = new StudentRegistrationService();
        studentRegistrationController.service = studentRegistrationService;
    }

    @Test
    public void testCreateStudentRegistrations() throws Exception {
        StudentRegistration std1 = new StudentRegistration(null,null,Status.ACCEPT,new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"),null));
        StudentRegistration std2 = studentRegistrationController.addStudentRegistration(new StudentProfile(null,null,new DateOfBirth(1,5,2000),new TaxPayerNumber("111111111"),new MecanographicNumber("1211514")),Status.ACCEPT,new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"),null));
        studentRegistrationController.saveStudentRegistration(std1);
        assertEquals(std1,std2);
    }

    @Test
    public void testGetAllCourses() {
        StudentRegistration std1 = new StudentRegistration(null,new StudentProfile(null,null,new DateOfBirth(1,5,2000),new TaxPayerNumber("111111111"),new MecanographicNumber("1211514")),Status.ACCEPT,new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"),null));

        List<StudentRegistration> studentRegistrations = studentRegistrationController.service.getAllStudentsRegistrations();
        assertNotNull(studentRegistrations);
        assertEquals(1, studentRegistrations.size());
        assertTrue(studentRegistrations.contains(std1));
    }

    @Test
   public void getStudentRegistrationsByCourseWhenNoRegistrations() {
        Course course = new Course(1L, State.OPEN, new Capacity(10, 20), EnrollmentsState.OPEN, new Title("Test Course"), new HashSet<>());
        List<StudentRegistration> studentRegistrations = new ArrayList<>();

        List<StudentRegistration> result = studentRegistrationController.service.getAllStudentsRegistrations();

        assertTrue(result.isEmpty());
    }

    @Test
    public void getStudentRegistrationsByCourseWhenRegistrationsExist() { // Create a course
        Course course = new Course(1L, State.OPEN, new Capacity(10, 20), EnrollmentsState.OPEN, new Title("Test Course"), new HashSet<>());

        List<StudentRegistration> studentRegistrations = new ArrayList<>();
        StudentProfile studentProfile1 = new StudentProfile(1L, null, new DateOfBirth(1, 1, 2000), new TaxPayerNumber("123456789"), new MecanographicNumber("12345"));
        StudentProfile studentProfile2 = new StudentProfile(null, null, new DateOfBirth(2, 2, 2000), new TaxPayerNumber("987654321"), new MecanographicNumber("67890"));
        StudentRegistration studentRegistration1 = new StudentRegistration(1L, studentProfile1, Status.PENDING, course);
        StudentRegistration studentRegistration2 = new StudentRegistration(2L, studentProfile2, Status.ACCEPT, course);
        studentRegistrations.add(studentRegistration1);
        studentRegistrations.add(studentRegistration2);


        List<StudentRegistration> result = studentRegistrationController.getStudentRegistrationsByCourse(course);

        assertTrue(result.contains(studentRegistration1));
        assertTrue(result.contains(studentRegistration2));
    }





    public class StudentRegistrationService{
        private List<StudentRegistration> studentRegistrations;
        private boolean newStudentRegistration;

        public StudentRegistrationService() {
            studentRegistrations = new ArrayList<>();
            newStudentRegistration = false;
        }

        public StudentRegistration addNewStudentRegistration(StudentRegistration studentRegistration) {
            studentRegistrations.add(studentRegistration);
            newStudentRegistration = true;
            return studentRegistration;
        }

        public List<StudentRegistration> getAllStudentsRegistrations() {
            return studentRegistrations;
        }

        public void addStudentRegistration(StudentRegistration studentRegistration) {
            studentRegistrations.add(studentRegistration);
        }

        public boolean isNewStudentRegistration() {
            return newStudentRegistration;
        }
    }

    public class StudentRegistrationController {

        private StudentRegistrationService service = new StudentRegistrationService();

        public StudentRegistration addStudentRegistration(final StudentProfile studentProfile, final Status status, final Course course) throws Exception {
            return StudentRegistration.from(null, studentProfile, status, course);
        }

        public void saveStudentRegistration(StudentRegistration studentRegistration) {
            service.addNewStudentRegistration(studentRegistration);
        }

        public List<StudentRegistration> getStudentRegistrationsByCourse(Course course){
            List<StudentRegistration> studentRegistrations = studentRegistrationController.service.getAllStudentsRegistrations();
            List<StudentRegistration> studentRegistrationsByCourse = new ArrayList<>();
            for (StudentRegistration studentRegistration : studentRegistrations) {
                if (studentRegistration.getCourse().getId().intValue() ==  course.getId().intValue())
                    studentRegistrationsByCourse.add(studentRegistration);
            }
            return studentRegistrationsByCourse;
        }
    }


 */
}

