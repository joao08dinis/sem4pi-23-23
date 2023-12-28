package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class UndoPostItAction implements Action {
    @Override
    public boolean execute() {
            return new UndoPostItUI().doShow();
    }
}
