package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

import javax.persistence.Table;

public class BoardMenuAction implements Action {

    @Override
    public boolean execute() {
        return new BoardMenuUi().doShow();
    }

}
