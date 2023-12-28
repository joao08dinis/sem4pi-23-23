package eapli.base.question.domain;

import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QuestionDescription implements ValueObject {

    private String description;


    public static QuestionDescription from (String description) throws BusinessRuleException {
        try {
            Preconditions.nonNull(description);
            Preconditions.nonEmpty(description);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new QuestionDescription(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
