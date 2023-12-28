package eapli.base.profile.domain;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class TeacherProfile extends Profile implements DomainEntity<Long> {

    private String acronym;

    public TeacherProfile(Long id, SystemUser user, DateOfBirth dateOfBirth, TaxPayerNumber taxPayerNumber, String acronym){
        super(id, user, taxPayerNumber, dateOfBirth);
        this.acronym = acronym;

    }

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((Profile) other).getId();
    }

    @Override
    public Long identity() {
        return this.getId();
    }

    @Override
    public String toString() {
        return String.format("%s",getUser().name());
    }
}
