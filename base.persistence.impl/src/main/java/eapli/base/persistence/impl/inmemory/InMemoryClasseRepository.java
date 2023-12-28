package eapli.base.persistence.impl.inmemory;

import eapli.base.classe.domain.Classe;
import eapli.base.classe.repository.ClasseRepository;
import eapli.base.meeting.domain.Meeting;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class InMemoryClasseRepository extends InMemoryDomainRepository<Classe,Long> implements ClasseRepository {

    public InMemoryClasseRepository(){


    }
    public InMemoryClasseRepository(Function<? super Classe, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public <S extends Classe> S save(S entity) {
        return null;
    }

    @Override
    public void delete(Classe entity) {

    }
    public List<Classe> getAllClasses() {
        Iterable<Classe> iterable = this.findAll();
        Iterator<Classe> iterator = iterable.iterator();

        List<Classe> classeList = new ArrayList<>();

        for (Iterator<Classe> it = iterator; it.hasNext(); ) {
            Classe classe = it.next();
            classeList.add(classe);
        }
        return classeList;
    }
}
