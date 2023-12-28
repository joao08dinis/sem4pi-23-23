package eapli.base.app.other.console.authz;

import eapli.framework.actions.Action;

import javax.swing.*;

public class CreateExamAction implements Action {

    @Override
    public boolean execute() {
        return new CreateExamUI().doShow();
    }



}
