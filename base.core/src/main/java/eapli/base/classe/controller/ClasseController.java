package eapli.base.classe.controller;

import eapli.base.classe.domain.*;
import eapli.base.classe.service.ClasseService;
import eapli.base.meeting.domain.Duration;
import eapli.base.meeting.domain.Meeting;
import eapli.base.meeting.domain.MeetingTime;
import eapli.base.meeting.service.MeetingService;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
import java.util.Set;

@UseCaseController
public class ClasseController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClasseService service = new ClasseService();


    public Classe addClass(int duration, int time_hour, int time_min, String title, int day_classe, int month_classe,
                           TeacherProfile teacherProfile, Set<ClasseParticipant> participants) throws Exception {
        return Classe.from(null,new Duration(duration), new ClasseTime(time_hour,time_min),new Class_Day(day_classe,month_classe),new Title(title),teacherProfile,participants);

    }

    public void saveMeeting(Classe classe){
        service.addNewClass(classe);
    }

    public List<Classe> getClasses() {
        return service.getAllClasses();
    }

}
