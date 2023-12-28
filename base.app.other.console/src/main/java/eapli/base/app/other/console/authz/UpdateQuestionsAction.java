package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class UpdateQuestionsAction implements Action {
    @Override
    public boolean execute() {
        return new UpdateFormativeQuestionsUI().doShow();
    }
}
