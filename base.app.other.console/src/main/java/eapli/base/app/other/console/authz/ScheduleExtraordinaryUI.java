package eapli.base.app.other.console.authz;

import eapli.base.classe.controller.ClasseController;
import eapli.base.classe.domain.Classe;
import eapli.base.notification.domain.Notification;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class ScheduleExtraordinaryUI extends AbstractUI {


    private final ClasseController theController = new ClasseController();

    private final ProfileController profileController = new ProfileController();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();


    @Override
    protected boolean doShow() {
        TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();
        Classe addClasse=null;

        System.out.println("Extraordinary class:");
        System.out.println();

        final String title = Console.readLine("Title:");
        final int duration = Console.readInteger("Duration(in minutes): ");
        final String time = Console.readLine("Time(HH:MM):");
        int hours;
        int minutes;
        do {
            String[] parts = time.split(":");
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);

        }while ((hours<0||hours>23)&&(minutes<0||minutes>59));

        Notification notification=null;

        TeacherProfile teacherProfile= currentUserProfile;
        List<Classe> classes= theController.getClasses();



        for (int i = 0; i < classes.size(); i++){
            int givenStartTime = hours * 60 + minutes;
            int givenEndTime = givenStartTime + duration;

            int classToCheckStartTime =classes.get(i).getClasseTime().getClasse_hour()* 60 + classes.get(i).getClasseTime().getClasse_minutes();
            int classToCheckEndTime = classToCheckStartTime + classes.get(i).getDuration().getMin();

            if(givenStartTime < classToCheckEndTime && classToCheckStartTime <= givenEndTime){
                System.out.println("Can't Schedule an extraordinary class, the teacher already has one class scheduled");
                return true;
            }

        }

        try {
            //addClasse = this.theController.addClass(duration, hours, minutes,title,0,0,teacherProfile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating an extraordinary Class.");
        }
        theController.saveMeeting(addClasse);


        System.out.println("Extraordinary class created!");







        return true;
    }

    @Override
    public String headline() {
        return null;
    }
}
