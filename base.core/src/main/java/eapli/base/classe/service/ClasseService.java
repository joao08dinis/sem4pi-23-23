package eapli.base.classe.service;

import eapli.base.classe.domain.Classe;
import eapli.base.classe.repository.ClasseRepository;
import eapli.base.meeting.domain.Meeting;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.meeting.repository.MeetingRepository;

import java.util.List;

public class ClasseService {
    private final ClasseRepository repository = PersistenceContext.repositories().classe();
    public Classe addNewClass(Classe classe){
        return repository.save(classe);
    }

    public List<Classe> getAllClasses(){return repository.getAllClasses();}

}
