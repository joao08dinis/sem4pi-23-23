package eapli.base.sharedboard.domain;


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
public class SharedBoardTitle implements ValueObject {



    public static SharedBoardTitle from (String title) throws BusinessRuleException {
        try {
            Preconditions.ensure(title==null, "Description is empty");


        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new SharedBoardTitle(title);
    }

    private String title;

    @Override
    public String toString() {
        return title;
    }
}
