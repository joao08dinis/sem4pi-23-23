package eapli.base.persistence.impl.jpa;

import eapli.base.course.domain.Course;
import eapli.base.meeting.domain.Meeting;
import eapli.base.meeting.repository.MeetingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JpaMeetingRepository extends JpaAutoTxRepository<Meeting, Long, Long> implements MeetingRepository {
    public JpaMeetingRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaMeetingRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaMeetingRepository(TransactionalContext tx) {
        super(tx, "id");
    }


    @Override
    public List<Meeting> getAllMeetings() {
        Iterable<Meeting> iterable = this.findAll();
        Iterator<Meeting> iterator = iterable.iterator();

        List<Meeting> meetingList = new ArrayList<>();

        for (Iterator<Meeting> it = iterator; it.hasNext(); ) {
            Meeting meeting = it.next();
            meetingList.add(meeting);
        }
        return meetingList;
    }
}
