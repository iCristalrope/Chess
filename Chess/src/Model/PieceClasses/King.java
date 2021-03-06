package Model.PieceClasses;

import Model.*;
import static Model.Board.*;

/**
 * Class representing the king piece
 *
 * @author Olivier De Cannière
 */
public class King extends Piece implements Pieceable {

    private static final int[][] DIRECTION_UPDATE = {
        {-1, +0}, {-1, +1}, {+0, +1}, {+1, +1}, {+1, +0}, {+1, -1}, {+0, -1}, {-1, -1}
    };
    private boolean hasMoved; //TODO pas sûr modifié à premier deplacement; idem Rook

    /**
     * Creates a King of the specified color
     *
     * @param color the color of the knight
     */
    public King(Color color) {
        super(color);
        this.hasMoved = false;
    }

    /**
     * Updates the accessible and captureable lists of the this piece
     *
     * @param board the board containing the pieces
     * @param coord the coordinates of this piece
     */
    @Override
    public void update(Board board, Coordinates coord) {
        Coordinates coord2;

        this.accessible.clear();
        this.captureable.clear();
        for (int[] directionUpdate : DIRECTION_UPDATE) {
            coord2 = new Coordinates(coord.getRow() + directionUpdate[0], coord.getColumn() + directionUpdate[1]);
            if (isOnBoard(coord2) && board.getPiece(coord2) == null) {
                accessible.add(coord2);
            } else if (board.isAttackable(coord2, this.color)) {
                captureable.add(coord2);
            }
        }
        if (board.canCastle(coord)) {
            accessible.add(coord); //TODO plutot 2 à droite de coord
        }
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    /**
     * Updates the King but doesn't check for castling to prevent infinite method calls
     *
     * @param board the board containing the pieces
     * @param coord the coordinates of this piece
     */
    public void simpleUpdate(Board board, Coordinates coord) {
        Coordinates coord2;

        this.accessible.clear();
        this.captureable.clear();
        for (int[] directionUpdate : DIRECTION_UPDATE) {
            coord2 = new Coordinates(coord.getRow() + directionUpdate[0], coord.getColumn() + directionUpdate[1]);
            if (isOnBoard(coord2) && board.getPiece(coord2) == null) {
                accessible.add(coord2);
            } else if (board.isAttackable(coord2, this.color)) {
                captureable.add(coord2);
            }
        }
    }
}
