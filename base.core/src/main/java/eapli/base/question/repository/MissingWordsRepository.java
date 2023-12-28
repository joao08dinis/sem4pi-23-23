package eapli.base.question.repository;

import eapli.base.question.domain.MissingWords;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface MissingWordsRepository extends DomainRepository<Long,MissingWords> {

    public List<MissingWords> getAll();
}
