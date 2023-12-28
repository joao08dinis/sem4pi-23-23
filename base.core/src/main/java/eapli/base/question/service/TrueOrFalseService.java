package eapli.base.question.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.domain.TrueOrFalse;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.base.question.repository.TrueOrFalseRepository;

import java.util.List;

public class TrueOrFalseService{
    private final TrueOrFalseRepository repository = PersistenceContext.repositories().trueOrFalses();

    public List<TrueOrFalse> getAll(){
        return repository.getAll();
    }
}
