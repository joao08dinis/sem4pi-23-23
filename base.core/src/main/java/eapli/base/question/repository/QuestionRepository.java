package eapli.base.question.repository;

import eapli.base.exam.domain.Exam;
import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;
import java.util.Set;

public interface QuestionRepository extends DomainRepository<Long, Question> {
    Optional<Question> findQuestionByExam(Exam exam);
    Set<Question> getAll();

}
