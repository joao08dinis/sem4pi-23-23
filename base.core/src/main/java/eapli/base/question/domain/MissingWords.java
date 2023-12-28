package eapli.base.question.domain;

import eapli.framework.domain.model.DomainEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MissingWords extends Question implements DomainEntity<Long>,Cloneable{
    private String statement;

    @ElementCollection
    private Set<Answer> answers;

    private boolean autoExam;


    public MissingWords(final String sentence,final Set<Answer> answers, final double quotation, final boolean autoExam,Difficulty difficulty) {
        super(null,difficulty,quotation);
        this.statement = sentence;
        this.answers = answers;
        this.autoExam = autoExam;

    }


    public void update(String newSentence, Set<Answer> newAnswers, double newQuotation,Difficulty difficulty) {
        super.setDifficulty(difficulty);
        super.setQuotation(newQuotation);
        this.statement = newSentence;
        this.answers = newAnswers;
    }


    @Override
    public MissingWords clone() {
        try {
            MissingWords clone = (MissingWords) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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
}