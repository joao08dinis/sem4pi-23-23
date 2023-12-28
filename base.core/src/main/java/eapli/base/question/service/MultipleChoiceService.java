package eapli.base.question.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.question.domain.MultipleChoice;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.MultipleChoiceRepository;
import eapli.base.question.repository.ShortAnswerRepository;

import java.util.List;

public class MultipleChoiceService {
    private final MultipleChoiceRepository repository = PersistenceContext.repositories().multipleChoices();

    public List<MultipleChoice> getAll(){
        return repository.getAll();
    }
}
