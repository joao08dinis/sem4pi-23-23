package eapli.base.app.other.console.authz;

import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.base.sharedboard.service.SharedBoardService;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class BoardMenuUi extends AbstractUI {

    public SharedBoardController controller = new SharedBoardController();

    public SharedBoard getSharedBoard() {
        List<SharedBoard> boards = controller.getAll();
        if (boards.isEmpty()) {
            System.out.println("This user does not have Shared Boards.");
            return null;
        } else {
            System.out.println("Which Board do you want to host?");
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<html><head><title>Choose Shared Board</title></head><body>");
            htmlBuilder.append("<h1>Choose a Shared Board</h1>");

            for (SharedBoard board : boards) {
                htmlBuilder.append("<form action=\"/start\" method=\"post\">");
                htmlBuilder.append("<input type=\"hidden\" name=\"boardId\" value=\"").append(board.getId()).append("\" />");
                htmlBuilder.append("<input type=\"submit\" value=\"").append(board.getShrdtitle()).append("\" />");
                htmlBuilder.append("</form>");
            }

            htmlBuilder.append("</body></html>");

            System.out.println(htmlBuilder);

            // Render the HTML to the user or send it to a web browser

            return null; // You may need to modify the return value based on your requirements
        }
    }


    @Override
    protected boolean doShow() {
        getSharedBoard();
        return false;
    }

    @Override
    public String headline() {
        return null;
    }
}
