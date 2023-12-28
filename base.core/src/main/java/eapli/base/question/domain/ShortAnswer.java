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
public class ShortAnswer extends Question implements DomainEntity<Long>,Cloneable {
    private String statement;

    private Answer answer;

    private boolean autoExam;


    public ShortAnswer(final String statement, final Answer answer, final double quotation, final boolean autoExam, final Difficulty difficulty) {
        super(null,difficulty,quotation);
        this.statement = statement;
        this.answer = answer;
        this.autoExam = autoExam;
    }

    public void update(String newstatement, Answer newAnswer, double quotation,Difficulty difficulty) {
        super.setDifficulty(difficulty);
        super.setQuotation(quotation);
        this.statement = newstatement;
        this.answer = newAnswer;
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
    public ShortAnswer clone() {
        try {
            ShortAnswer clone = (ShortAnswer) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

