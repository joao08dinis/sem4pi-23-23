# US 1001

*As Manager, I want to be able to register, disable/enable, and list users of the system (Teachers and Students, as well as Managers)*

## 1. Context

*This task will allow the manager to register new students and teachers while also being able to list them. This action will from now appear on the manager main menu.*

## 2. Requirements

***This user story must be able to allow the manager to:***

*register new students*

*register new teachers*

*register new managers*

*list all system profiles*

**This user story doesn't depend on any other user story**

## 3. Analysis

*Domain Model Excerpt*

![US1001_DM](/DM/US1001_DM.svg)

*System Sequence Diagram*
![US1001_SSD](/SSD/US1001_SSD.svg)
## 4. Design

### 4.1. Realization

*System Diagram*
![US1001_SD](/SD/US1001_SD.svg)

### 4.2. Class Diagram

![US1001_CD](/CD/US1001_CD.svg)

### 4.3. Applied Patterns

The SOLID principles and the GoF (Gang of Four) design patterns were applied.

### 4.4. Tests

**Test 1:** *Verifies that teacher profiles are successfully created*

```
@Test
    @DisplayName("Test if its creating teacher profiles sucessfully")
    public void testCreateTeacherProfiles() throws Exception {

        TaxPayerNumber number = null;
        DateOfBirth date = null;
        MecanographicNumber mecanographicNumber = new MecanographicNumber("1211511");
        try {
            number = TaxPayerNumber.from("123456789");
            date = DateOfBirth.from(1,2,2000);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }
        TeacherProfile profile = (TeacherProfile) controller.createTeacherProfile(null, null, number, date, "abc");
        assertEquals("abc", profile.getAcronym());
    }

```
**Test 2:** *Verifies that student profiles are successfully created*
```
    @Test
    @DisplayName("Test if its creating student profiles sucessfully")
    public void testCreateStudentProfiles() throws Exception {

        TaxPayerNumber number = null;
        DateOfBirth date = null;
        MecanographicNumber mecanographicNumber = new MecanographicNumber("1211511");
        try {
            number = TaxPayerNumber.from("123456789");
            date = DateOfBirth.from(1,2,2000);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }
        StudentProfile profile = (StudentProfile) controller.createStudentProfile(null, null, number, date, mecanographicNumber);
        assertEquals("1211511", profile.getMecanographicNumber().toString());
    }
```
**Test 3:** *Verifies that admin profiles are successfully created*

```
    @Test
    @DisplayName("Test if its creating admin profiles sucessfully")
    public void testCreateAdminProfiles() throws Exception {

        TaxPayerNumber number = null;
        DateOfBirth date = null;
        try {
            number = TaxPayerNumber.from("123456789");
            date = DateOfBirth.from(1,2,2000);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }
        AdminProfile profile = (AdminProfile) controller.createAdminProfile(null, null, number, date);
        assertEquals(1, profile.getDateOfBirth().getDay());
````

## 5. Implementation

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

**Profile**

    @Entity
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @ToString
    @EqualsAndHashCode
    @Inheritance(strategy = InheritanceType.JOINED)
    public abstract class Profile implements AggregateRoot<Long> {


    public Profile(Long id, SystemUser user, TaxPayerNumber taxPayerNumber, DateOfBirth dateOfBirth){
        this.id = id;
        this.user = user;
        this.taxPayerNumber = taxPayerNumber;
        this.dateOfBirth = dateOfBirth;
        this.role = getProfileRole();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SystemUser user;

    private TaxPayerNumber taxPayerNumber;

    private DateOfBirth dateOfBirth;

    private String role;

    public abstract boolean sameAs(Object other);

    public String getProfileRole(){
        return this.getUser().roleTypes().iterator().next().toString();
    }

**Profile Service**

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

## 6. Integration/Demonstration

This User Story was fully implemented with no dependencies with other user stories.

## 7. Observations
