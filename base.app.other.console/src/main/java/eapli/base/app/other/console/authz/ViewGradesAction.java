package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class ViewGradesAction implements Action {
    @Override
    public boolean execute() {
        return new ViewGradesUi().doShow();
    }
}
