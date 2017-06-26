package Model.PieceClasses;

import Model.*;
import static Model.Board.*;

/**
 *
 * @author Philippe
 */
public class Bishop extends Piece implements Pieceable {

    private final static int[][] DIRECTION_UPDATE = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};

    /**
     * Creates a bishop of the specified color
     *
     * @param color the color of the bishop
     */
    public Bishop(Color color) {
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
        accessible.clear();
        captureable.clear();
        
        Coordinates coord2;
        int row, col;
        for (int[] directionUpdate : DIRECTION_UPDATE) {
            row = coord.getRow();
            col = coord.getColumn();
            
            //haut droite
            while (col < MAX_COLUMNS && row < MAX_ROWS) {
                coord2 = new Coordinates(row, col);
                if (board.getPiece(coord2) != null) {
                    accessible.add(coord2);
                    break;
                }
            }
            
        }
    }
}
