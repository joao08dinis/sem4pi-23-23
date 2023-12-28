package eapli.base.profile.domain;


import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.grade.domain.Grade;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class StudentProfile extends Profile implements DomainEntity<Long> {

    private MecanographicNumber mecanographicNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Grade> grades;

    public StudentProfile(Long id, SystemUser user, DateOfBirth dateOfBirth, TaxPayerNumber taxPayerNumber, MecanographicNumber mechanographicNumber) {
        super(id, user, taxPayerNumber, dateOfBirth);
        this.mecanographicNumber = mechanographicNumber;
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
        return "mecanographic number=" + mecanographicNumber + " name=" + getUser().name();
    }

    public void setGrades(Grade grade){
        grades.add(grade);
    }
}
