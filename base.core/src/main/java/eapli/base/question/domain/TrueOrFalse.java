package eapli.base.question.domain;

import eapli.framework.domain.model.DomainEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class TrueOrFalse extends Question implements DomainEntity<Long>,Cloneable {
    private String statement;

    private Answer answer;

    private boolean autoExam;



    public TrueOrFalse(final String question, final Answer answer, final double quotation, final boolean autoExam,Difficulty difficulty) {
        super(null,difficulty,quotation);
        this.statement = question;
        this.answer = answer;
        this.autoExam = autoExam;
    }

    public void update(String newQuestion, Answer newAnswer, double quotation,Difficulty difficulty) {
        super.setDifficulty(difficulty);
        super.setQuotation(quotation);
        this.statement = newQuestion;
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
    public TrueOrFalse clone() {
        try {
            TrueOrFalse clone = (TrueOrFalse) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}