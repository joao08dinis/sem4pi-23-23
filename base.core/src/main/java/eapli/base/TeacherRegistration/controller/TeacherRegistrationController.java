package eapli.base.TeacherRegistration.controller;

import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.TeacherRegistration.domain.Role;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.TeacherRegistration.repository.TeacherRegistrationRepository;
import eapli.base.TeacherRegistration.service.TeacherRegistrationService;
import eapli.base.course.domain.Course;
import eapli.base.course.service.CourseService;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.profile.service.ProfileService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@UseCaseController
public class TeacherRegistrationController {

    private final TeacherRegistrationService service = new TeacherRegistrationService();

    private final CourseService courseService = new CourseService();

    private final ProfileService profileService = new ProfileService();

    public TeacherRegistration addTeacherRegistration(final TeacherProfile teacherProfile, final Role role, final Course course) throws Exception{
        return TeacherRegistration.from(null,teacherProfile,role, course);
    }

    public void saveTeacherRegistration(TeacherRegistration teacherRegistration){
        service.addNewTeacherRegistration(teacherRegistration);
    }

    public boolean hasRegentInCourse(Course course){
        boolean hasRegent = false;
        List<TeacherRegistration> teacherRegistrations = getTeacherRegistrations();
        for (TeacherRegistration teacherRegistration : teacherRegistrations) {
            if(teacherRegistration.getCourse().getId().intValue() == course.getId().intValue() && teacherRegistration.getRole().equals(Role.REGENT)) {
                hasRegent = true;
                break;
            }
        }
        return hasRegent;
    }

    public List<TeacherRegistration> getTeacherRegistrationsByCourse(Course course){
        List<TeacherRegistration> teacherRegistrations = getTeacherRegistrations();
        List<TeacherRegistration> teacherRegistrationsByCourse = new ArrayList<>();
        for (TeacherRegistration teacherRegistration : teacherRegistrations) {
            if (teacherRegistration.getCourse().getId().intValue() ==  course.getId().intValue())
                teacherRegistrationsByCourse.add(teacherRegistration);
        }
        return teacherRegistrationsByCourse;
    }

    public List<Course> getCourses(){
        return courseService.getAllCourses();
    }

    public List<TeacherRegistration> getTeacherRegistrations(){
        return service.getAll();
    }
    public List<Profile> getTeachersProfiles(){
        return profileService.getTeacherProfiles();
    }


}
