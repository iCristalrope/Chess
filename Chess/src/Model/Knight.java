package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the knight piece
 *
 * @author Olivier De Cannière
 */
public class Knight implements Pieceable {

    Color color;

    /**
     * Creates a knight of the specified color
     *
     * @param color the color of the knight
     */
    public Knight(Color color) {
        this.color = color;
    }

    /**
     * Returns the list of tiles accessible by an attack of a piece of this type sitting at that position
     *
     * @param board the board containing the pieces
     * @param coord the tile on which the piece sits
     * @return the list of coordinates attackable by the piece
     */
    @Override
    public List<Coordinates> accessible(Board board, Coordinates coord) {
        ArrayList<Coordinates> accessible = new ArrayList<>();
        Coordinates coord2;

        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() - 2, coord.getColumn() + 1))
                && board.getPiece(coord2) == null) {  //-2 +1  , analogie horloge : 1h
            accessible.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() - 1, coord.getColumn() + 2))
                && board.getPiece(coord2) == null) {  //-1 +2  , analogie horloge : 2h
            accessible.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() + 1, coord.getColumn() + 2))
                && board.getPiece(coord2) == null) {  //+1 +2  , analogie horloge : 4h
            accessible.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() + 2, coord.getColumn() + 1))
                && board.getPiece(coord2) == null) {  //+2 +1  , analogie horloge : 5h
            accessible.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() + 2, coord.getColumn() - 1))
                && board.getPiece(coord2) == null) {  //+2 -1  , analogie horloge : 7h
            accessible.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() + 1, coord.getColumn() - 2))
                && board.getPiece(coord2) == null) {  //+1 -2  , analogie horloge : 8h
            accessible.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() - 1, coord.getColumn() - 2))
                && board.getPiece(coord2) == null) {  //-1 -2  , analogie horloge : 10h
            accessible.add(coord2);
        }
        if (Board.isOnBoard(coord2 = new Coordinates(coord.getRow() - 2, coord.getColumn() - 1))
                && board.getPiece(coord2) == null) {  //-2 -1  , analogie horloge : 11h
            accessible.add(coord2);
        }

        return accessible;
    }

    /**
     * Returns the list of tiles reachable by simple displacement of a piece of this type at that position
     *
     * @param board the board containing the pieces
     * @param coord the tile on which the piece sits
     * @return the list of coordinates reachable by the piece
     */
    @Override
    public List<Coordinates> captureable(Board board, Coordinates coord) {
        ArrayList<Coordinates> captureable = new ArrayList<>();
        Coordinates coord2;

        if (isAttackable(board, coord2 = new Coordinates(coord.getRow() - 2, coord.getColumn() + 1))) {  //-2 +1  , analogie horloge : 1h
            captureable.add(coord2);
        }
        if (isAttackable(board, coord2 = new Coordinates(coord.getRow() - 1, coord.getColumn() + 2))) {  //-1 +2  , analogie horloge : 2h
            captureable.add(coord2);
        }
        if (isAttackable(board, coord2 = new Coordinates(coord.getRow() + 1, coord.getColumn() + 2))) {  //+1 +2  , analogie horloge : 4h
            captureable.add(coord2);
        }
        if (isAttackable(board, coord2 = new Coordinates(coord.getRow() + 2, coord.getColumn() + 1))) {  //+2 +1  , analogie horloge : 5h
            captureable.add(coord2);
        }
        if (isAttackable(board, coord2 = new Coordinates(coord.getRow() + 2, coord.getColumn() - 1))) {  //+2 -1  , analogie horloge : 7h
            captureable.add(coord2);
        }
        if (isAttackable(board, coord2 = new Coordinates(coord.getRow() + 1, coord.getColumn() - 2))) {  //+1 -2  , analogie horloge : 8h
            captureable.add(coord2);
        }
        if (isAttackable(board, coord2 = new Coordinates(coord.getRow() - 1, coord.getColumn() - 2))) {  //-1 -2  , analogie horloge : 10h
            captureable.add(coord2);
        }
        if (isAttackable(board, coord2 = new Coordinates(coord.getRow() - 2, coord.getColumn() - 1))) {  //-2 -1  , analogie horloge : 11h
            captureable.add(coord2);
        }

        return captureable;
    }

    /**
     * Returns the color of this piece
     *
     * @return color
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Checks if a position is attackable
     *
     * @param board the board containing the pieces
     * @param coord the tile to check
     * @return true if the piece at that position is of the other color
     */
    private boolean isAttackable(Board board, Coordinates coord) {
        return (Board.isOnBoard(coord) && board.getPiece(coord) != null
                && board.getPiece(coord).getColor() != this.color);
    }
}
