package Model.PieceClasses;

import Model.*;
import static Model.Board.*;

/**
 * Class representing the rook
 *
 * @author Olivier De Cannière
 */
public class Rook extends Piece implements Pieceable {

    private final static int[][] DIRECTION_UPDATE = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * Creates a Rook of the specified color
     *
     * @param color the color of the knight
     */
    public Rook(Color color) {
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
        accessible.clear();
        captureable.clear();

        Coordinates coord2;

        for (int[] directionUpdate : DIRECTION_UPDATE) {
            coord2 = new Coordinates(coord.getRow() + directionUpdate[0], coord.getColumn() + directionUpdate[1]);

            while (isOnBoard(coord2)) {
                if (board.getPiece(coord2) == null) {
                    accessible.add(coord2);
                } else if (board.isAttackable(coord2, color)) {
                    captureable.add(coord2);
                    break;
                } else {
                    break;
                }
                coord2 = new Coordinates(coord2.getRow() + directionUpdate[0], coord2.getColumn() + directionUpdate[1]);
            }
        }
    }
}
