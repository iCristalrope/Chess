package Controller;

import View.*;
import Model.*;
import Model.PieceClasses.*;

/**
 * temporary class : Equivalent to class Zebras in DDZ
 *
 * @author Olivier De Cannière
 */
public class Chess {

    public static void main(String[] args) {
        Board board = new Board();
        board.move(new Coordinates(1, 1), new Coordinates(2, 1));

        /* Piece piece = new King(Color.WHITE);
        board.putPiece(piece, new Coordinates(0, 1));
        System.out.println(board);
        board.move(new Coordinates(0, 1), new Coordinates(0, 2));
        System.out.println(board);
        board = new Board();
        System.out.println(board);*/
        ConsoleDisplayBoard.init();
        ConsoleDisplayBoard.draw(board);
    }
}
