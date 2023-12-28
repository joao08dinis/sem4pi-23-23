package eapli.base.meeting.domain;

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

public class MeetingTime implements ValueObject {

    private int meeting_hour;
    private int meeting_minutes;

    public static MeetingTime from (int meeting_hour, int min) throws BusinessRuleException {
        try {
            Preconditions.ensure((meeting_hour <= 24 && meeting_hour > 0), "Invalid hour.");
            Preconditions.ensure(min > 0 && min<=60, "Invalid minutes.");
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new MeetingTime(meeting_hour, min);
    }

}
