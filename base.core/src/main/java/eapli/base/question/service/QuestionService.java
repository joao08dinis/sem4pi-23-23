package eapli.base.question.service;

import eapli.base.exam.domain.Exam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.QuestionRepository;

import java.util.Optional;
import java.util.Set;

public class QuestionService {
    private final QuestionRepository repository = PersistenceContext.repositories().questions();

    public Question addNewQuestion(Question question){

        return repository.save(question);
    }

    public Set<Question> getAll(){
        return repository.getAll();
    }


}
