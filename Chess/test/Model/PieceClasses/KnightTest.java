package Model.PieceClasses;

import Model.Board;
import Model.Color;
import Model.Coordinates;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Olivier De Cannière
 */
public class KnightTest {

    /**
     * Test of update method, of class King.
     */
    @Test
    public void testUpdate_caseEmpty() {
        System.out.println("testUpdate_caseEmpty");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(5, 5);
        Knight instance = new Knight(Color.WHITE);
        board.putPiece(instance, coord);
        instance.update(board, coord);

        ArrayList<Coordinates> acc = new ArrayList<>();
        ArrayList<Coordinates> cap = new ArrayList<>();
        acc.add(new Coordinates(3, 6));
        acc.add(new Coordinates(4, 7));
        acc.add(new Coordinates(6, 7));
        acc.add(new Coordinates(7, 6));
        acc.add(new Coordinates(7, 4));
        acc.add(new Coordinates(6, 3));
        acc.add(new Coordinates(4, 3));
        acc.add(new Coordinates(3, 4));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class King.
     */
    @Test
    public void testUpdate_caseCorner() {
        System.out.println("testUpdate_caseCorner");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(0, 0);
        Knight instance = new Knight(Color.WHITE);
        board.putPiece(instance, coord);
        instance.update(board, coord);

        ArrayList<Coordinates> acc = new ArrayList<>();
        ArrayList<Coordinates> cap = new ArrayList<>();
        acc.add(new Coordinates(1, 2));
        acc.add(new Coordinates(2, 1));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class King.
     */
    @Test
    public void testUpdate_caseOnlyEnemies() {
        System.out.println("testUpdate_caseOnlyEnemies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(5, 5);
        Knight instance = new Knight(Color.WHITE);
        board.putPiece(instance, coord);
        board.putPiece(new Knight(Color.BLACK), new Coordinates(3, 6));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 7));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(6, 7));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(7, 6));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(7, 4));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(6, 3));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 3));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(3, 4));
        instance.update(board, coord);

        ArrayList<Coordinates> acc = new ArrayList<>();
        ArrayList<Coordinates> cap = new ArrayList<>();
        cap.add(new Coordinates(3, 6));
        cap.add(new Coordinates(4, 7));
        cap.add(new Coordinates(6, 7));
        cap.add(new Coordinates(7, 6));
        cap.add(new Coordinates(7, 4));
        cap.add(new Coordinates(6, 3));
        cap.add(new Coordinates(4, 3));
        cap.add(new Coordinates(3, 4));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }
    
        /**
     * Test of update method, of class King.
     */
    @Test
    public void testUpdate_caseOnlyAllies() {
        System.out.println("testUpdate_caseOnlyAllies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(5, 5);
        Knight instance = new Knight(Color.BLACK);
        board.putPiece(instance, coord);
        board.putPiece(new Knight(Color.BLACK), new Coordinates(3, 6));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 7));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(6, 7));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(7, 6));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(7, 4));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(6, 3));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 3));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(3, 4));
        instance.update(board, coord);

        ArrayList<Coordinates> acc = new ArrayList<>();
        ArrayList<Coordinates> cap = new ArrayList<>();

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }
    
        /**
     * Test of update method, of class King.
     */
    @Test
    public void testUpdate_caseMixNullAlliesEnemies() {
        System.out.println("testUpdate_caseMixNullAlliesEnemies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(5, 5);
        Knight instance = new Knight(Color.WHITE);
        board.putPiece(instance, coord);
        board.putPiece(new Knight(Color.BLACK), new Coordinates(3, 6));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 7));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(6, 7));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(7, 6));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(7, 4));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(6, 3));
        instance.update(board, coord);

        ArrayList<Coordinates> acc = new ArrayList<>();
        ArrayList<Coordinates> cap = new ArrayList<>();

        acc.add(new Coordinates(4, 3));
        acc.add(new Coordinates(3, 4));

        cap.add(new Coordinates(3, 6));
        cap.add(new Coordinates(7, 6));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }
}
