package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class CancelMeetingAction implements Action {
    @Override
    public boolean execute() {
        return new CancelMeetingUI().doShow();
    }

}
