package eapli.base.meeting.domain;


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
public class MeetingParticipant implements DomainEntity<Long> {

    public static MeetingParticipant from (Long id, MeetingParticipantState meetingParticipantState,Profile participantProfile) throws BusinessRuleException {
        try {
            Preconditions.nonNull(meetingParticipantState);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new MeetingParticipant(id,meetingParticipantState,participantProfile);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private MeetingParticipantState meetingParticipantState;

    @OneToOne
    private Profile participantProfile;

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((MeetingParticipant) other).getId();
    }


    @Override
    public Long identity() {
        return this.getId();
    }

    public void setMeetingParticipantState(MeetingParticipantState meetingParticipantState) {
        this.meetingParticipantState = meetingParticipantState;
    }

    public Profile getParticipantProfile() {
        return participantProfile;
    }

}
