package eapli.base.grade.repository;

import eapli.base.grade.domain.Grade;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Set;

public interface GradeRepository extends DomainRepository<Long, Grade> {

    public Set<Grade> getAll();
}
