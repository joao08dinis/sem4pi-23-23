package eapli.base.app.backoffice.console.presentation.authz.importer;

import java.io.FileNotFoundException;

public interface Importer {

    public void importFile (String file) throws FileNotFoundException;
}
