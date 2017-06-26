package Model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the class Board
 * @author Olivier De Cannière
 */
public class BoardTest {

    /**
     * Test of move method, of class Board.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        Board board = null;
        Coordinates origin = null;
        Coordinates destination = null;
        Board instance = new Board();
        instance.move(board, origin, destination);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPiece method, of class Board.
     */
    @Test
    public void testGetPiece() {
        System.out.println("getPiece");
        Coordinates coord = null;
        Board instance = new Board();
        Piece expResult = null;
        Piece result = instance.getPiece(coord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOnBoard method, of class Board.
     */
    @Test
    public void testIsOnBoard() {
        System.out.println("isOnBoard");
        Coordinates coord = null;
        boolean expResult = false;
        boolean result = Board.isOnBoard(coord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAttackable method, of class Board.
     */
    @Test
    public void testIsAttackable() {
        System.out.println("isAttackable");
        Coordinates coord = null;
        Color color = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isAttackable(coord, color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
