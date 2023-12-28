package eapli.base.meeting.repository;

import eapli.base.meeting.domain.Meeting;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface MeetingRepository extends DomainRepository<Long, Meeting> {

    public List<Meeting> getAllMeetings();
}
