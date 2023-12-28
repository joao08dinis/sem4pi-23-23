package eapli.base.meeting.service;

import eapli.base.meeting.domain.Meeting;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.meeting.repository.MeetingRepository;

import java.util.List;

public class MeetingService {
    private final MeetingRepository repository = PersistenceContext.repositories().meetings();

    public Meeting addNewMeeting(Meeting meeting){
        return repository.save(meeting);
    }

    public List<Meeting> getAllMeetings(){
        return repository.getAllMeetings();
    }
}
