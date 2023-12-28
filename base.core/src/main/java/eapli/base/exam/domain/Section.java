package eapli.base.exam.domain;

import eapli.base.question.domain.Question;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Entity
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Section implements DomainEntity<Long> {

    public static Section from (Long id, TextualDescription description, Set<Question> questions, Difficulty difficulty) throws BusinessRuleException {
        try {
            Preconditions.nonNull(description);
            Preconditions.nonEmpty(Collections.singleton(questions));
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Section(id, description, questions, difficulty);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TextualDescription description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Question> questions;

    private Difficulty difficulty;

    @Override
    public boolean sameAs(Object other) {
        return this.getId().equals(((Section) other).getId());
    }

    @Override
    public Long identity() {
        return this.getId();
    }

    @SneakyThrows
    public void verify() {

    }
}
