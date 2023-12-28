package eapli.base.profile.domain;


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
public class DateOfBirth implements ValueObject {


    // TODO IMPROVE

    private int day;
    private int month;
    private int year;

    public static DateOfBirth from (int day, int month, int year) throws BusinessRuleException {
        try {
            Preconditions.ensure((month >= 1 && month <= 12), "Invalid month.");
            Preconditions.ensure(year > 0, "Invalid year.");

            int maxDays = Month.of(month).length(Year.isLeap(year));
            Preconditions.ensure((day >= 1 && day <= maxDays), "Invalid day.");


        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new DateOfBirth(day, month, year);
    }

    @Override
    public String toString() {
        return day +"/"+month+"/"+year;
    }
}
