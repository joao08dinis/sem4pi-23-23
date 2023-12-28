package eapli.base.exam.domain;

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
public class FeedbackExam implements ValueObject {

    private String feedback;

    public static FeedbackExam from (String feedback) throws BusinessRuleException {

        try {
            Preconditions.nonNull(feedback);
            Preconditions.nonEmpty(feedback);
        }catch (Exception e){
            throw new BusinessRuleException();
        }

        return new FeedbackExam(feedback);
    }
}
