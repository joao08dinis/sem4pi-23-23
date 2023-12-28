package eapli.base.question.domain;

import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Feedback implements ValueObject {

    private String feedback;


    public static Feedback from (String feedback) throws BusinessRuleException {
        try {
            Preconditions.nonNull(feedback);
            Preconditions.nonEmpty(feedback);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Feedback(feedback);
    }

    @Override
    public String toString() {
        return feedback;
    }
}
