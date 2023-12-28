package eapli.base.classe.domain;

import eapli.base.meeting.domain.Duration;
import eapli.base.meeting.domain.MeetingParticipant;
import eapli.base.profile.domain.TeacherProfile;
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

public class Classe implements AggregateRoot<Long> {

       public static Classe from (Long id, Duration duration, ClasseTime classeTime,Class_Day classDay, eapli.base.classe.domain.Title title, TeacherProfile teacherProfile,Set<ClasseParticipant> classeParticipant) throws BusinessRuleException {
        try {
            Preconditions.nonNull(duration);
            Preconditions.nonNull(classeTime);
            Preconditions.nonNull(title);
            Preconditions.nonNull(classDay);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Classe(id,duration, classeTime,title,classDay,classeParticipant,teacherProfile);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Duration duration;

    private ClasseTime classeTime;

    @Column(unique = true)
    private Title title;

    @Column(unique = true)
    private Class_Day classDay;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ClasseParticipant> classParticipants;

    /*
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Notification notification;
*/
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TeacherProfile teacherProfile;

    @Override
    public boolean sameAs(Object other) {
        return Objects.equals(this.getId(), ((Classe) other).getId());
    }

    @Override
    public Long identity() {
        return this.getId();
    }

}
