# US 1008

*As Student, I want to request my enrollment in a course*

## 1. Context

*After students and courses are registered in the system, the student should be able to ask its enrollment in a certain course, and that is what this user story does. In another functionality the manager is able to accept or decline this requests*

## 2. Requirements

*As Student, I want to request my enrollment in a course, Courses may have a minimum and a maximum number of enrolled students.
This may limit the possibility of opening (i.e. starting) courses that do not satisfy the limits.*

*This functionality has several dependencies, which are US 1001,1002,1003 and 1004*

*Regarding this requirement we understand that registering users, creating courses, open and closing the course and its enrollments is necessary in order for a student to request its enrollment in a course*

## 3. Analysis

*Domain Model Excerpt*
![US1008_DM](/DM/US1008_DM.svg)

*System Sequence Diagram*
![US1008_SSD](/SSD/US1008_SSD.svg)

## 4. Design

### 4.1. Realization

*Sequence Diagram*
![US1008_SD](/SD/US1008_SD.svg)

### 4.2. Class Diagram

*Class Diagram*
![US1008_CD](/CD/US1008_CD.svg)

### 4.3. Applied Patterns

The SOLID principles and the GoF (Gang of Four) design patterns were applied.

### 4.4. Tests

**Test 1:** *Verifies that it is not possible to create an instance of the Example class with null values.*

```
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed() {
	Example instance = new Example(null, null);
}
````

## 5. Implementation

**AddStudentRegistrationUI**

    public class AddStudentRegistrationUI extends AbstractUI {

        private final StudentRegistrationController controller = new StudentRegistrationController();
    
        private final ProfileController profileController = new ProfileController();
    
        private final AuthorizationService authz = AuthzRegistry.authorizationService();
    
        private final StudentProfile currentUserProfile = (StudentProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();
    
        Scanner sc = new Scanner(System.in);
    
        @Override
        protected boolean doShow() {
    
            int option;
    
            StudentRegistration newStudentRegistration;
    
            List<Course> courseList = controller.getCourses();
    
            System.out.println("Choose the course you want to request enrollment:");
            for (int i = 0; i < courseList.size(); i++) {
                System.out.printf("Course %d. : %s\n",i + 1, courseList.get(i).toString());
            }
            option = sc.nextInt();
            Course course = courseList.get(option - 1);
    
            Status status;
            status = Status.PENDING;
    
            List<StudentRegistration> studentRegistrationsByCourse = controller.getStudentRegistrationsByCourse(course);
    
            try {
                if (course.getState().equals(State.CLOSE)) {
                    System.out.println("It was not possible to request your enrollment, because the course is closed");
                } else if (course.getEnrollmentsState().equals(EnrollmentsState.CLOSE)) {
                    System.out.println("It was not possible to request your enrollment, because the enrollments for this course are closed");
                } else if (course.getCapacity().getMaxCapacity() == studentRegistrationsByCourse.size()) {
                    System.out.println("We are sorry, the course is full!");
                } else{
                    newStudentRegistration = controller.addStudentRegistration(currentUserProfile, status, course);
                    controller.saveStudentRegistration(newStudentRegistration);
                    System.out.println("The course enrollment request you requested has been created!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error requesting enrollment in course.");
            }
    
            System.out.println();
    
            System.out.println("Student Enrollments:");
            studentRegistrationsByCourse = controller.getStudentRegistrationsByCourse(course);
            for (StudentRegistration studentRegistration: studentRegistrationsByCourse) {
                System.out.println(studentRegistration);
            }
    
            return true;
    
    
        }
    
        @Override
        public String headline() {
            return "Request Enrollment in Course";
        }
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
    
        public StudentRegistrationRepository getService() {
            return service.getRepository();
        }
    }

**StudentRegistration**

    @Entity
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @ToString
    @EqualsAndHashCode
    @Inheritance(strategy = InheritanceType.JOINED)
    public class StudentRegistration implements AggregateRoot<Long> {

        public static StudentRegistration from (Long id, StudentProfile studentProfile, Status status, Course course) throws BusinessRuleException {
            try {
                Preconditions.nonNull(studentProfile);
                Preconditions.nonNull(status);
                Preconditions.nonNull(course);
            } catch (Exception e) {
                throw new BusinessRuleException(e);
            }
    
            return new StudentRegistration(id, studentProfile, status,course);
        }
    
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
    
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private StudentProfile studentProfile;
    
        private Status status;
    
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Course course;
    
        @Override
        public boolean sameAs(Object other) {
            return this.getId() == ((StudentRegistration) other).getId();
        }
    
        @Override
        public Long identity() {
            return this.getId();
        }
    
        public void setStatus(Status status) {
            this.status = status;
        }
    }

**StudentRegistrationService**

    public class StudentRegistrationService {

        private final StudentRegistrationRepository repository = PersistenceContext.repositories().studentRegistrations();
    
    
        public StudentRegistration addNewStudentRegistration(StudentRegistration studentRegistration){
            return repository.save(studentRegistration);
        }
        public List<StudentRegistration> getAll(){
            return repository.getAll();
        }
    
        public StudentRegistrationRepository getRepository() {
            return repository;
        }
    }

**StudentRegistrationRepository**

    public interface StudentRegistrationRepository extends DomainRepository<Long, StudentRegistration> {

        public List<StudentRegistration> getAll();
    }

**JpaStudentRegistrationRepository**

    public class JpaStudentRegistrationRepository extends JpaAutoTxRepository<StudentRegistration,Long,Long> implements StudentRegistrationRepository {

        public JpaStudentRegistrationRepository(String persistenceUnitName) {
            super(persistenceUnitName, "id");
        }
    
        public JpaStudentRegistrationRepository(String persistenceUnitName, Map properties) {
            super(persistenceUnitName, properties, "id");
        }
    
        public JpaStudentRegistrationRepository(TransactionalContext tx) {
            super(tx, "id");
        }
    
        @Override
        public List<StudentRegistration> getAll() {
            Iterable<StudentRegistration> iterable = this.findAll();
            return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    
        }
    }

**InMemoryStudentRegistrationRepository**

    public class InMemoryStudentRegistrationRepository extends InMemoryDomainRepository<StudentRegistration,Long> implements StudentRegistrationRepository {

        public InMemoryStudentRegistrationRepository(){
    
    
        }
    
        public InMemoryStudentRegistrationRepository(Function<? super StudentRegistration, Long> identityGenerator) {
            super(identityGenerator);
        }
    
        @Override
        public <S extends StudentRegistration> S save(S entity) {
            return null;
        }
    
        @Override
        public void delete(StudentRegistration entity) {
    
        }
    
        @Override
        public List<StudentRegistration> getAll() {
            Iterable<StudentRegistration> iterable = this.findAll();
            return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        }
    }


## 6. Integration/Demonstration

*In order to create this functionality we had to create the domain classes that were represented in the domain model,repository classes and service classes in order to save the students registrations in the database, then pure fabrication classes in order to implement the functionality*

*In order to execute this functionality you have to run the script "./run-user.bat" in the command line being in the directory of the project. After that the application run, you need to log in as a student and select the courses menu, and finally select the option to request enrollment in a course. Keep in mind that the functionalities listed in the dependencies must be run before this*

## 7. Observations

*It is now possible for a student to request enrollment in a course, this action will from now appear on the student courses menu.*