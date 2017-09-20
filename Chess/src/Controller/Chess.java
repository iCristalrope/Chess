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

        Coordinates coord = new Coordinates(4, 4);
        board.putPiece(new King(Color.WHITE), new Coordinates(4, 4));
        board.putPiece(new Rook(Color.WHITE), new Coordinates(4, 7));
        ConsoleDisplayBoard.draw(board);
        board.getPiece(coord).update(board, coord);
        System.out.println(board.getPiece(coord).getAccessible());
        System.out.println(board.getPiece(coord).getCaptureable());
        System.out.println(board.canCastle(coord));
    }
}
