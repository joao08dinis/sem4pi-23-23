package eapli.base.sharedboard.domain;



import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.AggregateRoot;

import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class SharedBoard implements AggregateRoot<Long> {

    public static SharedBoard from(Long id, SharedBoardState state, BoardConfig boardConfig, AuditLog auditLog, View view, SharedBoardTitle shrdtitle, Set<Cell> cells, Owner owner, Set<Participant> participants)throws BusinessRuleException{
        try {
            Preconditions.nonNull(state);
            Preconditions.nonNull(boardConfig);
            Preconditions.nonNull(view);
            Preconditions.nonNull(shrdtitle);
            Preconditions.nonNull(cells);
            Preconditions.nonNull(owner);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }


        return new SharedBoard(id,state,boardConfig, auditLog,view,shrdtitle,cells,owner,participants);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private SharedBoardState sharedBoardState;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BoardConfig boardConfig;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AuditLog auditLog;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private View view;

    private SharedBoardTitle shrdtitle;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Cell> cells;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
