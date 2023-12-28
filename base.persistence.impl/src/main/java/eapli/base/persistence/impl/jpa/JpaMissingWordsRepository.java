package eapli.base.persistence.impl.jpa;

import eapli.base.question.domain.MissingWords;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.MissingWordsRepository;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaMissingWordsRepository extends JpaAutoTxRepository<MissingWords, Long, Long> implements MissingWordsRepository {
    public JpaMissingWordsRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaMissingWordsRepository(String persistenceUnitName, Map properties, String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaMissingWordsRepository(TransactionalContext tx) {
        super(tx, "id");
    }

    @Override
    public List<MissingWords> getAll() {
        Iterable<MissingWords> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
