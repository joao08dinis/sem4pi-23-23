package eapli.base.question.domain;

import eapli.base.exam.domain.Section;
import eapli.framework.domain.model.AggregateRoot;
import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Question implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long id;

    public Question(Long id, Difficulty difficulty, double quotation){
        this.id = id;
        this.difficulty = difficulty;
        this.quotation = quotation;
    }

    private Difficulty difficulty;

    private double quotation;

    public abstract void verify();
}