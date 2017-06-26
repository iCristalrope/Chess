package Model.PieceClasses;

import Model.Board;
import Model.Color;
import Model.Coordinates;
import Model.Piece;
import java.util.ArrayList;
import java.util.List;
import Model.Pieceable;

/**
 * Class representing the knight piece
 *
 * @author Olivier De Cannière
 */
public class Knight extends Piece implements Pieceable {

    /**
     * Creates a knight of the specified color
     *
     * @param color the color of the knight
     */
    public Knight(Color color) {
        this.color = color;
    }

    /**
     * Updates the accessible and captureable lists of the this piece
     *
     * @param board the board containing the pieces
     * @param coord the coordinates of this piece
     */
    @Override
    public void update(Board board, Coordinates coord) {
        this.accessible = accessible(board, coord);
        this.captureable = captureable(board, coord);
    }

    /**
     * Returns the list of tiles accessible by an attack of a piece of this type sitting at that position
     *
     * @param board the board containing the pieces
     * @param coord the tile on which the piece sits
     * @return the list of coordinates attackable by the piece
     */
    private List<Coordinates> accessible(Board board, Coordinates coord) {
        ArrayList<Coordinates> accessibles = new ArrayList<>();
        Coordinates coord2;

        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() - 2, coord.getColumn() + 1))
                && board.getPiece(coord2) == null) {  //-2 +1  , analogie horloge : 1h
            accessibles.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() - 1, coord.getColumn() + 2))
                && board.getPiece(coord2) == null) {  //-1 +2  , analogie horloge : 2h
            accessibles.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() + 1, coord.getColumn() + 2))
                && board.getPiece(coord2) == null) {  //+1 +2  , analogie horloge : 4h
            accessibles.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() + 2, coord.getColumn() + 1))
                && board.getPiece(coord2) == null) {  //+2 +1  , analogie horloge : 5h
            accessibles.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() + 2, coord.getColumn() - 1))
                && board.getPiece(coord2) == null) {  //+2 -1  , analogie horloge : 7h
            accessibles.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() + 1, coord.getColumn() - 2))
                && board.getPiece(coord2) == null) {  //+1 -2  , analogie horloge : 8h
            accessibles.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() - 1, coord.getColumn() - 2))
                && board.getPiece(coord2) == null) {  //-1 -2  , analogie horloge : 10h
            accessibles.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() - 2, coord.getColumn() - 1))
                && board.getPiece(coord2) == null) {  //-2 -1  , analogie horloge : 11h
            accessibles.add(coord2);
        }

        return accessibles;
    }

    /**
     * Returns the list of tiles reachable by simple displacement of a piece of this type at that position
     *
     * @param board the board containing the pieces
     * @param coord the tile on which the piece sits
     * @return the list of coordinates reachable by the piece
     */
    private List<Coordinates> captureable(Board board, Coordinates coord) {
        ArrayList<Coordinates> captureables = new ArrayList<>();
        Coordinates coord2;

        if (board.isAttackable(coord2 = new Coordinates(coord.getRow() - 2, coord.getColumn() + 1), this.color)) {  //-2 +1  , analogie horloge : 1h
            captureables.add(coord2);
        }
        if (board.isAttackable(coord2 = new Coordinates(coord.getRow() - 1, coord.getColumn() + 2), this.color)) {  //-1 +2  , analogie horloge : 2h
            captureables.add(coord2);
        }
        if (board.isAttackable(coord2 = new Coordinates(coord.getRow() + 1, coord.getColumn() + 2), this.color)) {  //+1 +2  , analogie horloge : 4h
            captureables.add(coord2);
        }
        if (board.isAttackable(coord2 = new Coordinates(coord.getRow() + 2, coord.getColumn() + 1), this.color)) {  //+2 +1  , analogie horloge : 5h
            captureables.add(coord2);
        }
        if (board.isAttackable(coord2 = new Coordinates(coord.getRow() + 2, coord.getColumn() - 1), this.color)) {  //+2 -1  , analogie horloge : 7h
            captureables.add(coord2);
        }
        if (board.isAttackable(coord2 = new Coordinates(coord.getRow() + 1, coord.getColumn() - 2), this.color)) {  //+1 -2  , analogie horloge : 8h
            captureables.add(coord2);
        }
        if (board.isAttackable(coord2 = new Coordinates(coord.getRow() - 1, coord.getColumn() - 2), this.color)) {  //-1 -2  , analogie horloge : 10h
            captureables.add(coord2);
        }
        if (board.isAttackable(coord2 = new Coordinates(coord.getRow() - 2, coord.getColumn() - 1), this.color)) {  //-2 -1  , analogie horloge : 11h
            captureables.add(coord2);
        }

        return captureables;
    }
}
