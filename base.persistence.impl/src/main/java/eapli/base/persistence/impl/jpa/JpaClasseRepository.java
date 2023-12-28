package eapli.base.persistence.impl.jpa;

import eapli.base.classe.domain.Classe;
import eapli.base.classe.repository.ClasseRepository;
import eapli.base.meeting.domain.Meeting;
import eapli.base.meeting.repository.MeetingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JpaClasseRepository extends JpaAutoTxRepository<Classe, Long, Long> implements ClasseRepository {
    public JpaClasseRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaClasseRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaClasseRepository(TransactionalContext tx) {
        super(tx, "id");
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


