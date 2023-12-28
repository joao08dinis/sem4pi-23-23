package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.StudentRegistration.controller.StudentRegistrationController;
import eapli.base.StudentRegistration.domain.Status;
import eapli.base.StudentRegistration.domain.StudentRegistration;
import eapli.base.StudentRegistration.service.StudentRegistrationService;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class AcceptUI extends AbstractUI {

    StudentRegistrationController studentRegistrationController=new StudentRegistrationController();


    @Override
    protected boolean doShow() {

        List<StudentRegistration> registrations=studentRegistrationController.getStudentRegistrations();
        Status status=Status.PENDING;

        for (StudentRegistration registration : registrations) {
            if (registration.getStatus() == status) {
                System.out.println("ID-" + registration.getId());
                System.out.println("Name-" + registration.getStudentProfile().getUser().name());
                System.out.println("Mecanographic-" + registration.getStudentProfile().getMecanographicNumber());
                System.out.println("Course-" + registration.getCourse());
                boolean isValidOption = false;
                int input;
                do {
                    System.out.println("Do you want to accept the student?");
                    System.out.println("1. ACCEPT");
                    System.out.println("2. DECLINE");

                    try {
                        input = Console.readInteger("Option: ");
                        switch (input) {
                            case 1:
                                System.out.println("You accepted the student.");
                                registration.setStatus(Status.ACCEPT);
                                isValidOption = true;
                                break;
                            case 2:
                                System.out.println("You declined the student.");
                                registration.setStatus(Status.DECLINE);
                                isValidOption = true;
                                break;
                            default:
                                System.out.println("Invalid option selected. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred on select the Status of the student: " + e.getMessage());
                    }
                } while (!isValidOption);

                try {
                    studentRegistrationController.saveStudentRegistration(registration);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }
    @Override
    public String headline() {
        return null;
    }
}
