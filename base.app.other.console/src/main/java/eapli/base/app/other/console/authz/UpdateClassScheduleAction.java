package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class UpdateClassScheduleAction implements Action {

    @Override
    public boolean execute() {
        return new UpdateClassScheduleUi().doShow();
    }
}
