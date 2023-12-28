package eapli.base.persistence.impl.inmemory;

import eapli.base.course.domain.Course;
import eapli.base.meeting.domain.Meeting;
import eapli.base.meeting.repository.MeetingRepository;
import eapli.base.profile.domain.Profile;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryMeetingRepository extends InMemoryDomainRepository<Meeting,Long> implements MeetingRepository {

    public InMemoryMeetingRepository(){


    }

    public InMemoryMeetingRepository(Function<? super Meeting, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public <S extends Meeting> S save(S entity) {
        return null;
    }

    @Override
    public void delete(Meeting entity) {

    }

    @Override
    public List<Meeting> getAllMeetings() {
        Iterable<Meeting> iterable = this.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
