package eapli.base.course.domain;

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
public class Capacity implements ValueObject {

    private int minCapacity;
    private int maxCapacity;

    public static Capacity from (int minCapacity, int maxCapacity) throws BusinessRuleException {
        try {
            Preconditions.ensure((minCapacity < 0), "Invalid minimum capacity.");
            Preconditions.ensure((maxCapacity < 0), "Invalid maximum capacity.");
            Preconditions.ensure((minCapacity > maxCapacity), "Invalid data.");

        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Capacity(minCapacity, maxCapacity);
    }

    @Override
    public String toString() {
        return String.format("Minimum Capacity: %d\nMaximum Capacity: %d", minCapacity,maxCapacity);
    }
}

