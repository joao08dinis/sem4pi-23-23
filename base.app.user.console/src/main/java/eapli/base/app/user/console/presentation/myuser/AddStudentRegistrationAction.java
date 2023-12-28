package eapli.base.app.user.console.presentation.myuser;

import eapli.framework.actions.Action;

public class AddStudentRegistrationAction implements Action {

    @Override
    public boolean execute() {
        return new AddStudentRegistrationUI().doShow();
    }
}
