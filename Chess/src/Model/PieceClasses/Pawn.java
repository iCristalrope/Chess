package Model.PieceClasses;

import Model.*;
import static Model.Board.*;

/**
 *
 * @author Philippe
 */
public class Pawn extends Piece implements Pieceable {

    private final static int[][] DIRECTION_UPDATE = {{1, 0}, {1, 1}, {1, -1}};

    /**
     * Creates a Pawn of the specified color
     *
     * @param color the color of the bishop
     */
    public Pawn(Color color) {
        super(color);
    }

    /**
     * Updates the accessible and captureable lists of the this piece
     *
     * @param board the board containing the pieces
     * @param coord the coordinates of this piece
     */
    @Override
    public void update(Board board, Coordinates coord) {
        int multColor = this.color == Color.WHITE ? -1 : 1;
        int row, col, dirUpdt = 0;
        Coordinates coord2;

        for (int[] directionUpdate : DIRECTION_UPDATE) {
            row = coord.getRow() + multColor * directionUpdate[0];
            col = coord.getColumn() + directionUpdate[1];
            coord2 = new Coordinates(row, col);

            if (isOnBoard(coord2) && dirUpdt == 0) {
                accessible.add(coord2);
            } else if (board.isAttackable(coord2, color)) {
                captureable.add(coord2);
            }

            dirUpdt++;
        }
    }
}
