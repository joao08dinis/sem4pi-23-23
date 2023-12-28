package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class AddQuestionsAction implements Action {
    @Override
    public boolean execute() {
        return new AddFormativeQuestionsUI().doShow();
    }
}
