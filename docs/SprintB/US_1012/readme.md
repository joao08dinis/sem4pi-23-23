# US 1012

*As Teacher, I want to modification the schedule of a class*

## 1. Context

*This task will teachers to modification the schedules of their classes.*

## 2. Requirements

***This user story must be able to allow the teacher to:***

*Change the date of a class*

*Change the time of a class*

## 3. Analysis

*Domain Model Excerpt*

![US1012_DM](/DM/US1012_DM.svg)

*System Sequence Diagram*
![US1012_SSD](/SSD/US1012_SSD.svg)
## 4. Design

### 4.1. Realization

*System Diagram*
![US1012_SD](/SD/US1012_SD.svg)

### 4.2. Class Diagram

![US1012_CD](/CD/US1012_CD.svg)

### 4.3. Applied Patterns

The SOLID principles and the GoF (Gang of Four) design patterns were applied.

## 5. Implementation

**ClasseController**

    @UseCaseController
public class ClasseController {
private final AuthorizationService authz = AuthzRegistry.authorizationService();
private final ClasseService service = new ClasseService();

    public Classe addClass(int duration, int time_hour,int time_min,String title,
                              TeacherProfile teacherProfile) throws Exception {
        return Classe.from(null,new Duration(duration), new ClasseTime(time_hour,time_min), new Title(title),teacherProfile);
    }

    public void saveMeeting(Classe classe){
        service.addNewClass(classe);
    }

    public List<Classe> getClasses() {
        return service.getAllClasses();
    }

**ClasseService**

    public class ClasseService {
    private final ClasseRepository repository = PersistenceContext.repositories().classe();
    public Classe addNewClass(Classe classe){
        return repository.save(classe);
    }

    public List<Classe> getAllClasses(){return repository.getAllClasses();}
    }

**UpdateClassScheduleUi**

    public class UpdateClassScheduleUi extends AbstractUI {

    Scanner sc = new Scanner(System.in);
    ClasseController controller = new ClasseController();

    private final AuthorizationService authz =
            AuthzRegistry.authorizationService();
    ProfileController profileController = new ProfileController();
    TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {

        List<Classe> classeList = controller.getClasses();
        List<Classe> availableClasseList = new ArrayList<>();

        for (Classe classe : classeList){
            if (classe.getTeacherProfile().equals(currentUserProfile)){
                availableClasseList.add(classe);
            }
        }

        System.out.println("Select class to modification: ");

        for (int i = 0; i < availableClasseList.size(); i++) {
            System.out.println(i+1 + ": " + availableClasseList.get(i));
        }

        int option = sc.nextInt();

        Classe selectedClasse = availableClasseList.get(option-1);


        final String time = Console.readLine("Select new Time(HH:MM)");

        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        ClasseTime newTime = null;
        try {
            newTime = ClasseTime.from(hours, minutes);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }

        selectedClasse.setClasseTime(newTime);
        controller.saveMeeting(selectedClasse);


        return false;
    }

    @Override
    public String headline() {
        return null;
    }
    }


## 6. Integration/Demonstration

This User Story was fully implemented with a dependency with US1010.

## 7. Observations
