# US 1004

*As Manager, I want to open and close courses.*

## 1. Context

*This task will allow the manager to open/close the created courses, this action will from now appear on the manager main menu.*

## 2. Requirements

*This functionality has a dependency with the US 1002.*

**US 1002** As Manager, I want to create courses.

*Regarding this requirement we understand that is necessary create a course before open/close it.*


## 3. Analysis

*Domain Model Excerpt*

![US1004_DM](/DM/US1004_DM.svg)

*System Sequence Diagram*

![US1004_SSD](/SSD/US1004_SSD.svg)


## 4. Design

### 4.1. Realization

*System Diagram*

![US1004_SD](/SD/US1004_SD.svg)

### 4.2. Class Diagram

*Class Diagram*

![US1004_CD](/CD/US1004_CD.svg)

### 4.3. Applied Patterns

The SOLID principles and the GoF (Gang of Four) design patterns were applied.

### 4.4. Tests


**Test 1:** *Verifies that is possible to set the modification of open/close enrollments of an instance of the Course class.*

    @Test
    public void testOpenCloseEnrollmentsCourse() {
        Course course = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"),null);
        controller.OpenCloseCourseEnrollments(course, EnrollmentsState.CLOSE);

        assertEquals(EnrollmentsState.CLOSE, course.getEnrollmentsState());
        assertTrue(courseService.isAddNewCourseCalled());
    }

**Test 2:** *Verifies that is possible to get all instances of the Course class.*

    @Test
    public void testGetAllCourses() {
        Course course1 = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new Title("ARQCP1"),null);
        Course course2 = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new Title("ARQCP2"),null);
        courseService.addCourse(course1);
        courseService.addCourse(course2);

        List<Course> courses = controller.getAllCourses();
        assertNotNull(courses);
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));
    }


## 5. Implementation
**OpenCloseCourseUI**

    public class OpenCloseCourseUI extends AbstractUI {
    private final OpenCloseCourseController theController = new OpenCloseCourseController();
    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        System.out.println("Which course do you want to modification the state?");
        List<Course> courses=theController.getAllCourses();
        int option = -1;

        if(courses.isEmpty()){
            System.out.println("Does not exist any course created!");
            return true;
        }

        boolean isValidOption = false;
        do {
            try {
                for (int i = 0; i <  courses.size(); i++) {
                    System.out.printf("[%d] -> Id:%d Title:%s State:%s\n",i+1,courses.get(i).getId(),courses.get(i).getTitle(),courses.get(i).getState());
                }
                option = Console.readInteger("Option: ");
                System.out.println();
                if (option>0 || option <courses.size()){
                    isValidOption=true;
                }else{
                    System.out.println("Invalid Option!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the course: " + e.getMessage());
            }
        }while (!isValidOption);

        Course course = courses.get(option-1);

        State state = course.getState();

        if (state.equals(State.CLOSE)) {
            state = State.OPEN;
        } else if (state.equals(State.OPEN)) {
            state = State.CLOSE;
        }

        try {
            this.theController.openCloseCourse(course, state);
        } catch (Exception e) {
            throw new RuntimeException("Error changing the state of the course.");
        }

        System.out.printf("The state of the course %s is now %s\n",course.getTitle(),course.getState());

        return true;
    }


    @Override
    public String headline() {
        return "Open/Close Course";
    }
    }

**OpenCloseCourseController**


    public class OpenCloseCourseController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseService service = new CourseService();
    public void openCloseCourse(Course course, State state) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);
        course.setState(state);
        service.addNewCourse(course);
    }

    public List<Course> getAllCourses(){
        return service.getAllCourses();
    }
    }


**Course**

    @Entity
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @ToString
    @EqualsAndHashCode
    @Inheritance(strategy = InheritanceType.JOINED)
    public class Course implements AggregateRoot<Long> {

    public static Course from (Long id, State state, Capacity capacity,EnrollmentsState enrollmentsState,Title title,Set<Exam> exams) throws BusinessRuleException {
        try {
            Preconditions.nonNull(state);
            Preconditions.nonNull(capacity);
            Preconditions.nonNull(title);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Course(id, state, capacity,enrollmentsState,title,exams);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private State state;

    private Capacity capacity;

    private EnrollmentsState enrollmentsState;

    @Column(unique = true)
    private Title title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Exam> exams;

    @Override
    public boolean sameAs(Object other) {
            return Objects.equals(this.getId(), ((Course) other).getId());
    }

    @Override
    public Long identity() {
        return this.getId();
    }

    public void setState(State state){
        this.state=state;
    }

    public void setEnrollmentsState(EnrollmentsState enrollmentsState) {
        this.enrollmentsState = enrollmentsState;
    }

    }

**Capacity**

    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public class Capacity implements ValueObject {

    private int minCapacity;
    private int maxCapacity;

    public static Capacity from (int minCapacity, int maxCapacity) throws BusinessRuleException {
        try {
            Preconditions.ensure((minCapacity < 0), "Invalid minimum capacity.");
            Preconditions.ensure((maxCapacity < 0), "Invalid maximum capacity.");
            Preconditions.ensure((minCapacity > maxCapacity), "Invalid data.");

        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Capacity(minCapacity, maxCapacity);
    }

    @Override
    public String toString() {
        return String.format("Minimum Capacity: %d\nMaximum Capacity: %d", minCapacity,maxCapacity);
    }
    }


**EnrollmentsState**

    public enum EnrollmentsState {
    CLOSE, OPEN;
    }


**State**

    public enum State {
    CLOSE, OPEN;
    }


**Title**

    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public class Title implements ValueObject {

    public String title;
    public static Title from(String title) throws BusinessRuleException {
        try {
            Preconditions.ensure((title==null), "Invalid Title.");

        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Title(title);
    }

    @Override
    public String toString() {
        return String.format("%s",title);
    }
    }


**CourseService**

    public class CourseService {
    private final CourseRepository repository = PersistenceContext.repositories().courses();

    public Course addNewCourse(Course course) {
        return repository.save(course);
    }
    public List<Course> getAllCourses(){return repository.getAllACourses();}
    }   

**CourseRepository**

    public interface CourseRepository extends DomainRepository<Long, Course> {
    public List<Course> getAllACourses();
    }

**JpaCourseRepository**

    public class JpaCourseRepository extends JpaAutoTxRepository<Course, Long, Long> implements CourseRepository{

    public JpaCourseRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaCourseRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaCourseRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<Course> getAllACourses() {
        Iterable<Course> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
    }


**InMemoryCourseRepository**

    public class InMemoryCourseRepository extends InMemoryDomainRepository<Course, Long> implements CourseRepository {

    public InMemoryCourseRepository() {
    }

    public InMemoryCourseRepository(Function<? super Course, Long> identityGenerator) {
        super(identityGenerator);
    }



    @Override
    public List<Course> getAllACourses() {
        Iterable<Course> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
    }

## 6. Integration/Demonstration

This User Story was fully implemented with no dependencies with other user stories.

## 7. Observations
