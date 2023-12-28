package eapli.base.persistence.impl.inmemory;

import eapli.base.question.domain.MissingWords;
import eapli.base.question.domain.Question;
import eapli.base.question.domain.ShortAnswer;
import eapli.base.question.repository.MissingWordsRepository;
import eapli.base.question.repository.ShortAnswerRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryMissingWordsRepository extends InMemoryDomainRepository<MissingWords, Long> implements MissingWordsRepository {

    public InMemoryMissingWordsRepository() {

    }
    public InMemoryMissingWordsRepository(Function<? super MissingWords, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public List<MissingWords> getAll() {
        Iterable<MissingWords> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
