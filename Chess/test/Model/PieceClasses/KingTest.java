package Model.PieceClasses;

import Model.Board;
import Model.Color;
import Model.Coordinates;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the class King
 *
 * @author Olivier De Cannière
 */
public class KingTest {

    public KingTest() {
    }

    /**
     * Test of update method, of class King.
     */
    @Test
    public void testUpdate_casVide() {
        System.out.println("testUpdate_casVide");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(5, 5);
        King instance = new King(Color.WHITE);
        board.putPiece(instance, coord);
        instance.update(board, coord);

        ArrayList<Coordinates> acc = new ArrayList<>();
        ArrayList<Coordinates> cap = new ArrayList<>();
        acc.add(new Coordinates(4, 5));
        acc.add(new Coordinates(4, 6));
        acc.add(new Coordinates(5, 6));
        acc.add(new Coordinates(6, 6));
        acc.add(new Coordinates(6, 5));
        acc.add(new Coordinates(6, 4));
        acc.add(new Coordinates(5, 4));
        acc.add(new Coordinates(4, 4));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class King.
     */
    @Test
    public void testUpdate_casCoin() {
        System.out.println("testUpdate_casCoin");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(0, 0);
        King instance = new King(Color.WHITE);
        board.putPiece(instance, coord);
        instance.update(board, coord);

        ArrayList<Coordinates> acc = new ArrayList<>();
        ArrayList<Coordinates> cap = new ArrayList<>();
        acc.add(new Coordinates(0, 1));
        acc.add(new Coordinates(1, 1));
        acc.add(new Coordinates(1, 0));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class King.
     */
    @Test
    public void testUpdate_casQueEnemis() {
        System.out.println("testUpdate_casQueEnemis");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(5, 5);
        King instance = new King(Color.WHITE);
        board.putPiece(instance, coord);
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 5));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 6));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(5, 6));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(6, 6));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(6, 5));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(6, 4));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(5, 4));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 4));
        instance.update(board, coord);

        ArrayList<Coordinates> acc = new ArrayList<>();
        ArrayList<Coordinates> cap = new ArrayList<>();
        cap.add(new Coordinates(4, 5));
        cap.add(new Coordinates(4, 6));
        cap.add(new Coordinates(5, 6));
        cap.add(new Coordinates(6, 6));
        cap.add(new Coordinates(6, 5));
        cap.add(new Coordinates(6, 4));
        cap.add(new Coordinates(5, 4));
        cap.add(new Coordinates(4, 4));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class King.
     */
    @Test
    public void testUpdate_casQueAllies() {
        System.out.println("testUpdate_casQueAllies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(5, 5);
        King instance = new King(Color.WHITE);
        board.putPiece(instance, coord);
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 5));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 6));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(5, 6));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(6, 6));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(6, 5));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(6, 4));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(5, 4));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 4));
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
    public void testUpdate_casMixteNullAlliesEnemis() {
        System.out.println("testUpdate_casMixteNullAlliesEnemis");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(5, 5);
        King instance = new King(Color.WHITE);
        board.putPiece(instance, coord);
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 5));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 6));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(5, 6));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(6, 6));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(6, 5));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(6, 4));
        //board.putPiece(new Knight(Color.WHITE), new Coordinates(5, 4));
        //board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 4));
        instance.update(board, coord);

        ArrayList<Coordinates> acc = new ArrayList<>();
        ArrayList<Coordinates> cap = new ArrayList<>();

        acc.add(new Coordinates(5, 4));
        acc.add(new Coordinates(4, 4));

        cap.add(new Coordinates(4, 5));
        cap.add(new Coordinates(6, 6));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }
}
