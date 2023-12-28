package eapli.base.meeting.domain;

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

public class Title implements ValueObject {
    private String title;

    public static Title from (String title) throws BusinessRuleException {
        try {
            Preconditions.ensure((title==null), "Invalid meeting duration.");
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
