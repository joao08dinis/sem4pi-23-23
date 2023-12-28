package eapli.base.notification.domain;


import eapli.base.sharedboard.domain.TypeOfPost;
import eapli.framework.domain.model.AggregateRoot;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class Notification implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TypeOfNotification typeOfPost;

    @Override
    public boolean sameAs(Object other) {
        return Objects.equals(this.getId(), ((Notification) other).getId());
    }

    @Override
    public Long identity() {
        return this.getId();
    }

}
