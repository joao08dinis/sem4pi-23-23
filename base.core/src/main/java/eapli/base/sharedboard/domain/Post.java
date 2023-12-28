package eapli.base.sharedboard.domain;





import eapli.base.notification.domain.Notification;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class Post implements DomainEntity<Long> {


    public static Post from (Long id, Notification notification, Description description,TypeOfPost typeOfPost,String lastPost) throws BusinessRuleException {
        try {
            Preconditions.nonNull(notification);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Post(id, notification,description,typeOfPost,lastPost);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Notification notification;

    private Description description;

    private TypeOfPost typeOfPost;

    private String lastPost;

    public void setDescription(Description description) {
        this.description = description;
    }


    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((Post) other).getId();
    }


    @Override
    public Long identity() {
        return this.getId();
    }

    public void setTypeOfPost(TypeOfPost typeOfPost) {
        this.typeOfPost = typeOfPost;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public void setLastPost(String lastPost) {
        this.lastPost = lastPost;
    }
}

