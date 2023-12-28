package http_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import eapli.base.sharedboard.controller.SharedBoardController;
import eapli.base.sharedboard.controller.SharedBoardServerController;
import eapli.base.sharedboard.domain.*;
import eapli.base.sharedboard.service.SharedBoardService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;



/**
 *
 * @author ANDRE MOREIRA (asc@isep.ipp.pt)
 */
public class HttpServerAjaxVoting {
    //private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("eapli.base");

    static private final String BASE_FOLDER="C:\\Users\\pcped\\Desktop\\sem4pi-22-23-27\\base.app.sharedboard\\src\\main\\java\\http_server\\www";
    static private ServerSocket sock;
    private static SharedBoard sharedBoard;

    public static SharedBoardController controller = new SharedBoardController();
    public static void start(SharedBoard sharedBoard) throws Exception {
        Socket cliSock;
        HttpServerAjaxVoting.sharedBoard=sharedBoard;
	try { sock = new ServerSocket(5000); }
	catch(IOException ex) {
            System.out.println("Server failed to open local port " + 5000);
            System.exit(1);
            }
	while(true) {
            cliSock=sock.accept();
            HttpAjaxVotingRequest req=new HttpAjaxVotingRequest(cliSock, BASE_FOLDER);
            req.start();
            getSharedBoard();
            }
        }

    
    // DATA ACCESSED BY THREADS - LOCKING REQUIRED
    
    private static final int candidatesNumber = 4;
    private static final String[] candidateName = new String[candidatesNumber];
    private static final int[] candidateVotes = new int[candidatesNumber];
    private static SharedBoard updatedSharedBoard;
    
    private static synchronized void getSharedBoard() {
        updatedSharedBoard=controller.getBoardId(sharedBoard.getId());
    }

    public static synchronized String getTitleInHTML(){
        String textHtml="<h1>"+sharedBoard.getShrdtitle().getTitle()+"</h1>\n" +
                "  <h3>"+sharedBoard.getOwner().getOwnerProfile()+"</h3>";
        return textHtml;
    }
    public static synchronized String getSharedBoardInHTML() {

        System.out.println("updating view");
        //EntityManager entityManager = emf.createEntityManager();
        //EntityTransaction transaction = entityManager.getTransaction();

        //transaction.begin();
        SharedBoardController updatedController = new SharedBoardController();
        SharedBoard updatedSharedBoard = updatedController.getBoardId(sharedBoard.getId());
        //System.out.println("participants: " + sharedBoard.getParticipants());
        System.out.println("new title: " + updatedSharedBoard.getShrdtitle().getTitle());
        System.out.println("changes: " + updatedSharedBoard.getAuditLog());

        String textHtml = "<h1>" + updatedSharedBoard.getShrdtitle().getTitle() + "</h1>\n" +
                "  <h3>" + updatedSharedBoard.getOwner().getOwnerProfile() + "</h3>";

        textHtml += "<table>\n" +
                "    <thead>\n" +
                "      <tr>\n" +
                "        <th>ID:" + updatedSharedBoard.getId() + "</th>\n";

        List<SharedBoardColumns> columns = new ArrayList<>(updatedSharedBoard.getBoardConfig().getColumn());
        Collections.sort(columns, Comparator.comparing(SharedBoardColumns::getNum));
        List<SharedBoardRows> rows = new ArrayList<>(updatedSharedBoard.getBoardConfig().getRows());
        Collections.sort(rows, Comparator.comparing(SharedBoardRows::getNum));
        List<Cell> cells = new ArrayList<>(updatedSharedBoard.getCells());
        Collections.sort(cells, Comparator.comparing(Cell::getId));

        for (int i = 0; i < columns.size(); i++) {
            textHtml += "<th>" + columns.get(i).getColtitle().getTitle() + "</th>\n";
        }

        int x = 0;
        for (int j = 0; j < rows.size(); j++) {
            textHtml += "<tr><th>" + rows.get(j).getRowtSharedBoardTitle().getTitle() + "</th>";
            for (int i = 0; i < columns.size(); i++) {
                if (i == columns.size() - 1) {
                    if (cells.get(x).getPost() == null) {
                        textHtml += "<td></td></tr>\n";
                    } else {
                        Post post = cells.get(x).getPost();
                        if(post.getTypeOfPost()==TypeOfPost.TEXT) {
                            textHtml += "<td>" + post.getDescription().getDescription() + "</td></tr>\n";
                        }else{
                            textHtml += "<td><img src="+post.getDescription().getDescription()+"></td></tr>\n";
                        }
                    }
                } else {
                    if (cells.get(x).getPost() == null) {
                        textHtml += "<td></td>\n";
                    } else {
                        Post post = cells.get(x).getPost();
                        if(post.getTypeOfPost()==TypeOfPost.TEXT) {
                            textHtml += "<td>" + post.getDescription().getDescription() + "</td>\n";
                        }else{
                            textHtml += "<td><img src="+post.getDescription().getDescription()+"></td>\n";
                        }
                    }
                }
                x++;
            }
        }

        return textHtml;
    }


    public static synchronized void castVote(String i) {
        int cN;
        try { cN=Integer.parseInt(i); }
        catch(NumberFormatException ne) { return; }
        cN--;
        if(cN >= 0 && cN < candidatesNumber) candidateVotes[cN]++;
    }
    
    
    
}
