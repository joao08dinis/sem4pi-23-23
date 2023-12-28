package eapli.base.app.other.console.authz;

import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.domain.*;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class CreateSharedBoardUI extends AbstractUI {


    private final SharedBoardController controller = new SharedBoardController();
    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        SharedBoard sharedBoard;

        final String title = Console.readLine("Title of the SharedBoard");
        final int numRows = Console.readInteger("Number of rows");
        final int numColumns = Console.readInteger("Number of collums");

        Set<SharedBoardRows> rows = new LinkedHashSet<>();
        Set<Cell> cells = new LinkedHashSet<>();
        Set<SharedBoardColumns> collums = new LinkedHashSet<>();
        List<SharedBoardColumns> sharedBoardColumns=new ArrayList<>();
        List<SharedBoardRows> sharedBoardRows=new ArrayList<>();


        for (int i = 1; i <= numColumns; i++) {
            final String columnTitle = Console.readLine("Title of Column " + i);
            SharedBoardColumns column = new SharedBoardColumns(null, new SharedBoardTitle(columnTitle), i);
            sharedBoardColumns.add(column);
            collums.add(column);
        }

        for (int j = 1; j <= numRows; j++) {
            final String rowTitle = Console.readLine("Title of Row " + j );
            SharedBoardRows row = new SharedBoardRows(null, new SharedBoardTitle(rowTitle), j);
            sharedBoardRows.add(row);
            rows.add(row);
        }
        for (SharedBoardRows sharedBoardRow : sharedBoardRows) {
            for (SharedBoardColumns sharedBoardColumn : sharedBoardColumns) {
                cells.add(new Cell(null, CellState.FREE, sharedBoardColumn, sharedBoardRow, null));
            }
        }

        final String pathname = Console.readLine("Pathname");

        SharedBoardState sharedBoardState = null;
        boolean isValidOption = false;
        int input;
        do {
            System.out.println("State of a Shared Board");
            System.out.println("1. OPEN");
            System.out.println("2. ARCHIVED");

            try {
                input = Console.readInteger("Option: ");
                switch (input) {
                    case 1:
                        System.out.println("You selected OPEN.");
                        sharedBoardState = SharedBoardState.OPEN;
                        isValidOption = true;
                        break;
                    case 2:
                        System.out.println("You selected ARCHIVED.");
                        sharedBoardState = SharedBoardState.ARCHIVED;
                        isValidOption = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the Shared Board state: " + e.getMessage());
            }
        } while (!isValidOption);


        try {
            sharedBoard = this.controller.addSharedBoard(sharedBoardState, numRows, numColumns, rows, collums, pathname, title, cells);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating a SharedBoard.");
        }
        controller.saveSharedBoard(sharedBoard);

        System.out.println("SharedBoard created!");

        return true;
    }

    @Override
    public String headline() {
        return null;
    }
}
