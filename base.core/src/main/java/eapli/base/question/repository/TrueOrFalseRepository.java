package eapli.base.question.repository;

import eapli.base.question.domain.TrueOrFalse;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface TrueOrFalseRepository extends DomainRepository<Long, TrueOrFalse> {

    public List<TrueOrFalse> getAll();
}
