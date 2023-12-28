package eapli.base.profile.domain;

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
public class TaxPayerNumber implements ValueObject {

    private String taxNumber;


    public static TaxPayerNumber from (String taxNumber) throws BusinessRuleException {
        try {
            Preconditions.nonNull(taxNumber);
            Preconditions.nonEmpty(taxNumber);
            // TODO Preconditions.ensure(taxNumber.matches(regex));
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new TaxPayerNumber(taxNumber);
    }

    @Override
    public String toString() {
        return taxNumber;
    }
}
