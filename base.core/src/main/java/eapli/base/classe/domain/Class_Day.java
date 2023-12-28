package eapli.base.classe.domain;


import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class Class_Day {

    private int classe_day;
    private int classe_month;

    public static Class_Day from (int classe_day, int classe_month) throws BusinessRuleException {
        try {
            Preconditions.ensure((classe_day <= 31 && classe_day> 0), "Invalid day.");
            Preconditions.ensure( classe_month> 0 && classe_month<=12, "Invalid month.");
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Class_Day(classe_day, classe_month);
    }



}
