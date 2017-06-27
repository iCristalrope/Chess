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
public class RookTest {
    
    /**
     * Test of update method, of class Rook.
     */
    @Test
    public void testUpdate_caseEmpty() {
        System.out.println("update_caseEmpty");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Rook instance = new Rook(Color.WHITE);
        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();
        //up 
        acc.add(new Coordinates(2, 3));
        acc.add(new Coordinates(1, 3));
        acc.add(new Coordinates(0, 3));
        //right
        acc.add(new Coordinates(3, 4));
        acc.add(new Coordinates(3, 5));
        acc.add(new Coordinates(3, 6));
        acc.add(new Coordinates(3, 7));
        //down
        acc.add(new Coordinates(4, 3));
        acc.add(new Coordinates(5, 3));
        acc.add(new Coordinates(6, 3));
        acc.add(new Coordinates(7, 3));
        //left
        acc.add(new Coordinates(3, 2));
        acc.add(new Coordinates(3, 1));
        acc.add(new Coordinates(3, 0));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Rook.
     */
    @Test
    public void testUpdate_caseCorner() {
        System.out.println("update_caseCorner");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(0, 0);
        Rook instance = new Rook(Color.WHITE);
        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();
        //up
        //right
        acc.add(new Coordinates(0, 1));
        acc.add(new Coordinates(0, 2));
        acc.add(new Coordinates(0, 3));
        acc.add(new Coordinates(0, 4));
        acc.add(new Coordinates(0, 5));
        acc.add(new Coordinates(0, 6));
        acc.add(new Coordinates(0, 7));
        //down
        acc.add(new Coordinates(1, 0));
        acc.add(new Coordinates(2, 0));
        acc.add(new Coordinates(3, 0));
        acc.add(new Coordinates(4, 0));
        acc.add(new Coordinates(5, 0));
        acc.add(new Coordinates(6, 0));
        acc.add(new Coordinates(7, 0));
        //left

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Rook.
     */
    @Test
    public void testUpdate_caseOnlyAllies() {
        System.out.println("update_caseOnlyAllies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Rook instance = new Rook(Color.WHITE);

        //tiles around
        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 3));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(3, 4));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 3));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(3, 2));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }

    /**
     * Test of update method, of class Rook.
     */
    @Test
    public void testUpdate_caseOnlyEnemies() {
        System.out.println("update_caseOnlyEnemies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(3, 3);
        Rook instance = new Rook(Color.BLACK);

        //tiles around
        board.putPiece(new Knight(Color.WHITE), new Coordinates(2, 3));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(3, 4));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 3));
        board.putPiece(new Knight(Color.WHITE), new Coordinates(3, 2));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();
        cap.add(new Coordinates(2, 3));
        cap.add(new Coordinates(3, 4));
        cap.add(new Coordinates(4, 3));
        cap.add(new Coordinates(3, 2));

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }
    
    /**
     * Test of update method, of class Rook.
     */
    @Test
    public void testUpdate_caseMixteNullAlliesEnemies() {
        System.out.println("update_caseMixteNullAlliesEnemies");
        Board board = new Board(true);
        Coordinates coord = new Coordinates(4, 3);
        Rook instance = new Rook(Color.WHITE);

        //up
        board.putPiece(new Knight(Color.BLACK), new Coordinates(0, 3));
        //right
        //down
        board.putPiece(new Knight(Color.BLACK), new Coordinates(7, 3));
        //left
        board.putPiece(new Knight(Color.WHITE), new Coordinates(4, 0));

        instance.update(board, coord);

        List<Coordinates> acc = new ArrayList<>();
        List<Coordinates> cap = new ArrayList<>();
        //up
        acc.add(new Coordinates(3, 3));
        acc.add(new Coordinates(2, 3));
        acc.add(new Coordinates(1, 3));
        cap.add(new Coordinates(0, 3));
        //right
        acc.add(new Coordinates(4, 4));
        acc.add(new Coordinates(4, 5));
        acc.add(new Coordinates(4, 6));
        acc.add(new Coordinates(4, 7));
        //down
        acc.add(new Coordinates(5, 3));
        acc.add(new Coordinates(6, 3));
        cap.add(new Coordinates(7, 3));
        //left
        acc.add(new Coordinates(4, 2));
        acc.add(new Coordinates(4, 1));
        

        assertEquals(acc, instance.getAccessible());
        assertEquals(cap, instance.getCaptureable());
    }
    
}
