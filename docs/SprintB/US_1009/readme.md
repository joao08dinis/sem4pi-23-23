# US 1009

*As Manager, I want to approve or reject students applications to courses*

## 1. Context

*This task will allow the manager to approve or reject requests of students that are trying to enroll in a course. This action will from now appear on the manager main menu.*

## 2. Requirements

***This user story must be able to allow the manager to:***

*Approve or Reject requests of a student*

## 3. Analysis

*Domain Model Excerpt*

![US1009_DM](/DM/US1009_DM.svg)

*System Sequence Diagram*
![US1009_SSD](/SSD/US1009_SSD.svg)
## 4. Design

### 4.1. Realization

*System Diagram*
![US1009_SD](/SD/US1009_SD.svg)

### 4.2. Class Diagram

![US1009_CD](/CD/US1009_CD.svg)

### 4.3. Applied Patterns

The SOLID principles and the GoF (Gang of Four) design patterns were applied.

### 4.4. Tests

## 5. Implementation

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
**StudenRegistrationRepository**

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

*This User Story was fully implemented with a dependency on US1008 where the students can request an enroll in a course*

## 7. Observations