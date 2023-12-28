package eapli.base.app.user.console.presentation.myuser;

import eapli.framework.actions.Action;

import javax.swing.*;

public class ChangePostSharedBoardAction implements Action {

    @Override
    public boolean execute() {
        return new ChangePostSharedBoardUI().doShow();
    }
}
