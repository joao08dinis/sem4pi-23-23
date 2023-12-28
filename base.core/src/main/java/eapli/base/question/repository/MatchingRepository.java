package eapli.base.question.repository;

import eapli.base.question.domain.Matching;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface MatchingRepository extends DomainRepository<Long, Matching> {

    public List<Matching> getAll();
}
