package eapli.base.question.repository;

import eapli.base.question.domain.Numerical;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface NumericalRepository extends DomainRepository<Long, Numerical> {

    public List<Numerical> getAll();
}
