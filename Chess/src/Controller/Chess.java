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
        ConsoleDisplayBoard.init();
        Board board = new Board();

        ConsoleDisplayBoard.draw(board);
        Piece piece = board.getPiece(new Coordinates(1, 4));
        piece.update(board, new Coordinates(1, 4));
        System.out.println(piece.getAccessible());

        board.move(new Coordinates(1, 4), new Coordinates(2, 4));
        board.move(new Coordinates(0, 5), new Coordinates(3, 2));
        board.move(new Coordinates(0, 3), new Coordinates(4, 7));
        board.move(new Coordinates(4, 7), new Coordinates(6, 5)); //coup du berger lel

        ConsoleDisplayBoard.init();
        ConsoleDisplayBoard.draw(board);

        System.out.println("King at (0, 4) is in check : " + board.isKingInCheck(new Coordinates(0, 4)));
        System.out.println("King at (7, 4) is in check : " + board.isKingInCheck(new Coordinates(7, 4)));
        System.out.println("King at (7, 4) is in checkmate : " + board.isKingCheckMate(new Coordinates(7, 4)));
    }
}
