package eapli.base.exam.domain;

import eapli.base.course.domain.Course;
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

public class Exam implements AggregateRoot<Long>  {

    public static Exam from (Long id, Title title, TeacherProfile teacher, Set<StudentProfile> students, Course course, Set<Section> sections, OpenDate openDate, CloseDate closeDate, String path) throws BusinessRuleException {
        try {
            Preconditions.nonNull(title);
            Preconditions.nonNull(teacher);
            Preconditions.nonNull(course);
            Preconditions.nonEmpty(Collections.singleton(sections));
            Preconditions.nonNull(closeDate);
            Preconditions.nonNull(openDate);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Exam(id, title, teacher, students, course, sections, openDate, closeDate,path);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Title title;

    @OneToOne
    private TeacherProfile teacher;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<StudentProfile> students;

    @OneToOne
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Section> sections;

    @Embedded
    private OpenDate openDate;

    @Embedded
    private CloseDate closeDate;

    private String path;


    @Override
    public boolean sameAs(Object other) {
        return this.getId().equals(((Exam) other).getId());
    }

    @Override
    public Long identity() {
        return this.getId();
    }


    public void verify() {
        for (Section section : sections) {
            section.verify();
        }
    }
}