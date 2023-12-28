package eapli.base.TeacherRegistration.domain;

import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.course.domain.*;
import eapli.base.exam.domain.Exam;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class TeacherRegistration implements AggregateRoot<Long> {

    public static TeacherRegistration from (Long id, TeacherProfile teacherProfile, Role role, Course course) throws BusinessRuleException {
        try {
            Preconditions.nonNull(teacherProfile);
            Preconditions.nonNull(role);
            Preconditions.nonNull(course);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new TeacherRegistration(id, teacherProfile, role,course);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TeacherProfile teacherProfile;

    private Role role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Course course;

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((TeacherRegistration) other).getId();
    }

    @Override
    public Long identity() {
        return this.getId();
    }

}
