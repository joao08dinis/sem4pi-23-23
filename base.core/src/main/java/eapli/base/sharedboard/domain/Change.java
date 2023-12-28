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
public class Change implements ValueObject {

    private String modification;

    public static Change from (String change) throws BusinessRuleException {
        try {
            Preconditions.nonNull(change);
            Preconditions.nonEmpty(change);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Change(change);
    }

    @Override
    public String toString() {
        return modification;
    }
}
