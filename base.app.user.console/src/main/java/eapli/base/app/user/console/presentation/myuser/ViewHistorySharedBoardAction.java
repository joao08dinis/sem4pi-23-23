package eapli.base.app.user.console.presentation.myuser;

import eapli.framework.actions.Action;

public class ViewHistorySharedBoardAction implements Action {
    @Override
    public boolean execute() {
        return new ViewHistorySharedBoardUI().doShow();
    }
}
