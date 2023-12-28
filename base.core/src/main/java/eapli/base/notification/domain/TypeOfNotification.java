package eapli.base.notification.domain;


import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TypeOfNotification implements ValueObject {


        // TODO IMPROVE

        private String typeofnotification;

        public static TypeOfNotification from (String typeofnotification) throws BusinessRuleException {
            try {
                Preconditions.ensure(!Objects.equals(typeofnotification, " SharedBoardNotification") && !Objects.equals(typeofnotification, "MeetingNotification"),"Invalid state( SharedBoardNotification or MeetingNotification)");

            } catch (Exception e) {
                throw new BusinessRuleException(e);
            }

            return new TypeOfNotification(typeofnotification);
        }

        @Override
        public String toString() {
            return "CellState{" +
                    "state=" + typeofnotification +
                    '}';
        }
}
