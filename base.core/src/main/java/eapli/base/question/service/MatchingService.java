package eapli.base.question.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.question.domain.Matching;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.MatchingRepository;
import eapli.base.question.repository.ShortAnswerRepository;

import java.util.List;

public class MatchingService{
    private final MatchingRepository repository = PersistenceContext.repositories().matchings();

    public List<Matching> getAll(){
        return repository.getAll();
    }
}
