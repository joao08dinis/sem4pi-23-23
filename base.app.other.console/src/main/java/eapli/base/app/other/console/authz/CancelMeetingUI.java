package eapli.base.app.other.console.authz;

import eapli.base.meeting.controller.MeetingController;
import eapli.base.meeting.domain.Meeting;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;
import java.util.Scanner;

public class CancelMeetingUI extends AbstractUI {

    MeetingController theController=new MeetingController();
    private final ProfileController profileController = new ProfileController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {

        TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

        List<Meeting> meetings=theController.getMeetingsByProfile(currentUserProfile);
        int option = -1;

        if(meetings.isEmpty()){
            System.out.println("Does not exist any meeting created!");
            return true;
        }
        System.out.println("Which meeting do you want to cancel?");
        boolean isValidOption = false;
        do {
            try {
                for (int i = 0; i <  meetings.size(); i++) {
                        System.out.printf("Meeting [%d] -> Id:%d Title:%s Meetings State:%s\n", i + 1, meetings.get(i).getId(), meetings.get(i).getTitle(), meetings.get(i).getMeetingState());
                }
                option = Console.readInteger("Option: ");
                System.out.println();
                if (option>0 || option <meetings.size()){
                    isValidOption=true;
                }else{
                    System.out.println("Invalid Option!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the meeting: " + e.getMessage());
            }
        }while (!isValidOption);

        Meeting meeting = meetings.get(option-1);
        try {
            this.theController.CancelMeeting(meeting);
        } catch (Exception e) {
            throw new RuntimeException("Error changing the enrollments state of the course.");
        }

        System.out.printf("The state of the meeting %s is now %s\n",meeting.getTitle(),meeting.getMeetingState());

        return true;
    }

    @Override
    public String headline() {
        return "Cancel Meeting";
    }
}
