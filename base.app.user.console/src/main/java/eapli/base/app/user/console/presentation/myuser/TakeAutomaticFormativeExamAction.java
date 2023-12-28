package eapli.base.app.user.console.presentation.myuser;

import eapli.framework.actions.Action;

public class TakeAutomaticFormativeExamAction implements Action {
    @Override
    public boolean execute() {
        return new TakeAutomaticFormativeExamUI().show();
    }
}
