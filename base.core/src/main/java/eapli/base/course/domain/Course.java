package eapli.base.course.domain;

import eapli.base.exam.domain.Exam;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class Course implements AggregateRoot<Long> {

    public static Course from (Long id, State state, Capacity capacity,EnrollmentsState enrollmentsState,Title title) throws BusinessRuleException {
        try {
            Preconditions.nonNull(state);
            Preconditions.nonNull(capacity);
            Preconditions.nonNull(title);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Course(id, state, capacity,enrollmentsState,title);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private State state;

    private Capacity capacity;

    private EnrollmentsState enrollmentsState;

    @Column(unique = true)
    private Title title;

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
