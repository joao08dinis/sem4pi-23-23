package eapli.base.usermanagement.application;

import eapli.base.StudentRegistration.domain.Status;
import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.TeacherRegistration.domain.Role;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.course.domain.*;
import eapli.base.profile.domain.DateOfBirth;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TaxPayerNumber;
import eapli.base.profile.domain.TeacherProfile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeacherRegistrationControllerTest {
/*
    private TeacherRegistrationService teacherRegistrationService;

    private TeacherRegistrationController teacherRegistrationController;

    public  TeacherRegistrationControllerTest(){
        teacherRegistrationController = new TeacherRegistrationController();
        teacherRegistrationService = new TeacherRegistrationService();
        teacherRegistrationController.service = teacherRegistrationService;
    }

    @Test
    public void testCreateStudentRegistrations() throws Exception {
        TeacherRegistration teacherRegistration = new TeacherRegistration(null,new TeacherProfile(null,null,new DateOfBirth(1,5,2000),new TaxPayerNumber("111111111"),"ARC"),Role.REGENT,new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"),null));
        TeacherRegistration teacherRegistration1 = teacherRegistrationController.addTeacherRegistration(new TeacherProfile(null,null,new DateOfBirth(1,5,2000),new TaxPayerNumber("111111111"),"ARC"),Role.REGENT,new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"),null));
        teacherRegistrationController.saveTeacherRegistration(teacherRegistration);
        assertEquals(teacherRegistration1,teacherRegistration);
    }

    @Test
    public void testGetAllCourses() {
        TeacherRegistration teacherRegistration = new TeacherRegistration(null,new TeacherProfile(null,null,new DateOfBirth(1,5,2000),new TaxPayerNumber("111111111"),"ARC"),Role.REGENT,new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"),null));

        List<TeacherRegistration> teacherRegistrations = teacherRegistrationController.service.getAllTeachersRegistrations();
        assertNotNull(teacherRegistrations);
        assertEquals(1, teacherRegistrations.size());
        assertTrue(teacherRegistrations.contains(teacherRegistration));
    }

    public class TeacherRegistrationService{
        private List<TeacherRegistration> teacherRegistrations;
        private boolean newTeacherRegistration;

        public TeacherRegistrationService() {
            teacherRegistrations = new ArrayList<>();
            newTeacherRegistration = false;
        }

        public TeacherRegistration addNewTeacherRegistration(TeacherRegistration teacherRegistration) {
            teacherRegistrations.add(teacherRegistration);
            newTeacherRegistration = true;
            return teacherRegistration;
        }

        public List<TeacherRegistration> getAllTeachersRegistrations() {
            return teacherRegistrations;
        }

        public void addTeacherRegistration(TeacherRegistration teacherRegistration) {
            teacherRegistrations.add(teacherRegistration);
        }

        public boolean isNewTeacherRegistration() {
            return newTeacherRegistration;
        }
    }

    public class TeacherRegistrationController {

        private TeacherRegistrationService service = new TeacherRegistrationService();

        public TeacherRegistration addTeacherRegistration(final TeacherProfile teacherProfile, Role role, Course course) throws Exception {
            return TeacherRegistration.from(null, teacherProfile, role, course);
        }

        public void saveTeacherRegistration(TeacherRegistration teacherRegistration) {
            service.addNewTeacherRegistration(teacherRegistration);
        }

        public boolean hasRegentInCourse(Course course){
            boolean hasRegent = false;
            List<TeacherRegistration> teacherRegistrations = service.getAllTeachersRegistrations();
            for (TeacherRegistration teacherRegistration : teacherRegistrations) {
                if(teacherRegistration.getCourse().getId().intValue() == course.getId().intValue() && teacherRegistration.getRole().equals(Role.REGENT)) {
                    hasRegent = true;
                    break;
                }
            }
            return hasRegent;
        }
    }

 */

}
