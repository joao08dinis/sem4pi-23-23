package eapli.base.app.other.console.authz;


import eapli.framework.actions.Action;

public class CreateSharedBoardAction implements Action {
    @Override
    public boolean execute() {
        return new CreateSharedBoardUI().doShow();
    }
}