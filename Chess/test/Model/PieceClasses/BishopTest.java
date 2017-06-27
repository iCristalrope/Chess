package Model.PieceClasses;

import Model.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class BishopTest {

    /**
     * Test of update method, of class Bishop.
     */
    @Test
    public void testUpdate_caseEmpty() {
        System.out.println("update_caseEmpty");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Bishop instance = new Bishop(Color.WHITE);
        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();
        //up right
        acc.add(new Coordinates(2, 4));
        acc.add(new Coordinates(1, 5));
        acc.add(new Coordinates(0, 6));
        //down right
        acc.add(new Coordinates(4, 4));
        acc.add(new Coordinates(5, 5));
        acc.add(new Coordinates(6, 6));
        acc.add(new Coordinates(7, 7));
        //down left
        acc.add(new Coordinates(4, 2));
        acc.add(new Coordinates(5, 1));
        acc.add(new Coordinates(6, 0));
        //up left
        acc.add(new Coordinates(2, 2));
        acc.add(new Coordinates(1, 1));
        acc.add(new Coordinates(0, 0));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Bishop.
     */
    @Test
    public void testUpdate_caseCorner() {
        System.out.println("update_caseCorner");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(0, 0);
        Bishop instance = new Bishop(Color.WHITE);
        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();
        //up right
        //down right
        acc.add(new Coordinates(1, 1));
        acc.add(new Coordinates(2, 2));
        acc.add(new Coordinates(3, 3));
        acc.add(new Coordinates(4, 4));
        acc.add(new Coordinates(5, 5));
        acc.add(new Coordinates(6, 6));
        acc.add(new Coordinates(7, 7));
        //down left
        //up left

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Bishop.
     */
    @Test
    public void testUpdate_caseOnlyAllies() {
        System.out.println("update_caseOnlyAllies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Bishop instance = new Bishop(Color.WHITE);

        //tiles around
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 4));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 4));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 2));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 2));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Bishop.
     */
    @Test
    public void testUpdate_caseOnlyEnemies() {
        System.out.println("update_caseOnlyEnemies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Bishop instance = new Bishop(Color.BLACK);

        //tiles around
        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 4));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 4));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 2));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 2));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();
        cap.add(new Coordinates(2, 4));
        cap.add(new Coordinates(4, 4));
        cap.add(new Coordinates(4, 2));
        cap.add(new Coordinates(2, 2));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }
    
    /**
     * Test of update method, of class Bishop.
     */
    @Test
    public void testUpdate_caseMixteNullAlliesEnemies() {
        System.out.println("update_caseMixteNullAlliesEnemies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(4, 3);
        Bishop instance = new Bishop(Color.WHITE);

        //up right
        board.putPiece(new Knight(Color.BLACK), new Coordinates(0, 7));
        //down right
        //down left
        board.putPiece(new Knight(Color.BLACK), new Coordinates(6, 1));
        //up left
        board.putPiece(new Knight(Color.WHITE), new Coordinates(1, 0));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();
        //up right
        acc.add(new Coordinates(3, 4));
        acc.add(new Coordinates(2, 5));
        acc.add(new Coordinates(1, 6));
        cap.add(new Coordinates(0, 7));
        //down right
        acc.add(new Coordinates(5, 4));
        acc.add(new Coordinates(6, 5));
        acc.add(new Coordinates(7, 6));
        //down left
        acc.add(new Coordinates(5, 2));
        cap.add(new Coordinates(6, 1));
        //up left
        acc.add(new Coordinates(3, 2));
        acc.add(new Coordinates(2, 1));
        

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }
}
