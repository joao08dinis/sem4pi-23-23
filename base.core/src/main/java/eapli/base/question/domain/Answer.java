package eapli.base.question.domain;

import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Answer implements ValueObject {

    public static Answer from (String answer) throws BusinessRuleException {
        try {
            Preconditions.nonNull(answer);
            Preconditions.nonEmpty(answer);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Answer(answer);
    }



    private String answer;

    @Override
    public String toString() {
        return answer;
    }

    public String getAnswer() {
        return answer;
    }

}
