# US 3002

*As User, I want to create a board*

## 1. Context

*This task will allow the user to create a shared board. This action will from now appear on the manager main menu,student main menu and teacher main menu.*

## 2. Requirements

***This user story must be able to allow the Users to:***

*Create a Shared Board*

## 3. Analysis

*Domain Model Excerpt*

![US3002_DM](/DM/US3002_DM.svg)

*System Sequence Diagram*
![US3002_SSD](/SSD/US3002_SSD.svg)
## 4. Design

### 4.1. Realization

*System Diagram*
![US3002_SD](/SD/US3002_SD.svg)

### 4.2. Class Diagram

![US3002_CD](/CD/US3002_CD.svg)

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

**CreateSharedBoardController**

    @UseCaseController
    public class CreateSharedBoardController {
        private final AuthorizationService authz = AuthzRegistry.authorizationService();
    
        ProfileController profileController = new ProfileController();
    
    
        private final SharedBoardService service = new SharedBoardService();
    
        public SharedBoard addSharedBoard(SharedBoardState state, int maxnumrows, int maxnumcolluns, Set<SharedBoardRows> rows, Set<SharedBoardColumns> columns, String path , String title, Set<Cell> cells) throws Exception {
            Profile adminProfile = profileController.getUserProfile(authz.session().get().authenticatedUser()).get();
            return SharedBoard.from(null,state, new BoardConfig(null,maxnumrows,maxnumcolluns,rows,columns), new View(null,new File(path)),new Title(title),cells,new Owner(null,Permission.READANDWRITE,adminProfile,null,state),null);
        }
    
        public void saveCourse(SharedBoard sharedBoard){
            service.addNewSharedBoard(sharedBoard);
        }
    }

**SharedBoard**

    @Entity
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @ToString
    @EqualsAndHashCode
    @Inheritance(strategy = InheritanceType.JOINED)
    public class SharedBoard implements AggregateRoot<Long> {
    
        public static SharedBoard from(Long id, SharedBoardState state,BoardConfig boardConfig,View view,Title title,Set<Cell> cells,Owner owner,Set<Participant> participants)throws BusinessRuleException{
            try {
                Preconditions.nonNull(state);
                Preconditions.nonNull(boardConfig);
                Preconditions.nonNull(view);
                Preconditions.nonNull(title);
                Preconditions.nonNull(cells);
                Preconditions.nonNull(owner);
            } catch (Exception e) {
                throw new BusinessRuleException(e);
            }
    
    
            return new SharedBoard(id,state,boardConfig,null,view,title,cells,owner,null);
        }
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
    
        private SharedBoardState sharedBoardState;
    
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private BoardConfig boardConfig;
    
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private History auditLog;
    
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private View view;
    
        private Title title;
    
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Set<Cell> cells;
    
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Owner owner;
    
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Set<Participant> participants;
    
    
    
    
        @Override
        public boolean sameAs(Object other) {
            return this.getId() == ((SharedBoard) other).getId();
        }
    
        @Override
        public Long identity() {
            return this.getId();
        }



    }

**SharedBoardService**

    public class SharedBoardService {
        private final SharedBoardRepository repository= PersistenceContext.repositories().sharedboards();
    
        public SharedBoard addNewSharedBoard(SharedBoard sharedboard){
            return repository.save(sharedboard);
        }
    
        public List<SharedBoard> getAllSharedBoard(){
            return repository.getAllSharedBoard();
        }
    }
**SharedBoardRepository**

    public interface SharedBoardRepository extends DomainRepository<Long, SharedBoard> {
        public List<SharedBoard> getAllSharedBoard();
    }

**JpaSharedBoardRepository**

    public class JpaSharedBoardRepository extends JpaAutoTxRepository<SharedBoard, Long, Long> implements SharedBoardRepository {

        public JpaSharedBoardRepository(String persistenceUnitName) {
            super(persistenceUnitName, "id");
        }
    
        public JpaSharedBoardRepository(TransactionalContext tx) {
            super(tx, "id");
        }
    
    
        @Override
        public List<SharedBoard> getAllSharedBoard() {
            return null;
        }
    }



**InMemorySharedBoardRepository**

    public class InMemorySharedBoardRepository extends InMemoryDomainRepository<SharedBoard,Long> implements SharedBoardRepository  {


        public InMemorySharedBoardRepository() {
        }

        public InMemorySharedBoardRepository(Function<? super SharedBoard, Long> identityGenerator) {
            super(identityGenerator);
        }
        @Override
        public List<SharedBoard> getAllSharedBoard() {
            return null;
        }
    }

## 6. Integration/Demonstration

*This User Story was fully implemented with no dependencies with other user stories.*

## 7. Observations
