# US 1005

*Manager, I want to set the teachers of a course*

## 1. Context

*After teachers and courses are registered in the system, the manager should be able to set the teachers of a certain course, and that is what this user story does.*

## 2. Requirements

*As Manager, I want to set the teachers of a course, each course may have several teachers and must have
only one Teacher in Charge. Only managers are able to execute this functionality.*

*This functionality has several dependencies, which are US 1001,1002,1003 and 1004*

*Regarding this requirement we understand that registering users, creating courses, open and closing the course and its enrollments is necessary in order to set the teachers of the course*

## 3. Analysis

*Domain Model Excerpt*
![US1005_DM](/DM/US1005_DM.svg)

*System Sequence Diagram*
![US1005_SSD](/SSD/US1005_SSD.svg)

## 4. Design

### 4.1. Realization

*Sequence Diagram*
![US1005_SD](/SD/US1005_SD.svg)

### 4.2. Class Diagram

*Class Diagram*
![US1005_CD](/CD/US1005_CD.svg)

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

**AddTeacherRegistrationUI**

    public class AddTeacherRegistrationUI extends AbstractUI {

        private final TeacherRegistrationController controller = new TeacherRegistrationController();
    
        Scanner sc = new Scanner(System.in);
    
        @Override
        protected boolean doShow() {
    
            int option;
    
            TeacherRegistration newTeacherRegistration = null;
    
            List<Course> courseList = controller.getCourses();
    
            List<Profile> teacherList = controller.getTeachersProfiles();
    
            System.out.println("Choose the course you want to set teachers:");
            for (int i = 0; i < courseList.size(); i++) {
                System.out.printf("Course %d. : %s\n" , i + 1, courseList.get(i).toString());
            }
            option = sc.nextInt();
            Course course = courseList.get(option - 1);
    
            System.out.println("Choose the teacher you want to set:");
            for (int i = 0; i < teacherList.size(); i++) {
                System.out.printf("Teacher %d. : %s\n", i + 1, teacherList.get(i).toString());
            }
            option = sc.nextInt();
            TeacherProfile teacherProfile = (TeacherProfile) teacherList.get(option - 1);
    
            System.out.println("Choose the Role that the teacher will have:");
            System.out.println("1. REGENT");
            System.out.println("2. ASSISTANT");
    
            Role role=null;
            boolean isValidOption = false;
    
            do{
                try {
                    option = Console.readInteger("Option: ");
                    switch (option) {
                        case 1:
                            System.out.println("You selected REGENT.");
                            role = Role.REGENT;
                            isValidOption = true;
                            break;
                        case 2:
                            System.out.println("You selected ASSISTANT.");
                            role = Role.ASSISTANT;
                            isValidOption = true;
                            break;
                        default:
                            System.out.println("Invalid option selected. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred on select the state: " + e.getMessage());
                }
            } while(!isValidOption);
    
            try {
                if(course.getState().equals(State.CLOSE)) {
                    System.out.println("It was not possible to register this teacher because the course is closed");
                }else if(course.getEnrollmentsState().equals(EnrollmentsState.CLOSE)) {
                    System.out.println("It was not possible to register this teacher because the enrollments for this course are closed");
                }else if (controller.hasRegentInCourse(course) && role.equals(Role.REGENT)) {
                    System.out.println("This Course already has a regent!");
                }else {
                    newTeacherRegistration = controller.addTeacherRegistration(teacherProfile, role, course);
                    controller.saveTeacherRegistration(newTeacherRegistration);
                    System.out.println("The teacher registration you requested has been created!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error creating a teacher registration.");
            }
    
            System.out.println();
            System.out.println("Teacher Registrations in this Course:");
            System.out.println();
            List<TeacherRegistration> teacherRegistrationsList = controller.getTeacherRegistrationsByCourse(course);
            for (TeacherRegistration teacherRegistration: teacherRegistrationsList) {
                System.out.println(teacherRegistration);
            }
    
            return true;
    
        }
    
        @Override
        public String headline() {
            return "Set Teachers of a Course";
        }
    }

**AddTeacherRegistrationController**

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

**TeacherRegistration**

    @Entity
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @ToString
    @EqualsAndHashCode
    @Inheritance(strategy = InheritanceType.JOINED)
    public class TeacherRegistration implements AggregateRoot<Long> {

        public static TeacherRegistration from (Long id, TeacherProfile teacherProfile, Role role, Course course) throws BusinessRuleException {
            try {
                Preconditions.nonNull(teacherProfile);
                Preconditions.nonNull(role);
                Preconditions.nonNull(course);
            } catch (Exception e) {
                throw new BusinessRuleException(e);
            }
    
            return new TeacherRegistration(id, teacherProfile, role,course);
        }
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
    
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private TeacherProfile teacherProfile;
    
        private Role role;
    
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Course course;
    
        @Override
        public boolean sameAs(Object other) {
            return this.getId() == ((TeacherRegistration) other).getId();
        }
    
        @Override
        public Long identity() {
            return this.getId();
        }

    }

**TeacherRegistrationService**

    public class TeacherRegistrationService {

        private final TeacherRegistrationRepository repository = PersistenceContext.repositories().teacherRegistrations();

        public TeacherRegistration addNewTeacherRegistration(TeacherRegistration teacherRegistration){
            return repository.save(teacherRegistration);
        }

        public List<TeacherRegistration> getAll(){
        return repository.getAll();
        }
    }

**TeacherRegistrationRepository**

    public interface TeacherRegistrationRepository extends DomainRepository<Long, TeacherRegistration> {

        public List<TeacherRegistration> getAll();
    }

**JpaTeacherRegistrationRepository**

    public class JpaTeacherRegistrationRepository extends JpaAutoTxRepository<TeacherRegistration, Long, Long> implements TeacherRegistrationRepository {

        public JpaTeacherRegistrationRepository(String persistenceUnitName) {
            super(persistenceUnitName, "id");
        }
    
        public JpaTeacherRegistrationRepository(String persistenceUnitName, Map properties) {
            super(persistenceUnitName, properties, "id");
        }
    
        public JpaTeacherRegistrationRepository(TransactionalContext tx) {
            super(tx, "id");
        }
    
        @Override
        public List<TeacherRegistration> getAll() {
            Iterable<TeacherRegistration> iterable = this.findAll();
            return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    
        }
    }

**InMemoryTeacherRegistrationRepository**

    public class InMemoryTeacherRegistrationRepository extends InMemoryDomainRepository<TeacherRegistration, Long> implements TeacherRegistrationRepository {

        public InMemoryTeacherRegistrationRepository(){
    
    
        }
    
        public InMemoryTeacherRegistrationRepository(Function<? super TeacherRegistration, Long> identityGenerator) {
            super(identityGenerator);
        }
    
        @Override
        public <S extends TeacherRegistration> S save(S entity) {
            return null;
        }
    
        @Override
        public void delete(TeacherRegistration entity) {
    
        }
    
        @Override
        public List<TeacherRegistration> getAll() {
            Iterable<TeacherRegistration> iterable = this.findAll();
            return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        }
    }



## 6. Integration/Demonstration

*In order to create this functionality we had to create the domain classes that were represented in the domain model,repository classes and service classes in order to save the teachers registrations in the database, then pure fabrication classes in order to implement the functionality*

*In order to execute this functionality you have to run the script "./run-backoffice.bat" in the command line being in the directory of the project. After that the application run, you need to log in as a manager and select the courses menu, and finally select the option to set the teachers of a course. Keep in mind that the functionalities listed in the dependencies must be run before this*

## 7. Observations

*It is now possible for a manager to set teachers of a course, this action will from now appear on the manager courses menu.*