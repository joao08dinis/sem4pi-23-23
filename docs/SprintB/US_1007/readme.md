# US 1007

*As Manager, I want to enroll students in bulk by importing their data using a csv file*

## 1. Context

*After courses are registered in the system, the manager should be able to import students via csv file, register them in the system and enroll them in a certain course, and that is what this user story does.*

## 2. Requirements

*As Manager, I want to enroll students in bulk by importing their data using a csv file, this can be made by importing a csv file
with students. Only managers are able to execute this functionality. Courses may have a minimum and a maximum number of enrolled students.
This may limit the possibility of opening (i.e. starting) courses that do not satisfy the limits.*

*This functionality has several dependencies, which are US 1001,1002,1003 and 1004*

*Regarding this requirement we understand that registering users, creating courses, open and closing the course and its enrollments is necessary in order to enroll students in bulk by importing their data using a csv file*

## 3. Analysis

*Domain Model Excerpt*
![US1007_DM](/DM/US1007_DM.svg)

*System Sequence Diagram*
![US1007_SSD](/SSD/US1007_SSD.svg)

## 4. Design

### 4.1. Realization

*Sequence Diagram*
![US1007_SD](/SD/US1007_SD.svg)

### 4.2. Class Diagram

*Class Diagram*
![US1007_CD](/CD/US1007_CD.svg)

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

**ImportStudentsUI**

    public class ImportStudentsUI extends AbstractUI implements Importer{

        private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);
        private final AddUserController theController = new AddUserController();
        private final ProfileController profileController = new ProfileController();
        final ListUsersController listUserController = new ListUsersController();
        private final StudentRegistrationController controller = new StudentRegistrationController();
        Scanner sc = new Scanner(System.in);
    
        @Override
        public void importFile(String file) throws FileNotFoundException {
    
            Scanner fileScanner = new Scanner(new File(file));
            Status status;
            status = Status.PENDING;
            int option;
            StudentRegistration newStudentRegistration;
    
            List<Course> courseList = controller.getCourses();
    
            System.out.println("Choose the course you want to request enrollment:");
            for (int i = 0; i < courseList.size(); i++) {
                System.out.printf("Course %d. : %s\n",i + 1, courseList.get(i).toString());
            }
    
            option = sc.nextInt();
            Course course = courseList.get(option - 1);
    
            List<StudentRegistration> studentRegistrationsByCourse = controller.getStudentRegistrationsByCourse(course);
    
            try {
                if (course.getState().equals(State.CLOSE)) {
                    System.out.println("It was not possible to request your enrollment, because the course is closed");
                } else if (course.getEnrollmentsState().equals(EnrollmentsState.CLOSE)) {
                    System.out.println("It was not possible to request your enrollment, because the enrollments for this course are closed");
                } else if (course.getCapacity().getMaxCapacity() == studentRegistrationsByCourse.size()) {
                    System.out.println("We are sorry, the course is full!");
                } else {
                    int lineNumber = 1;
                    fileScanner.nextLine(); //Ignorar cabeçalho
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        String[] elements;
                        String username;
                        String password;
                        String firstName;
                        String lastName;
                        String email;
                        String number;
                        String mechanographic;
                        int day;
                        int month;
                        int year;
                        elements = line.split(",");
                        username = elements[0];
                        password = elements[1];
                        firstName = elements[2];
                        lastName = elements[3];
                        email = elements[4];
                        mechanographic = elements[5];
                        number = elements[6];
                        day = Integer.parseInt(elements[7]);
                        month = Integer.parseInt(elements[8]);
                        year = Integer.parseInt(elements[9]);
    
                        DateOfBirth dateOfBirth = DateOfBirth.from(day, month, year);
                        MecanographicNumber mecanographicNumber = new MecanographicNumber(mechanographic);
                        TaxPayerNumber taxPayerNumber = TaxPayerNumber.from(number);
    
                        try {
                            try {
                                final Set<Role> roles = new HashSet<>();
                                roles.add(BaseRoles.STUDENT);
                                SystemUser user = registerUser(username, password, firstName, lastName, email, roles);
                                StudentProfile profile = (StudentProfile) profileController.createStudentProfile(user,null, taxPayerNumber, dateOfBirth, mecanographicNumber);
                                newStudentRegistration = controller.addStudentRegistration(profile, status, course);
                                controller.saveStudentRegistration(newStudentRegistration);
                            }catch (IllegalArgumentException e){
                                e.printStackTrace();
                                System.out.println("An error has occurred while registering and enrolling a new student");
                            }
    
                        } catch (final IntegrityViolationException | ConcurrencyException e) {
                            System.out.println("That username is already in use.");
                            System.out.println(e.getMessage());
                        }
    
    
                        System.out.println("Student enrolled successfully!");
    
                    }
    
    
                    List<StudentRegistration> studentRegistrations = controller.getStudentRegistrationsByCourse(course);
                    for (StudentRegistration studentRegistration : studentRegistrations) {
                        System.out.println(studentRegistration);
                    }
                    System.out.println("The course enrollments requests you requested have been created!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error requesting enrollment in course.");
            }
    
    
        }
    
        @Override
        protected boolean doShow() {
    
            System.out.println("Please specify the path of the file that has the students data: ");
    
            String filePath = sc.nextLine();
    
            try {
                importFile(filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
    
            return true;
        }
    
        protected SystemUser registerUser(final String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles) {
            SystemUser u = null;
            try {
                u = theController.addUser(username, password, firstName, lastName, email, roles);
                LOGGER.debug("»»» %s", username);
            } catch (final IntegrityViolationException | ConcurrencyException e) {
                // assuming it is just a primary key violation due to the tentative
                // of inserting a duplicated user. let's just lookup that user
                u = listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
            }
            return u;
        }
    
        @Override
        public String headline() {
            return "Enroll Students using csv file";
        }
    }

**AddUserController**

    @UseCaseController
    public class AddUserController {

        private final AuthorizationService authz = AuthzRegistry.authorizationService();
        private final UserManagementService userSvc = AuthzRegistry.userService();
    
        /**
         * Get existing RoleTypes available to the user.
         *
         * @return a list of RoleTypes
         */
        public Role[] getRoleTypes() {
            return BaseRoles.nonUserValues();
        }
    
        public SystemUser addUser(final String username, final String password, final String firstName,
                final String lastName,
                final String email, final Set<Role> roles, final Calendar createdOn) {
            authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);
    
            return userSvc.registerNewUser(username, password, firstName, lastName, email, roles,
                    createdOn);
        }
    
        public SystemUser addUser(final String username, final String password, final String firstName,
                final String lastName,
                final String email, final Set<Role> roles) {
            return addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
        }
    }

**ProfileController**

    @UseCaseController
    public class ProfileController {

        private final ProfileService profileService = new ProfileService();
    
        public Profile createTeacherProfile(SystemUser user, Long id, TaxPayerNumber taxPayerNumber, DateOfBirth dateOfBirth, String acronym){
    
            TeacherProfile profile = new TeacherProfile(id, user, dateOfBirth, taxPayerNumber, acronym);
    
            profileService.addNewProfile(profile);
    
            return profile;
        }
    
        public Profile createStudentProfile(SystemUser user, Long id, TaxPayerNumber taxPayerNumber, DateOfBirth dateOfBirth, MecanographicNumber mecanographicNumber){
    
            StudentProfile profile = new StudentProfile(id, user, dateOfBirth, taxPayerNumber, mecanographicNumber);
    
            profileService.addNewProfile(profile);
    
            return profile;
        }
    
        public Profile createAdminProfile(SystemUser user, Long id, TaxPayerNumber taxPayerNumber, DateOfBirth dateOfBirth){
    
            AdminProfile profile = new AdminProfile(id, user, dateOfBirth, taxPayerNumber);
    
            profileService.addNewProfile(profile);
    
            return profile;
        }
    
        public Optional<Profile> getUserProfile(SystemUser user){
            return profileService.getProfileFromDatabase(user);
        }
    
    
        public List<Profile> getTeacherProfiles(){
            return profileService.getTeacherProfiles();
        }
    
        public List<Profile> getStudentProfiles(){
            return profileService.getStudentProfiles();
        }
    
        public List<Profile> getAdminProfiles(){
            return profileService.getAdminProfiles();
        }

    }

**ListUsersController**

    @UseCaseController
    public class ListUsersController{

        private final AuthorizationService authz = AuthzRegistry.authorizationService();
        private final UserManagementService userSvc = AuthzRegistry.userService();
    
        public Iterable<SystemUser> allUsers() {
            authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);
    
            return userSvc.allUsers();
        }
    
        public Optional<SystemUser> find(final Username u) {
            return userSvc.userOfIdentity(u);
        }
    }

**StudentRegistrationController**

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

**ProfileService**

    @Service
    public class ProfileService {
        private final ProfileRepository repository = PersistenceContext.repositories().profiles();
        
            public Profile addNewProfile(Profile profile){
        
                return repository.save(profile);
            }
        
            public Optional<Profile> getProfileFromDatabase(SystemUser user){
        
                Optional<Profile> profile = repository.findProfileByUser(user);
                return profile;
        
            }
        
        
            public List<Profile> getTeacherProfiles(){
                return repository.getTeacherProfiles();
            }
        
        
            public List<Profile> getStudentProfiles(){
                return repository.getStudentProfiles();
            }
        
            public List<Profile> getAdminProfiles(){
                return repository.getAdminProfiles();
            }


    }

**ProfileRepository**

    public interface ProfileRepository extends DomainRepository<Long, Profile> {

        Optional<Profile> findProfileByUser(SystemUser user);
    
        List<Profile> getTeacherProfiles();
    
        List<Profile> getStudentProfiles();
    
        List<Profile> getAdminProfiles();
    }

**JpaProfileRepository**

    public class JpaProfileRepository extends JpaAutoTxRepository<Profile, Long, Long> implements ProfileRepository {

        public JpaProfileRepository(String persistenceUnitName) {
            super(persistenceUnitName, "id");
        }
    
        public JpaProfileRepository(String persistenceUnitName, Map properties) {
            super(persistenceUnitName, properties, "id");
        }
    
        public JpaProfileRepository(TransactionalContext tx) {
            super(tx, "id");
        }
    
    
    
        @Override
        public Optional<Profile> findProfileByUser(SystemUser user) {
            return this.matchOne("e.user.email.email = '" + user.email().toString() + "'");
    
        }
    
    
        @Override
        public List<Profile> getTeacherProfiles() {
            List<Profile> teacherProfiles = new ArrayList<>();
            for (Profile profile : this.findAll()) {
                if (profile instanceof TeacherProfile){
                    teacherProfiles.add(profile);
                }
            }
            return teacherProfiles;
        }
    
    
        @Override
        public List<Profile> getStudentProfiles() {
            List<Profile> studentProfiles = new ArrayList<>();
            for (Profile profile : this.findAll()) {
                if (profile instanceof StudentProfile){
                    studentProfiles.add(profile);
                }
            }
            return studentProfiles;
        }
    
        @Override
        public List<Profile> getAdminProfiles() {
            List<Profile> adminProfiles = new ArrayList<>();
            for (Profile profile : this.findAll()) {
                if (profile instanceof AdminProfile){
                    adminProfiles.add(profile);
                }
            }
            return adminProfiles;
        }




    }

**InMemoryProfileRepository**

    public class InMemoryProfileRepository extends InMemoryDomainRepository<Profile, Long> implements ProfileRepository {

        public InMemoryProfileRepository() {
        }
    
        public InMemoryProfileRepository(Function<? super Profile, Long> identityGenerator) {
            super(identityGenerator);
        }
    
        @Override
        public Optional<Profile> findProfileByUser(SystemUser user) {
            return this.matchOne(profile -> profile.getUser().equals(user));
        }
    
    
        @Override
        public List<Profile> getTeacherProfiles() {
            Iterator<Profile> iterator = this.match(profile -> profile instanceof TeacherProfile).iterator();
            List<Profile> list = new ArrayList<>();
            while (iterator.hasNext()){
                list.add(iterator.next());
            }
    
            return list;
        }
    
    
    
        @Override
        public List<Profile> getStudentProfiles() {
            Iterator<Profile> iterator = this.match(profile -> profile instanceof StudentProfile).iterator();
            List<Profile> list = new ArrayList<>();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
    
            return list;
        }
    
        @Override
        public List<Profile> getAdminProfiles() {
                Iterator<Profile> iterator = this.match(profile -> profile instanceof AdminProfile).iterator();
                List<Profile> list = new ArrayList<>();
                while (iterator.hasNext()) {
                    list.add(iterator.next());
                }
    
                return list;
        }
    
    
    
        @Override
        public Optional<Profile> ofIdentity(Long id) {
            return Optional.empty();
        }
    
        @Override
        public void deleteOfIdentity(Long entityId) {
    
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

*In order to create this functionality we had to create the domain classes that were represented in the domain model,repository classes and service classes in order to save the students registrations and profiles in the database, then pure fabrication classes in order to implement the functionality*

*In order to execute this functionality you have to run the script "./run-backoffice.bat" in the command line being in the directory of the project. After that the application run, you need to log in as a manager and select the courses menu, and finally select the option to Enroll Students using a csv file. Keep in mind that the functionalities listed in the dependencies must be run before this*

## 7. Observations

*It is now possible for a manager to enroll students in bulk by importing their data using a csv file, this action will from now appear on the manager courses menu.*