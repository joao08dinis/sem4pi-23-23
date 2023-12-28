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
public class ExamDescription implements ValueObject {

    private String description;


    public static ExamDescription from (String description) throws BusinessRuleException {
        try {
            Preconditions.nonNull(description);
            Preconditions.nonEmpty(description);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new ExamDescription(description);
    }
}
