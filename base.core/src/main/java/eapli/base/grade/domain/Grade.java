package eapli.base.grade.domain;

import eapli.base.course.domain.Course;
import eapli.base.exam.domain.*;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class Grade implements AggregateRoot<Long> {

    public static Grade from (Long id, Exam exam, int grade, TypeOfGrade typeOfGrade) throws BusinessRuleException {
        try {
            Preconditions.nonNull(exam);
            Preconditions.nonNull(grade);
            Preconditions.nonNull(typeOfGrade);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Grade(id,exam,grade,typeOfGrade);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Exam exam;

    private int grade;

    private TypeOfGrade typeOfGrade;

    @Override
    public boolean sameAs(Object other) {
        return this.getId().equals(((Exam) other).getId());
    }

    @Override
    public Long identity() {
        return this.getId();
    }
}
