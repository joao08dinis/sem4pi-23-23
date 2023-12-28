package eapli.base.StudentRegistration.controller;

import eapli.base.StudentRegistration.domain.Status;
import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.StudentRegistration.repository.StudentRegistrationRepository;
import eapli.base.StudentRegistration.service.StudentRegistrationService;
import eapli.base.TeacherRegistration.domain.Role;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.course.domain.Course;
import eapli.base.course.service.CourseService;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class StudentRegistrationController {

    private final StudentRegistrationService service = new StudentRegistrationService();

    private final CourseService courseService = new CourseService();

    public List<Course> getCourses(){
        return courseService.getAllCourses();
    }

    public StudentRegistration addStudentRegistration(final StudentProfile studentProfile, final Status status, final Course course) throws Exception{
        return StudentRegistration.from(null,studentProfile,status, course);
    }

    public void saveStudentRegistration(StudentRegistration studentRegistration){
        service.addNewStudentRegistration(studentRegistration);
    }

    public List<StudentRegistration> getStudentRegistrationsByCourse(Course course){
        List<StudentRegistration> studentRegistrations = getStudentRegistrations();
        List<StudentRegistration> studentRegistrationsByCourse = new ArrayList<>();
        for (StudentRegistration studentRegistration : studentRegistrations) {
            if (studentRegistration.getCourse().getId().intValue() ==  course.getId().intValue())
                studentRegistrationsByCourse.add(studentRegistration);
        }
        return studentRegistrationsByCourse;
    }

    public List<StudentRegistration> getStudentRegistrations(){
        return service.getAll();
    }

    public StudentRegistrationRepository getService() {
        return service.getRepository();
    }
}
