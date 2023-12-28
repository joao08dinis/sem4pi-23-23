package eapli.base.question.domain;

import eapli.framework.domain.model.DomainEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Matching extends Question implements DomainEntity<Long>,Cloneable {

    private String statement;

    @ElementCollection
    private Set<String> table1;

    @ElementCollection
    private Set<String> table2;

    private boolean autoExam;

    private Answer answer;


    public Matching(final String statement, final Set<String> table1, final Set<String> table2, final double quotation, final boolean autoExam, final Difficulty difficulty, final Answer answer) {
        super(null,difficulty,quotation);
        this.statement = statement;
        this.table1 = table1;
        this.table2 = table2;
        this.autoExam = autoExam;
        this.answer=answer;
    }

    public void update(String newQuestion, Set<String> newTable1, Set<String> newTable2, double quotation,Difficulty difficulty,Answer answer) {
        super.setDifficulty(difficulty);
        super.setQuotation(quotation);
        this.statement = newQuestion;
        this.table1 = newTable1;
        this.table2 = newTable2;
        this.answer=answer;
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
    public Matching clone() {
        try {
            Matching clone = (Matching) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
