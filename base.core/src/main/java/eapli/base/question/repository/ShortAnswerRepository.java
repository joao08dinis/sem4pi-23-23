package eapli.base.question.repository;

import eapli.base.question.domain.ShortAnswer;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface ShortAnswerRepository extends DomainRepository<Long, ShortAnswer> {

    public List<ShortAnswer> getAll();

}
