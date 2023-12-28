package eapli.base.usermanagement.application;

import eapli.base.notification.domain.Notification;
import eapli.base.notification.domain.TypeOfNotification;
import eapli.base.sharedboard.domain.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangePostSharedBoardTest {
    private Set<Cell> cells = new HashSet<>();

    @Before
    public void setUp() {

    }

    @Test
    public void testPostInOverwrittenCell() throws Exception {
        cells.add(new Cell(null,CellState.OCCUPIED,new SharedBoardColumns(null,new SharedBoardTitle("SEM1"),1),new SharedBoardRows(null,new SharedBoardTitle("EAPLI"),1), new Post(null,new Notification(null, new TypeOfNotification("Shared Board")),new Description("Shared Board"),TypeOfPost.TEXT,null)));
        Cell cell = cells.iterator().next();
        Post post = new Post(null,new Notification(null, new TypeOfNotification("Shared Board")),new Description("new post"),TypeOfPost.TEXT,cell.getPost().getDescription().getDescription());
        assertEquals(cell.getPost().getDescription().getDescription(),post.getLastPost());
        cell.setPost(post);

        assertEquals(cell.getPost(),post);
    }

    @Test
    public void testPostImageInFreeCell() throws Exception {
        cells.add(new Cell(null,CellState.FREE,new SharedBoardColumns(null,new SharedBoardTitle("SEM1"),1),new SharedBoardRows(null,new SharedBoardTitle("LPROG"),2),new Post(null,new Notification(null, new TypeOfNotification("Shared Board")),new Description("Path"),TypeOfPost.IMAGE,null)));
        Cell cell = cells.iterator().next();
        Post post = new Post(null,new Notification(null, new TypeOfNotification("Shared Board")),new Description("new post"),TypeOfPost.TEXT,cell.getPost().getDescription().getDescription());
        assertEquals(cell.getPost().getDescription().getDescription(),post.getLastPost());
        cell.setPost(post);

        assertEquals(cell.getPost(),post);
    }

    @Test
    public void testChangePostTextInFreeCell() throws Exception {
        cells.add(new Cell(null,CellState.FREE,new SharedBoardColumns(null,new SharedBoardTitle("SEM1"),1),new SharedBoardRows(null,new SharedBoardTitle("EAPLI"),1), null));
        Cell cell = cells.iterator().next();
        assertEquals(cell.getCellState(),CellState.FREE);
    }

    @Test
    public void testChangePostImageInFreeCell() throws Exception {
        cells.add(new Cell(null,CellState.FREE,new SharedBoardColumns(null,new SharedBoardTitle("SEM1"),1),new SharedBoardRows(null,new SharedBoardTitle("EAPLI"),1), null));
        Cell cell = cells.iterator().next();
        assertEquals(cell.getCellState(),CellState.FREE);
    }
}
