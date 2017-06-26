package Controller;

import Model.Board;
import Model.Color;
import Model.Coordinates;
import Model.Piece;
import Model.PieceClasses.*;

/**
 * temporary class : Equivalent to class Zebras in DDZ
 *
 * @author Olivier De Cannière
 */
public class Chess {

    public static void main(String[] args) {
        Board board = new Board(true);
        Piece piece = new King(Color.WHITE);
        board.putPiece(piece, new Coordinates(0, 1));
        System.out.println(board.toString());
        board.move(new Coordinates(0, 1), new Coordinates(0, 2));
        System.out.println(board.toString());
    }
}
