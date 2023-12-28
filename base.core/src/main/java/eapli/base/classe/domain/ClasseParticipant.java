package eapli.base.classe.domain;


import eapli.base.meeting.domain.MeetingParticipant;
import eapli.base.profile.domain.Profile;
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
public class ClasseParticipant implements DomainEntity<Long> {

    public static ClasseParticipant from (Long id, Profile participantProfile) throws BusinessRuleException {
        try {
            Preconditions.nonNull(participantProfile);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new ClasseParticipant(id,participantProfile);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Profile participantProfile;

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((MeetingParticipant) other).getId();
    }


    @Override
    public Long identity() {
        return this.getId();
    }
}
