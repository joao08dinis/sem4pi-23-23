package eapli.base.app.other.console.authz;

import eapli.base.meeting.controller.MeetingController;
import eapli.base.meeting.domain.Meeting;
import eapli.base.meeting.domain.MeetingParticipant;
import eapli.base.meeting.domain.MeetingParticipantState;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;
import java.util.Scanner;

public class ListParticipantsInMeetingUI extends AbstractUI {

    private final MeetingController controller = new MeetingController();
    private final ProfileController profileController = new ProfileController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {

        int option;

        TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

        List<Meeting> meetingsByUser = controller.getMeetingsByUser(currentUserProfile);

        if(meetingsByUser.size() == 0) {
            System.out.println("You do not have a scheduled meeting!");
        }else {

            System.out.println("Please choose one of your meetings:");
            for (int i = 0; i < meetingsByUser.size(); i++) {
                System.out.printf("Meeting %d. : %s\n" , i + 1, meetingsByUser.get(i).getTitle().toString());
            }
            option = sc.nextInt();
            Meeting meeting = meetingsByUser.get(option - 1);

            List<MeetingParticipant> participantList = controller.getParticipants(meeting);

            if (participantList.size() == 0){
                System.out.println("Your meeting does not have any accepted or rejected participants!");
            }else {
                System.out.println("Your meeting has this participants:");
                for (MeetingParticipant meetingParticipant : participantList) {
                    if (!meetingParticipant.getMeetingParticipantState().equals(MeetingParticipantState.PENDING))
                        System.out.printf("%s with Status: %s\n", meetingParticipant.getParticipantProfile(), meetingParticipant.getMeetingParticipantState());
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "List Participants in My Meeting";
    }
}
