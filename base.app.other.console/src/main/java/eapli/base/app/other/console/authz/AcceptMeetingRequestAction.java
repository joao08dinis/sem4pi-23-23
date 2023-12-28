package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class AcceptMeetingRequestAction implements Action {
    @Override
    public boolean execute() {
        try {
            return new AcceptMeetingRequestUI().doShow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
