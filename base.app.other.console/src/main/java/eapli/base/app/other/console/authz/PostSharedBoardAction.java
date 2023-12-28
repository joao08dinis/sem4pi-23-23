package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class PostSharedBoardAction  implements Action {
    @Override
    public boolean execute() {
        return new PostSharedBoardUI().doShow();
    }

}
