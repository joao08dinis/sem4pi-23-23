# US 1002

*As Manager, I want to create courses.*

## 1. Context

*This task will allow the manager to create courses, this action will from now appear on the manager main menu.*

## 2. Requirements

*Different editions of a course are to be considered different courses (e.g., Intro-Math-Sem01, Intro-Math-Sem02).*

*Only managers are able to execute this functionality.*

## 3. Analysis

*Domain Model Excerpt*

![US1002_DM](/DM/US1002_DM.svg)

*System Sequence Diagram*

![US1002_SSD](/SSD/US1002_SSD.svg)


## 4. Design

### 4.1. Realization

*System Diagram*

![US1002_SD](/SD/US1002_SD.svg)

### 4.2. Class Diagram

*Class Diagram*

![US1002_CD](/CD/US1002_CD.svg)

### 4.3. Applied Patterns

The SOLID principles and the GoF (Gang of Four) design patterns were applied.

### 4.4. Tests

**Test 1:** *Verifies that it is possible to create an instance of the Course.*

    @Test
    public void testCreateCourses() throws Exception {
        Course course = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP"),null);
        Course course1 = controller.addCourse("ARQCP",State.OPEN,10,200,EnrollmentsState.OPEN);
        controller.saveCourse(course1);
        assertEquals("ARQCP", course.getTitle().toString());
        assertTrue(courseService.isAddNewCourseCalled());
    }

**Test 2:** *Verifies that it is possible to get all the instances of the Courses.*

    @Test
    public void testGetAllCourses() {
        Course course1 = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new eapli.base.course.domain.Title("ARQCP1"),null);
        Course course2 = new Course(null, State.OPEN,new Capacity(10,200), EnrollmentsState.OPEN,new Title("ARQCP2"),null);
        courseService.addCourse(course1);
        courseService.addCourse(course2);

        List<Course> courses = controller.service.getAllCourses();
        assertNotNull(courses);
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));
    }


## 5. Implementation
**AddCourseUI**

    public class AddCourseUI extends AbstractUI {

    private final AddCourseController theController = new AddCourseController();
    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        Course addCourse =null;

        final String title = Console.readLine("Title");
        final String capacity1 = Console.readLine("Capacity(Min)");
        final String capacity2 = Console.readLine("Capacity(Max)");


        EnrollmentsState enrollmentsState1 = null;
        State state1 = null;

        boolean isValidOption = false;
        int input;
        do {
            System.out.println("State");
            System.out.println("1. OPEN");
            System.out.println("2. CLOSE");

            try {
                input = Console.readInteger("Option: ");
                switch (input) {
                    case 1:
                        System.out.println("You selected OPEN.");
                        state1=State.OPEN;
                        isValidOption = true;
                        break;
                    case 2:
                        System.out.println("You selected CLOSE.");
                        state1=State.CLOSE;
                        isValidOption = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the state: " + e.getMessage());
            }
        } while (!isValidOption);


        isValidOption = false;
        do {
            System.out.println("Enrollments State");
            System.out.println("1. OPEN");
            System.out.println("2. CLOSE");

            try {
                input = Console.readInteger("Option: ");
                switch (input) {
                    case 1:
                        System.out.println("You selected OPEN.");
                        enrollmentsState1=EnrollmentsState.OPEN;
                        isValidOption = true;
                        break;
                    case 2:
                        System.out.println("You selected CLOSE.");
                        enrollmentsState1=EnrollmentsState.CLOSE;
                        isValidOption = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the enrollments state: " + e.getMessage());
            }
        } while (!isValidOption);


        try {
            addCourse = this.theController.addCourse(title, state1, Integer.parseInt(capacity1), Integer.parseInt(capacity2), enrollmentsState1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating a course.");
        }
        theController.saveCourse(addCourse);

        System.out.println("Course created!");

        return true;
    }

    @Override
    public String headline() {
        return "Add Course";
    }
    }

**AddCourseController**

    @UseCaseController
    public class AddCourseController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseService service = new CourseService();

    public Course addCourse(final String title, final State state, final int capacity1, final int capacity2,
                            final EnrollmentsState enrollmentsState) throws Exception {

        return Course.from(null,state, new Capacity(capacity1, capacity2), enrollmentsState, new Title(title),null);
    }

    public void saveCourse(Course course){
        service.addNewCourse(course);
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
