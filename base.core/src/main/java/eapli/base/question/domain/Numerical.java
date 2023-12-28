package eapli.base.question.domain;

import eapli.framework.domain.model.DomainEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Numerical extends Question implements DomainEntity<Long>,Cloneable {

    private String statement;

    private Answer answer;

    private boolean autoExam;


    public Numerical(final String statement, final Answer answer, final double quotation, final boolean autoExam, final Difficulty difficulty) {
        super(null,difficulty,quotation);
        this.statement = statement;
        this.answer = answer;
        this.autoExam = autoExam;
    }

    public void update(String statement, Answer answer, double quotation,Difficulty difficulty) {
        super.setDifficulty(difficulty);
        super.setQuotation(quotation);
        this.statement = statement;
        this.answer = answer;
    }

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((Question) other).getId();
    }

    @Override
    public Long identity() {
        return this.getId();
    }

    @Override
    public void verify() {

    }

    @Override
    public Numerical clone() {
        try {
            Numerical clone = (Numerical) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}