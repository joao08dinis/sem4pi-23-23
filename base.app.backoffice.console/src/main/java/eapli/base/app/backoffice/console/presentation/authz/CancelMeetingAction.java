package eapli.base.app.backoffice.console.presentation.authz;

import eapli.framework.actions.Action;

public class CancelMeetingAction implements Action {
    @Override
    public boolean execute() {
        return new CancelMeetingUI().doShow();
    }

}
