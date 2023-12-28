package eapli.base.question.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.question.domain.Numerical;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.NumericalRepository;
import eapli.base.question.repository.ShortAnswerRepository;

import java.util.List;

public class NumericalService {
    private final NumericalRepository repository = PersistenceContext.repositories().numericals();
    public List<Numerical> getAll(){
        return repository.getAll();
    }
}
