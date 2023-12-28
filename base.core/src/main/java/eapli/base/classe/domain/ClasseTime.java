package eapli.base.classe.domain;

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

public class ClasseTime implements ValueObject {

    private int classe_hour;
    private int classe_minutes;

    public static ClasseTime from (int classe_hour, int classe_minutes) throws BusinessRuleException {
        try {
            Preconditions.ensure((classe_hour <= 24 && classe_hour> 0), "Invalid hour.");
            Preconditions.ensure( classe_minutes> 0 && classe_minutes<=60, "Invalid minutes.");
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new ClasseTime(classe_hour, classe_minutes);
    }

}
