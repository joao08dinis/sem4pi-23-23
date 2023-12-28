package eapli.base.app.backoffice.console.presentation.authz;

import eapli.framework.actions.Action;

public class CreateSharedBoardAction implements Action {
    @Override
    public boolean execute() {
        return new CreateSharedBoardUI().doShow();
    }
}
