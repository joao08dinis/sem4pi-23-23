package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class ViewHistorySharedBoardAction implements Action {
    @Override
    public boolean execute() {
        return new ViewHistorySharedBoardUI().doShow();
    }
}
