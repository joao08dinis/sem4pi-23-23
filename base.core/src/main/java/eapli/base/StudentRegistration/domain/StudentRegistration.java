package eapli.base.StudentRegistration.domain;

import eapli.base.TeacherRegistration.domain.Role;
import eapli.base.TeacherRegistration.domain.TeacherRegistration;
import eapli.base.course.domain.Course;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class StudentRegistration implements AggregateRoot<Long> {

    public static StudentRegistration from (Long id, StudentProfile studentProfile, Status status, Course course) throws BusinessRuleException {
        try {
            Preconditions.nonNull(studentProfile);
            Preconditions.nonNull(status);
            Preconditions.nonNull(course);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new StudentRegistration(id, studentProfile, status,course);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StudentProfile studentProfile;

    private Status status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Course course;

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((StudentRegistration) other).getId();
    }

    @Override
    public Long identity() {
        return this.getId();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
