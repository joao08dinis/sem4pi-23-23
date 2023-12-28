package eapli.base.sharedboard.domain;

import eapli.base.profile.domain.Profile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Modification implements DomainEntity<Long> {

    public static Modification from(Long id, Date date, Profile author, Change change) throws BusinessRuleException {
        try {
            Preconditions.nonNull(date);
            Preconditions.nonNull(author);
            Preconditions.nonNull(change);

        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Modification(id,date,author,change);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @OneToOne
    private Profile author;

    private Change modification;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(date)+" : "+author.getUser().name().toString()+" has changed "+ modification;
    }

    @Override
    public boolean sameAs(Object other) {
        return this.getId().equals(((Modification) other).getId());
    }

    @Override
    public Long identity() {
        return this.getId();
    }
}
