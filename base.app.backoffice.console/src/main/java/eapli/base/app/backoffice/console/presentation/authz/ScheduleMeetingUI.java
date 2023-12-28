package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.meeting.controller.MeetingController;
import eapli.base.meeting.domain.Meeting;
import eapli.base.meeting.domain.MeetingParticipant;
import eapli.base.meeting.domain.MeetingParticipantState;
import eapli.base.meeting.domain.MeetingState;
import eapli.base.notification.domain.Notification;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.AdminProfile;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.StudentProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ScheduleMeetingUI extends AbstractUI {

    Scanner sc = new Scanner(System.in);

    private final MeetingController theController = new MeetingController();
    private final ProfileController profileController = new ProfileController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        AdminProfile currentUserProfile = (AdminProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();
        Meeting addMeeting = null;
        int option = 0;
        final String title = Console.readLine("Title");
        final int duration = Console.readInteger("Duration: ");
        final String time = Console.readLine("Time(HH:MM)");

        List<Profile> students = profileController.getStudentProfiles();
        List<Profile> teachers = profileController.getTeacherProfiles();
        Set<MeetingParticipant> meetingParticipants = new HashSet<MeetingParticipant>();
        boolean isValidOption = false;

        for (Profile student : students) {
            System.out.println("ID-" + student.getId());
            System.out.println("Name-" + student.getUser().name().firstName());
            int input;
            System.out.println("Do you want to add this student to the meeting?");
            System.out.println("1. ACCEPT");
            System.out.println("2. DECLINE");
            MeetingParticipant meetingParticipant = new MeetingParticipant(null, MeetingParticipantState.PENDING,student);
            do {
                try {
                    input = Console.readInteger("Option: ");
                    switch (input) {
                        case 1:
                            System.out.println("You accepted the student.");
                            meetingParticipants.add(meetingParticipant);
                            isValidOption = true;
                            break;
                        case 2:
                            System.out.println("You declined the student.");
                            isValidOption = true;
                            break;
                        default:
                            System.out.println("Invalid option selected. Please try again.");
                    }

                } catch (Exception e) {
                    System.out.println("An error occurred on select the Status of the student: " + e.getMessage());
                }
            }while(!isValidOption);
        }

        isValidOption = false;

        for (Profile teacher : teachers) {
            System.out.println("ID-" + teacher.getId());
            System.out.println("Name-" + teacher.getUser().name().firstName());
            int input;
            System.out.println("Do you want to add this teacher to the meeting?");
            System.out.println("1. ACCEPT");
            System.out.println("2. DECLINE");
            MeetingParticipant meetingParticipant = new MeetingParticipant(null, MeetingParticipantState.PENDING,teacher);
            do {
                try {
                    input = Console.readInteger("Option: ");
                    switch (input) {
                        case 1:
                            System.out.println("You accepted the teacher.");
                            meetingParticipants.add(meetingParticipant);
                            isValidOption = true;
                            break;
                        case 2:
                            System.out.println("You declined the teacher.");
                            isValidOption = true;
                            break;
                        default:
                            System.out.println("Invalid option selected. Please try again.");
                    }

                } catch (Exception e) {
                    System.out.println("An error occurred on select the Status of the teacher: " + e.getMessage());
                }
            }while(!isValidOption);
        }
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        Notification notification = null;

        AdminProfile adminProfile = currentUserProfile;

        try {
            addMeeting = this.theController.addMeeting(duration, hours, minutes, title, adminProfile, meetingParticipants,MeetingState.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating a course.");
        }
        theController.saveMeeting(addMeeting);


        System.out.println("Meeting created!");
        /*List<Meeting> meetings= theController.getMeetings();

        for (int i = 0; i < meetings.size(); i++){
            System.out.println(meetings.get(i));
        }*/
        return true;
    }
    @Override
    public String headline() {
        return "Schedule Meeting";
    }


}
