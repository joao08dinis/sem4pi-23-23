package eapli.base.sharedboard.domain;


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
public class Owner implements DomainEntity<Long> {

    public static Owner from (Long id, Permission permission,Profile ownerProfile, Set<Post> post) throws BusinessRuleException {
        try {
            Preconditions.nonNull(permission);
            Preconditions.nonNull(ownerProfile);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Owner(id,permission,ownerProfile,post);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Permission permission;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Profile ownerProfile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Post> post;



    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((Owner) other).getId();
    }


    @Override
    public Long identity() {
        return this.getId();
    }
}