package eapli.base.app.other.console.authz;

import eapli.base.classe.controller.ClasseController;
import eapli.base.classe.domain.Classe;
import eapli.base.classe.domain.ClasseTime;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import javax.persistence.Entity;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateClassScheduleUi extends AbstractUI {

    Scanner sc = new Scanner(System.in);
    ClasseController controller = new ClasseController();

    private final AuthorizationService authz =
            AuthzRegistry.authorizationService();
    ProfileController profileController = new ProfileController();
    TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    @Override
    protected boolean doShow() {

        List<Classe> classeList = controller.getClasses();
        List<Classe> availableClasseList = new ArrayList<>();

        for (Classe classe : classeList){
            if (classe.getTeacherProfile().equals(currentUserProfile)){
                availableClasseList.add(classe);
            }
        }

        System.out.println("Select class to change: ");

        for (int i = 0; i < availableClasseList.size(); i++) {
            System.out.println(i+1 + ": " + availableClasseList.get(i));
        }

        int option = sc.nextInt();

        Classe selectedClasse = availableClasseList.get(option-1);


        final String time = Console.readLine("Select new Time(HH:MM)");

        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        ClasseTime newTime = null;
        try {
            newTime = ClasseTime.from(hours, minutes);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }

        selectedClasse.setClasseTime(newTime);
        controller.saveMeeting(selectedClasse);

        System.out.println("Class Schedule Updated!");


        return false;
    }

    @Override
    public String headline() {
        return null;
    }
}
