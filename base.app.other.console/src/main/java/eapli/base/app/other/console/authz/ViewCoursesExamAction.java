package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class ViewCoursesExamAction implements Action {

    @Override
    public boolean execute() {
        return new ViewCoursesExamsUi().doShow();
    }
}
