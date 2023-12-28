package eapli.base.app.other.console.authz;

import eapli.base.classe.controller.ClasseController;
import eapli.base.classe.domain.Classe;
import eapli.base.classe.domain.ClasseParticipant;
import eapli.base.course.domain.State;
import eapli.base.meeting.controller.MeetingController;
import eapli.base.meeting.domain.Meeting;
import eapli.base.meeting.domain.MeetingParticipant;
import eapli.base.notification.domain.Notification;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Set;

public class ScheduleClassUI extends AbstractUI {

    Scanner sc = new Scanner(System.in);

    private final ClasseController theController = new ClasseController();
    private final ProfileController profileController = new ProfileController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();
        Classe addClasse=null;

        final String title = Console.readLine("Title");
        final int duration = Console.readInteger("Duration: ");
        final String time = Console.readLine("Time(HH:MM)");
        final int weeks = Console.readInteger("Weeks that the class will be scheduled: ");

        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        Notification notification=null;

        TeacherProfile teacherProfile= currentUserProfile;
        List<Classe> classes= theController.getClasses();

        for (int i = 0; i < classes.size(); i++){
            int givenStartTime = hours * 60 + minutes;
            int givenEndTime = givenStartTime + duration;

            int classToCheckStartTime =classes.get(i).getClasseTime().getClasse_hour()* 60 + classes.get(i).getClasseTime().getClasse_minutes();
            int classToCheckEndTime = classToCheckStartTime + classes.get(i).getDuration().getMin();

            if(givenStartTime < classToCheckEndTime && classToCheckStartTime <= givenEndTime){
                System.out.println("Can't Schedule a class, the teacher already has one scheduled");
                return true;
            }

        }
        List<Profile> students = profileController.getStudentProfiles();
        Set<ClasseParticipant> classParticipants = new HashSet<ClasseParticipant>();
        boolean isValidOption = false;

        for (Profile student : students) {
            System.out.println("ID-" + student.getId());
            System.out.println("Name-" + student.getUser().name().firstName());
            int input;
            System.out.println("Do you want to add this student to the meeting?");
            System.out.println("1. ACCEPT");
            System.out.println("2. DECLINE");
            ClasseParticipant classeParticipant = new ClasseParticipant(null, student);
            do {
                try {
                    input = Console.readInteger("Option: ");
                    switch (input) {
                        case 1:
                            System.out.println("You accepted the student.");
                            classParticipants.add(classeParticipant);
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
        LocalDate today = LocalDate.now();
        int day=today.getDayOfMonth();
        int month=today.getMonthValue();
        try {
            addClasse = this.theController.addClass(duration, hours, minutes, title,month,day,teacherProfile,classParticipants);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating a Class.");
        }
        theController.saveMeeting(addClasse);
        for (int i=1;i<weeks;i++){
            LocalDate nextDate = today.plusWeeks(i);
            day = nextDate.getDayOfMonth();
            month = nextDate.getMonthValue();
            try {
               addClasse = this.theController.addClass(duration, hours, minutes, title,month,day,teacherProfile,classParticipants);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error creating a Class.");
            }
            theController.saveMeeting(addClasse);

        }



        System.out.println("Class created!");



        return true;

    }
    @Override
    public String headline() {
        return "Schedule Class";
    }


}
