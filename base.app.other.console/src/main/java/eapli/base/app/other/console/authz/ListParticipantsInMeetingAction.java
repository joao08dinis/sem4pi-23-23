package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class ListParticipantsInMeetingAction implements Action {
    @Override
    public boolean execute() {
        return new ListParticipantsInMeetingUI().doShow();
    }
}
