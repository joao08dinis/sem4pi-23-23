package eapli.base.question.domain;

import eapli.framework.domain.model.DomainEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MultipleChoice extends Question implements DomainEntity<Long>,Cloneable {
    private String statement;

    @ElementCollection
    private Set<String> options;

    private Answer answer;

    private boolean autoExam;


    public MultipleChoice(final String statement, final Set<String> options, final Answer answer, final double quotation, final boolean automaticExam,Difficulty difficulty) {
        super(null,difficulty,quotation);
        if (!answerIsValid(answer, options)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.statement = statement;
        this.options = options;
        this.answer = answer;
        this.autoExam = automaticExam;
    }

    public void update(String newQuestion, LinkedHashSet<String> newOptions, Answer newAnswer, double quotation,Difficulty difficulty) {
        super.setDifficulty(difficulty);
        super.setQuotation(quotation);
        this.statement = newQuestion;
        this.options = newOptions;
        this.answer = newAnswer;
    }

    private boolean answerIsValid(Answer answerObj, Set<String> optionsObj) {
        return (Integer.parseInt(answerObj.getAnswer()) >= 0 && Integer.parseInt(answerObj.getAnswer()) < optionsObj.size()+1);
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
    public MultipleChoice clone() {
        try {
            MultipleChoice clone = (MultipleChoice) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
