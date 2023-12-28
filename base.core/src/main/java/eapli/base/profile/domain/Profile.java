package eapli.base.profile.domain;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import lombok.*;

import javax.persistence.*;

@Entity
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Profile implements AggregateRoot<Long> {


    public Profile(Long id, SystemUser user, TaxPayerNumber taxPayerNumber, DateOfBirth dateOfBirth){
        this.id = id;
        this.user = user;
        this.taxPayerNumber = taxPayerNumber;
        this.dateOfBirth = dateOfBirth;
        this.role = getProfileRole();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SystemUser user;

    private TaxPayerNumber taxPayerNumber;

    private DateOfBirth dateOfBirth;

    private String role;

    public abstract boolean sameAs(Object other);

    public String getProfileRole(){
        return this.getUser().roleTypes().iterator().next().toString();
    }

}
