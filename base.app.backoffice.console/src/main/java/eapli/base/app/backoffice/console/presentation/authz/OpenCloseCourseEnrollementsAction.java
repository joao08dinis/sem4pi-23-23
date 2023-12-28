package eapli.base.app.backoffice.console.presentation.authz;

import eapli.framework.actions.Action;

import javax.swing.*;

public class OpenCloseCourseEnrollementsAction implements Action {
    @Override
    public boolean execute() {
        return new OpenCloseCourseEnrollmentsUI().show();
    }
}
