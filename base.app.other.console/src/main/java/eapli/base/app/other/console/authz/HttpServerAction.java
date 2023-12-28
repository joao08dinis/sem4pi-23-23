package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class HttpServerAction implements Action {
    @Override
    public boolean execute() {
        return new HttpServerUI().doShow();
    }
}
