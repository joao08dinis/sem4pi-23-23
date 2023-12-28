package eapli.base.sharedboard.domain;


import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.profile.domain.Profile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class Participant implements DomainEntity<Long> {

    public static Participant from (Long id, Profile profile, Permission permission, Set<Post> post) throws BusinessRuleException {
        try {
            Preconditions.nonNull(permission);
            Preconditions.nonNull(profile);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Participant(id, permission, profile, post);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Permission permission;

    @OneToOne
    public Profile profile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Post> post;

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((Participant) other).getId();
    }


    @Override
    public Long identity() {
        return this.getId();
    }
}
