package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.Profile;
import eapli.framework.actions.Action;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Pedro Campos 1211511
 */


public class ListProfilesAction implements Action {

    Scanner sc = new Scanner(System.in);

    ProfileController profileController = new ProfileController();

    @Override
    public boolean execute() {

        return show();
    }

    public boolean show(){
        System.out.println("Select the role to list: ");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Admin");
        System.out.println("0. Exit");
        int input = -1;

        do {
            input = sc.nextInt();

        } while (input > 3 || input < 0);

        List<Profile> profileList = new ArrayList<>();


        if (input == 1){
            profileList = profileController.getStudentProfiles();
        } else if (input == 2){
            profileList = profileController.getTeacherProfiles();
        } else  if (input == 3){
            profileList = profileController.getAdminProfiles();
        }



        if (input != 0){
            for (int i = 0; i < profileList.size(); i++){
                System.out.println(profileList.get(i));
            }
        }

        return true;
    }

}
