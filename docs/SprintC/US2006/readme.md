# US 2006

*As Teacher, I want to view a list of the grades of exams of my courses*

## 1. Context

*This task will allow teachers to view all the grades of a course the teacher is part of*

## 2. Requirements

***This user story must be able to allow the teacher to:***

*View all the grades of a course the teacher is part of.*


## 3. Analysis

*Domain Model Excerpt*

![US2006_DM](/DM/US2006_DM.svg)

*System Sequence Diagram*
![US2006_SSD](/SSD/US2006_SSD.svg)
## 4. Design

### 4.1. Realization

*System Diagram*
![US2006_SD](/SD/US2006_SD.svg)

### 4.2. Class Diagram

![US2006_CD](/CD/US2006_CD.svg)

### 4.3. Applied Patterns

The SOLID principles and the GoF (Gang of Four) design patterns were applied.

## 5. Implementation

**ExamController**

public class ExamController {

    private final ExamService service = new ExamService();

    public Exam addExam(String title, TeacherProfile teacherProfile, Set<StudentProfile> studentProfiles, Course course, Set<Section> sections, OpenDate openDate, CloseDate closeDate) throws Exception{
        return Exam.from(null,new Title(title),teacherProfile,studentProfiles,course,sections,openDate,closeDate);
    }

    public void saveExam(Exam exam){
        service.addNewExam(exam);
    }

    public Set<Exam> getAll(){
        return service.getAll();
    }
    }

**ExamService**

    public class ExamService {
    private final ExamRepository repository = PersistenceContext.repositories().exams();

    public Exam addNewExam(Exam exam){
        return repository.save(exam);
    }

    public Set<Exam> getAll(){
        return repository.getAll();
    }
    }

**ViewGradesUi**

    public class ViewGradesUi extends AbstractUI {

    Scanner sc = new Scanner(System.in);

    ExamController examController = new ExamController();


    TeacherRegistrationController controller = new TeacherRegistrationController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
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

        System.out.println("Choose course to view grades: ");
        for (int i = 0; i < availableCourseList.size(); i++) {
            System.out.println(i+1 + ": " + availableCourseList.get(i));

        }

        int option = sc.nextInt();

        Course choosenCourse = availableCourseList.get(option-1);

        List<Profile> students = profileController.getStudentProfiles();

        List<Grade> choosenGrades = new ArrayList<>();

        for (Profile student : students){
            StudentProfile studentProfile = (StudentProfile) student;
            Set<Grade> studentGrades = studentProfile.getGrades();
            for (Grade grade : studentGrades){
                if (grade.getExam().getCourse().equals(choosenCourse)){
                    choosenGrades.add(grade);
                }
            }
        }

        System.out.println("Grades: ");
        for (Grade grade : choosenGrades){
            System.out.println("Grade: " + grade.getGrade() + " Exam: " + grade.getExam().getTitle());
        }


        return false;
    }

    @Override
    public String headline() {
        return "View Course Grades";
    }
}


## 6. Integration/Demonstration

This User Story was fully implemented with a dependency with US1002, US1005 and US2001.

## 7. Observations
