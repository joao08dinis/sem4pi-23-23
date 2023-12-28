package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class ScheduleClassAction implements Action {
    @Override
    public boolean execute() {
        return new ScheduleClassUI().doShow();
    }

}
