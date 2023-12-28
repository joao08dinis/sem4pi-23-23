package eapli.base.question.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.QuestionRepository;
import eapli.base.question.repository.ShortAnswerRepository;

import java.util.List;
import java.util.Set;

public class ShortAnswerService {
    private final ShortAnswerRepository repository = PersistenceContext.repositories().shortAnswers();

    public List<ShortAnswer> getAll(){
        return repository.getAll();
    }
}
