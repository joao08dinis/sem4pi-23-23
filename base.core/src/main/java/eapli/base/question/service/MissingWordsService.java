package eapli.base.question.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.question.domain.MissingWords;
import eapli.base.question.domain.MultipleChoice;
import eapli.base.question.repository.MissingWordsRepository;
import eapli.base.question.repository.MultipleChoiceRepository;

import java.util.List;

public class MissingWordsService {
    private final MissingWordsRepository repository = PersistenceContext.repositories().missingWords();

    public List<MissingWords> getAll(){
        return repository.getAll();
    }
}
