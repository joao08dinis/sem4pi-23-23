package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class AddAutomaticFormativeAction implements Action {
    @Override
    public boolean execute() {
        try {
            return new AddAutomaticFormativeExamUI().doShow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
