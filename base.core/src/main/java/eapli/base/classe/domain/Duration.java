package eapli.base.classe.domain;

import eapli.base.profile.domain.DateOfBirth;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;
import java.time.Month;
import java.time.Year;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class Duration implements ValueObject {
    private int min;

    public static Duration from (int min) throws BusinessRuleException {
        try {
            Preconditions.ensure((min<=0), "Invalid meeting duration.");
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Duration(min);
    }

    @Override
    public String toString() {
        return "Duration{" +
                "min=" + min +
                '}';
    }
}
