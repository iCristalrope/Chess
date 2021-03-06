/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PawnTest {

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseEmptyWhite() {
        System.out.println("update_caseEmptyWhite");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Pawn instance = new Pawn(Color.WHITE);

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        acc.add(new Coordinates(2, 3));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseEmptyBlack() {
        System.out.println("update_caseEmptyBlack");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Pawn instance = new Pawn(Color.BLACK);

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        acc.add(new Coordinates(4, 3));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseOnlyAlliesWhite() {
        System.out.println("update_caseOnlyAlliesWhite");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Pawn instance = new Pawn(Color.WHITE);

        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 2));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 3));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 4));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseOnlyAlliesBlack() {
        System.out.println("update_caseOnlyAlliesBlack");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Pawn instance = new Pawn(Color.BLACK);

        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 2));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 3));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 4));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseOnlyEnemiesWhite() {
        System.out.println("update_caseOnlyEnemiesWhite");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Pawn instance = new Pawn(Color.WHITE);

        board.putPiece(new Knight(Color.BLACK), new Coordinates(2, 2));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(2, 3));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(2, 4));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        cap.add(new Coordinates(2, 4));
        cap.add(new Coordinates(2, 2));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseOnlyEnemiesBlack() {
        System.out.println("update_caseOnlyEnemiesBlack");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Pawn instance = new Pawn(Color.BLACK);

        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 2));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 3));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 4));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        cap.add(new Coordinates(4, 4));
        cap.add(new Coordinates(4, 2));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseAlliesEnemiesNullWhite() {
        System.out.println("update_caseAlliesEnemiesNullWhite");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Pawn instance = new Pawn(Color.WHITE);

        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 2));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(2, 4));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        acc.add(new Coordinates(2, 3));
        cap.add(new Coordinates(2, 4));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseAlliesEnemiesNullBlack() {
        System.out.println("update_caseAlliesEnemiesNullBlack");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Pawn instance = new Pawn(Color.BLACK);

        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 2));
        board.putPiece(new Knight(Color.BLACK), new Coordinates(4, 4));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        acc.add(new Coordinates(4, 3));
        cap.add(new Coordinates(4, 2));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseUpBordWhite() {
        System.out.println("update_caseUpBordWhite");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(0, 0);
        Pawn instance = new Pawn(Color.WHITE);

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseUpBordBlack() {
        System.out.println("update_caseUpBordBlack");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(7, 0);
        Pawn instance = new Pawn(Color.BLACK);

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseSideBordWhite() {
        System.out.println("update_caseSideBordWhite");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 0);
        Pawn instance = new Pawn(Color.WHITE);

        board.putPiece(new Knight(Color.BLACK), new Coordinates(2, 1));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        acc.add(new Coordinates(2, 0));
        cap.add(new Coordinates(2, 1));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Pawn.
     */
    @Test
    public void testUpdate_caseSideBordBlack() {
        System.out.println("update_caseSideBordBlack");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 0);
        Pawn instance = new Pawn(Color.BLACK);

        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 1));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        acc.add(new Coordinates(4, 0));
        cap.add(new Coordinates(4, 1));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }
}
