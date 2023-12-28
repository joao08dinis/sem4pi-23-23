package eapli.base.classe.repository;

import eapli.base.classe.domain.Classe;
import eapli.base.meeting.domain.Meeting;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface ClasseRepository extends DomainRepository<Long, Classe> {

    public List<Classe> getAllClasses();

}
