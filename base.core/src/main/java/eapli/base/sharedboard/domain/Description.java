package eapli.base.sharedboard.domain;


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
public class Description implements ValueObject {


    // TODO IMPROVE

    private String description;

    public static Description from (String description) throws BusinessRuleException {
        try {
           // Preconditions.ensure(description==null, "Description is empty");

        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Description(description);
    }

    @Override
    public String toString() {
        return description;
    }
}