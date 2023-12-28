package eapli.base.meeting.controller;

import eapli.base.meeting.domain.*;
import eapli.base.meeting.service.MeetingService;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.Profile;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@UseCaseController
public class MeetingController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final MeetingService service = new MeetingService();

    ProfileController profileController = new ProfileController();


    public Meeting addMeeting(int duration, int time_hour,int time_min,String title, Profile userProfile,Set<MeetingParticipant> meetingParticipants,MeetingState meetingState) throws Exception {
        return Meeting.from(null,new Duration(duration), new MeetingTime(time_hour,time_min),new Title(title),userProfile, meetingParticipants,meetingState);

    }
    public void saveMeeting(Meeting meeting){
        service.addNewMeeting(meeting);
    }


    public void CancelMeeting(Meeting meeting){
        meeting.setMeetingState(MeetingState.CANCELED);
        service.addNewMeeting(meeting);
    }
    public List<Meeting> getMeetings() {
        List<Meeting> allMeetings= service.getAllMeetings();
        List<Meeting> acceptedMeetings=new ArrayList<>();
        for(int i=0;i<allMeetings.size();i++){
            if(allMeetings.get(i).getMeetingState().equals(MeetingState.ACCEPTED))
                acceptedMeetings.add(allMeetings.get(i));
        }
        return acceptedMeetings;
    }
    public List<Meeting> getMeetingsByProfile(Profile userProfile) {
        List<Meeting> allMeetings= service.getAllMeetings();
        List<Meeting> acceptedMeetings=new ArrayList<>();
        for(int i=0;i<allMeetings.size();i++){
            if((allMeetings.get(i).getMeetingState().equals(MeetingState.ACCEPTED))&&(allMeetings.get(i).getUserProfile().equals(userProfile)))
                acceptedMeetings.add(allMeetings.get(i));
        }
        return acceptedMeetings;
    }

    public Profile getAuthz() {
        return profileController.getUserProfile(authz.session().get().authenticatedUser()).get();
    }

    public List<MeetingParticipant> getParticipants(Meeting meeting){
        List<MeetingParticipant> participants = new ArrayList<>();
        Set<MeetingParticipant> meetingParticipants = meeting.getMeetingParticipants();
        for (MeetingParticipant meetingParticipant : meetingParticipants) {
            if (!meetingParticipant.getMeetingParticipantState().equals(MeetingParticipantState.PENDING)){
                participants.add(meetingParticipant);
            }
        }
        return participants;
    }

    public List<Meeting> getMeetingsByUser(Profile userProfile){
        List<Meeting> meetings = getMeetings();
        List<Meeting> meetingsByUser = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getUserProfile().equals(userProfile) || isParticipant(userProfile, meeting) && meeting.getMeetingState().equals(MeetingState.ACCEPTED)){
                meetingsByUser.add(meeting);
            }
        }
        return meetingsByUser;
    }

    public boolean isParticipant(Profile userProfile, Meeting meeting){
        boolean flag = false;
        for (MeetingParticipant meetingParticipant : meeting.getMeetingParticipants()){
            if (meetingParticipant.getParticipantProfile().equals(userProfile)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
