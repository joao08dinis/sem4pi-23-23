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
public class Title implements ValueObject {

    public String title;
    public static Title from(String title) throws BusinessRuleException {
        try {
            Preconditions.ensure((title==null), "Invalid Title.");

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