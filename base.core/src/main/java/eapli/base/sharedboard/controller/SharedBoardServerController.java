package eapli.base.sharedboard.controller;

import eapli.base.sharedboard.domain.SharedBoard;
import eapli.base.sharedboard.service.SharedBoardService;

import java.util.List;

public class SharedBoardServerController {

    private final SharedBoardService service = new SharedBoardService();
    public List<SharedBoard> getAll() {
        return service.getAllSharedBoard();
    }
}
