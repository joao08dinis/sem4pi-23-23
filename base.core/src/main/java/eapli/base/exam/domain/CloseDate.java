package eapli.base.exam.domain;


import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Month;
import java.time.Year;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CloseDate implements ValueObject {

    @Column
    private int CloseDay;
    @Column
    private int CloseMonth;
    @Column
    private int CloseYear;
    @Column
    private int CloseHour;
    @Column
    private int CloseMinute;

    public static CloseDate from (int day, int month, int year, int hour, int minute) throws BusinessRuleException {

        try {
            Preconditions.ensure((month >= 1 && month <= 12), "Invalid month.");
            Preconditions.ensure(year > 0, "Invalid year.");
            Preconditions.ensure(hour > 0 && hour < 24, "Invalid hours.");
            Preconditions.ensure(minute > 0 && minute < 60, "Invalid minutes.");

            int maxDays = Month.of(month).length(Year.isLeap(year));
            Preconditions.ensure((day >= 1 && day <= maxDays), "Invalid day.");

        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new CloseDate(day, month, year, hour, minute);
    }

    @Override
    public String toString() {
        return CloseDay +"/"+ CloseMonth +"/"+ CloseYear +" "+ CloseHour +":"+ CloseMinute;
    }
}
