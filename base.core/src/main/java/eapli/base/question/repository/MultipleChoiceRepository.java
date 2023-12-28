package eapli.base.question.repository;

import eapli.base.question.domain.MultipleChoice;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface MultipleChoiceRepository extends DomainRepository<Long, MultipleChoice> {

    public List<MultipleChoice> getAll();
}
