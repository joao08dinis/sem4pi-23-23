package eapli.base.app.backoffice.console.presentation.authz.importer;

import eapli.framework.actions.Action;

public class ImportStudentsAction implements Action {
    @Override
    public boolean execute() {
        return new ImportStudentsUI().show();
    }
}
