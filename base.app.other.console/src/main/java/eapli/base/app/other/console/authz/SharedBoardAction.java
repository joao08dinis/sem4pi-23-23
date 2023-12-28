package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class SharedBoardAction implements Action {

    @Override
    public boolean execute() {
        return new ShareSharedBoardUi().doShow();
    }
}
