package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class ScheduleMeetingAction implements Action {
    @Override
    public boolean execute() {
        return new ScheduleMeetingUI().doShow();
    }

}
