package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

public class ArchiveBoardAction implements Action {

    @Override
    public boolean execute() {
        return new ArchiveBoardUi().show();
    }
}
