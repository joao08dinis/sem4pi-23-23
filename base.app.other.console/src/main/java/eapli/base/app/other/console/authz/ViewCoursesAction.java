package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class ViewCoursesAction implements Action {

    @Override
    public boolean execute() {
        return new ViewCoursesUi().doShow();
    }
}
