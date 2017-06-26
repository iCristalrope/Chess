package Model;

import Model.PieceClasses.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the class Board
 *
 * @author Olivier De Cannière
 */
public class BoardTest {

    /**
     * Test of move method, of class Board.
     */
    @Test
    public void testMove() {
        Coordinates origin = new Coordinates(0, 1);
        Coordinates destination = new Coordinates(0, 2);
        Board instance = new Board(true);
        instance.putPiece(new King(Color.BLACK), origin);
        instance.move(origin, destination);
        System.out.println(instance);
        assertTrue(new King(Color.BLACK).equals(instance.getPiece(destination)));
    }

    ///////////////////////////////////////////////////////
    /**
     * Test of putPiece method, of class Board.
     */
    @Test
    public void testPutPiece_casGeneral() {
        System.out.println("putPiece");
        Board board = new Board(true);
        Piece piece = new Knight(Color.WHITE);
        Coordinates coord = new Coordinates(1, 5);
        board.putPiece(piece, coord);
        assertTrue(piece.equals(board.getPiece(coord)));
    }

    /**
     * Test of putPiece method, of class Board.
     */
    @Test
    public void testPutPiece_00() {
        System.out.println("putPiece00");
        Board board = new Board(true);
        Piece piece = new King(Color.BLACK);
        Coordinates coord = new Coordinates(0, 0);
        board.putPiece(piece, coord);
        assertTrue(piece.equals(board.getPiece(coord)));
    }

    /**
     * Test of putPiece method, of class Board.
     */
    @Test
    public void testPutPiece_MaxMax() {
        System.out.println("testPutPiece_MaxMax");
        Board board = new Board(true);
        Piece piece = new Knight(Color.WHITE);
        Coordinates coord = new Coordinates(Board.MAX_ROWS - 1, Board.MAX_COLUMNS - 1);
        board.putPiece(piece, coord);
        assertTrue(piece.equals(board.getPiece(coord)));
    }

    /**
     * Test of putPiece method, of class Board.
     */
    @Test(expected = GameException.class)
    public void testPutPiece_pieceNull() {
        System.out.println("testPutPiece_pieceNull");
        Board board = new Board(true);
        Piece piece = null;
        Coordinates coord = new Coordinates(5, 0);
        board.putPiece(piece, coord);
    }

    /**
     * Test of putPiece method, of class Board.
     */
    @Test(expected = GameException.class)
    public void testPutPiece_coordNull() {
        System.out.println("testPutPiece_coordNull");
        Board board = new Board(true);
        Piece piece = new Knight(Color.WHITE);
        Coordinates coord = null;
        board.putPiece(piece, coord);
    }

    /**
     * Test of putPiece method, of class Board.
     */
    @Test(expected = GameException.class)
    public void testPutPiece_notOnBoard() {
        System.out.println("testPutPiece_notOnBoard");
        Board board = new Board(true);
        Piece piece = new Knight(Color.WHITE);
        Coordinates coord = new Coordinates(-1, 0);
        board.putPiece(piece, coord);
    }

    /**
     * Test of putPiece method, of class Board.
     */
    @Test(expected = GameException.class)
    public void testPutPiece_notFree() {
        System.out.println("testPutPiece_notFree");
        Board board = new Board(true);
        board.putPiece(new King(Color.BLACK), new Coordinates(2, 2));
        Piece piece = new Knight(Color.WHITE);
        Coordinates coord = new Coordinates(2, 2);
        board.putPiece(piece, coord);
    }

    ///////////////////////////////////////////////////////
    /**
     * Test of getPiece method, of class Board.
     */
    @Test
    public void testGetPiece_casGeneral() {
        System.out.println("testGetPiece_casGeneral");
        Coordinates coord = new Coordinates(3, 3);
        Board instance = new Board(true);
        Piece piece = new Knight(Color.BLACK);
        instance.putPiece(piece, coord);
        Piece expResult = null;
        Piece result = instance.getPiece(coord);
        assertEquals(piece, instance.getPiece(coord));
    }

    /**
     * Test of getPiece method, of class Board.
     */
    @Test
    public void testGetPiece_MaxMax() {
        System.out.println("testGetPiece_MaxMax");
        Coordinates coord = new Coordinates(Board.MAX_ROWS - 1, Board.MAX_COLUMNS - 1);
        Board instance = new Board(true);
        Piece piece = new Knight(Color.BLACK);
        instance.putPiece(piece, coord);
        Piece expResult = null;
        Piece result = instance.getPiece(coord);
        assertEquals(piece, instance.getPiece(coord));
    }

    /**
     * Test of getPiece method, of class Board.
     */
    @Test(expected = GameException.class)
    public void testGetPiece_coordNull() {
        System.out.println("testGetPiece_coordNull");
        Coordinates coord = null;
        Board instance = new Board(true);
        Piece piece = new Knight(Color.BLACK);
        instance.putPiece(piece, new Coordinates(7, 6));
        Piece expResult = null;
        Piece result = instance.getPiece(coord);
        assertEquals(piece, instance.getPiece(coord));
    }

    /**
     * Test of getPiece method, of class Board.
     */
    @Test(expected = GameException.class)
    public void testGetPiece_notOnBoard() {
        System.out.println("testGetPiece_notOnBoard");
        Coordinates coord = new Coordinates(5, 80);
        Board instance = new Board(true);
        Piece piece = new Knight(Color.BLACK);
        instance.putPiece(piece, new Coordinates(7, 6));
        Piece expResult = null;
        Piece result = instance.getPiece(coord);
        assertEquals(piece, instance.getPiece(coord));
    }

    ///////////////////////////////////////////////////////
    /**
     * Test of putPiece method, of class Board.
     */
    @Test
    public void testRemovePiece_casGeneral() {
        System.out.println("testRemovePiece_casGeneral");
        Coordinates coord = new Coordinates(1, 1);
        Board instance = new Board(true);
        Piece piece = new King(Color.WHITE);
        instance.putPiece(piece, coord);
        Piece expResult = piece;
        Piece result = instance.removePiece(coord);
        assertEquals(expResult, result);
    }

    /**
     * Test of putPiece method, of class Board.
     */
    @Test(expected = GameException.class)
    public void testRemovePiece_coordNull() {
        System.out.println("testRemovePiece_coordNull");
        Coordinates coord = null;
        Board instance = new Board(true);
        Piece piece = new King(Color.WHITE);
        instance.putPiece(piece, coord);
        Piece expResult = piece;
        Piece result = instance.removePiece(coord);
        assertEquals(expResult, result);
    }

    /**
     * Test of putPiece method, of class Board.
     */
    @Test(expected = GameException.class)
    public void testRemovePiece_noPieceToRemove() {
        System.out.println("testRemovePiece_noPieceToRemove");
        Coordinates coord = new Coordinates(0, 0);
        Board instance = new Board(true);
        Piece piece = new King(Color.WHITE);
        Piece expResult = piece;
        Piece result = instance.removePiece(coord);
        assertEquals(expResult, result);
    }

    ///////////////////////////////////////////////////////
    /**
     * Test of isOnBoard method, of class Board.
     */
    @Test
    public void testIsOnBoard_casGeneral() {
        System.out.println("testIsOnBoard_casGeneral");
        Coordinates coord = new Coordinates(2, 3);
        boolean expResult = true;
        boolean result = Board.isOnBoard(coord);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOnBoard method, of class Board.
     */
    @Test
    public void testIsOnBoard_00() {
        System.out.println("testIsOnBoard_00");
        Coordinates coord = new Coordinates(0, 0);
        boolean expResult = true;
        boolean result = Board.isOnBoard(coord);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOnBoard method, of class Board.
     */
    @Test
    public void testIsOnBoard_MaxMax() {
        System.out.println("testIsOnBoard_MaxMax");
        Coordinates coord = new Coordinates(Board.MAX_ROWS - 1, Board.MAX_COLUMNS - 1);
        boolean expResult = true;
        boolean result = Board.isOnBoard(coord);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOnBoard method, of class Board.
     */
    @Test
    public void testIsOnBoard_neg() {
        System.out.println("testIsOnBoard_neg");
        Coordinates coord = new Coordinates(-8, Board.MAX_COLUMNS - 1);
        boolean expResult = false;
        boolean result = Board.isOnBoard(coord);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOnBoard method, of class Board.
     */
    @Test
    public void testIsOnBoard_tropGrand() {
        System.out.println("testIsOnBoard_neg");
        Coordinates coord = new Coordinates(1000, 6);
        boolean expResult = false;
        boolean result = Board.isOnBoard(coord);
        assertEquals(expResult, result);
    }

    /////////////////////////////////////////////////////////////////
    /**
     * Test of isAttackable method, of class Board.
     */
    @Test
    public void testIsAttackable_otherColor() {
        System.out.println("testIsAttackable_otherColor");
        Coordinates coord = new Coordinates(0, 0);
        Color color = Color.BLACK;
        Piece piece = new Knight(Color.WHITE);
        Board instance = new Board(true);
        instance.putPiece(piece, coord);
        boolean expResult = true;
        boolean result = instance.isAttackable(coord, color);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAttackable method, of class Board.
     */
    @Test
    public void testIsAttackable_sameColor() {
        System.out.println("testIsAttackable_sameColor");
        Coordinates coord = new Coordinates(0, 0);
        Color color = Color.BLACK;
        Piece piece = new Knight(Color.BLACK);
        Board instance = new Board(true);
        instance.putPiece(piece, coord);
        boolean expResult = false;
        boolean result = instance.isAttackable(coord, color);
        assertEquals(expResult, result);
    }

}
