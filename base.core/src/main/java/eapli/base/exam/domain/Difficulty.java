package eapli.base.exam.domain;

import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Difficulty implements ValueObject {

    int difficulty;
    public static Difficulty from (int difficulty) throws BusinessRuleException {
        try {
            Preconditions.nonNull(difficulty);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Difficulty(difficulty);
    }
}

