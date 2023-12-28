package eapli.base.meeting.domain;

import eapli.base.profile.domain.Profile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)

public class Meeting implements AggregateRoot<Long> {

       public static Meeting from (Long id, Duration duration, MeetingTime meetingTime, eapli.base.meeting.domain.Title title, Profile userProfile, Set<MeetingParticipant> meetingParticipants,MeetingState meetingState) throws BusinessRuleException {
        try {
            Preconditions.nonNull(duration);
            Preconditions.nonNull(meetingTime);
            Preconditions.nonNull(title);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }
        return new Meeting(id,duration, meetingTime,meetingState,title,userProfile,meetingParticipants);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Duration duration;

    private MeetingTime meetingTime;

    private MeetingState meetingState;

    @Column(unique = true)
    private Title title;
/*
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Notification notification;
*/
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile userProfile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MeetingParticipant> meetingParticipants;


    @Override
    public boolean sameAs(Object other) {
        return Objects.equals(this.getId(), ((Meeting) other).getId());
    }

    @Override
    public Long identity() {
        return this.getId();
    }


}
