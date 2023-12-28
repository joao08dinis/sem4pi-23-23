package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class ScheduleExtraordinaryAction implements Action {

    @Override
    public boolean execute() {
        return new ScheduleExtraordinaryUI().doShow();
    }
}
