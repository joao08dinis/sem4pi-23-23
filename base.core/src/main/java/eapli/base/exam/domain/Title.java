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
public class Title implements ValueObject {

    private String title;


        public static Title from (String title) throws BusinessRuleException {
        try {
            Preconditions.nonNull(title);
            Preconditions.nonEmpty(title);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Title(title);
    }

    @Override
    public String toString() {
        return String.format("%s",title);
    }
}
