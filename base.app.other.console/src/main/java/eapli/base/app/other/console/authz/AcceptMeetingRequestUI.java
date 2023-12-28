package eapli.base.app.other.console.authz;

import eapli.base.meeting.controller.MeetingController;
import eapli.base.meeting.domain.Meeting;
import eapli.base.meeting.domain.MeetingParticipant;
import eapli.base.meeting.domain.MeetingParticipantState;
import eapli.base.meeting.domain.MeetingState;
import eapli.base.profile.domain.Profile;
import eapli.base.sharedboard.domain.SharedBoardState;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;
import java.util.Set;

public class AcceptMeetingRequestUI extends AbstractUI {

    MeetingController meetingController=new MeetingController();


    @Override
    protected boolean doShow() {

        List<Meeting>meetings=meetingController.getMeetings();
        Profile user=meetingController.getAuthz();

        for (Meeting meeting : meetings) {
            if (meeting.getMeetingState() == MeetingState.ACCEPTED) {
                Set<MeetingParticipant> meetingParticipants = meeting.getMeetingParticipants();
                for (MeetingParticipant meetingParticipant : meetingParticipants) {
                    Profile user1=meetingParticipant.getParticipantProfile();
                    if (user1.equals(user)) {
                        if (meetingParticipant.getMeetingParticipantState() == MeetingParticipantState.PENDING){
                            System.out.println("Meeting-" + meeting.getTitle());
                            MeetingParticipantState meetingParticipantState = null;
                            boolean isValidOption = false;
                            int input;
                            do {
                                System.out.println("Do you want to accept or reject the Meeting");
                                System.out.println("1. ACCEPT");
                                System.out.println("2. REJECT");
                                try {
                                    input = Console.readInteger("Option: ");
                                    switch (input) {
                                        case 1:
                                            System.out.println("You selected ACCEPT.");
                                            meetingParticipantState = MeetingParticipantState.ACCEPT;
                                            isValidOption = true;
                                            break;
                                        case 2:
                                            System.out.println("You selected REJECT.");
                                            meetingParticipantState = MeetingParticipantState.REJECT;
                                            isValidOption = true;
                                            break;
                                        default:
                                            System.out.println("Invalid option selected. Please try again.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("An error occurred on accepting or rejecting the meeting: " + e.getMessage());
                                }
                            } while (!isValidOption);
                            meetingParticipant.setMeetingParticipantState(meetingParticipantState);
                            meetingController.saveMeeting(meeting);
                            break;
                        } else {
                            System.out.println("You have already accepted the meeting"+meeting.getTitle());
                        }
                    }else{
                        System.out.println("You dont have this meeting"+meeting.getTitle());
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Accept or Reject meeting";
    }
}
