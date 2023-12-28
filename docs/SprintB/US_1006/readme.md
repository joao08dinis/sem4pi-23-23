# US 1006

*As User, I want to list all the courses that are available to me*

## 1. Context

*This task will allow users (students or teachers) to see what courses are available to them*

## 2. Requirements

***This user story must be able to allow students to:***

*See what courses they are enrolled in*

***This user story must be able to allow teachers to:***

*See what courses they are participating in either as a regent or assistant teacher*

## 3. Analysis

*Domain Model Excerpt*

![US1006_DM](/DM/US1006_DM.svg)

*System Sequence Diagram*
![US1006_SSD](/SSD/US1006_SSD.svg)
## 4. Design

### 4.1. Realization

*System Diagram*
![US1006_SD](/SD/US1006_SD.svg)

### 4.2. Class Diagram

![US1006_CD](/CD/US1006_CD.svg)

### 4.3. Applied Patterns

The SOLID principles and the GoF (Gang of Four) design patterns were applied.

## 5. Implementation

**ViewCoursesUi** (Student)

public class ViewCoursesUi extends AbstractUI {

    StudentRegistrationController controller = new StudentRegistrationController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    ProfileController profileController = new ProfileController();
    StudentProfile currentUserProfile = (StudentProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {
        List<StudentRegistration> studentRegistrationsList = controller.getStudentRegistrations();
        List<Course> availableCourseList = new ArrayList<>();
        for (StudentRegistration regist : studentRegistrationsList){
            if (regist.getStudentProfile().equals(currentUserProfile) && regist.getStatus() == Status.ACCEPT){
                availableCourseList.add(regist.getCourse());
            }
        }

        System.out.println("You have access to the following courses: ");
        for (Course course: availableCourseList){
            System.out.println(course.getTitle());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Available Courses";
    }

**ViewCoursesUi** (Teacher)

    public class ViewCoursesUi extends AbstractUI {

    TeacherRegistrationController controller = new TeacherRegistrationController();
    private final AuthorizationService authz =
            AuthzRegistry.authorizationService();
    ProfileController profileController = new ProfileController();
    TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {
        List<TeacherRegistration> teacherRegistrationList = controller.getTeacherRegistrations();
        List<Course> availableCourseList = new ArrayList<>();
        for (TeacherRegistration regist : teacherRegistrationList){
            if (regist.getTeacherProfile().equals(currentUserProfile)){
                availableCourseList.add(regist.getCourse());
            }
        }

        System.out.println("You have access to the following courses: ");
        for (Course course: availableCourseList){
            System.out.println(course.getTitle());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Available Courses";
    }


**StudentRegistrationController**

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


**TeacherRegistrationController**

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

## 6. Integration/Demonstration

This User Story was fully implemented with dependencies on US1002, US1003, US1004, US1005 and US1009.

## 7. Observations
