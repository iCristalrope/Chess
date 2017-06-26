package Model.PieceClasses;

import Model.*;

/**
 *
 * @author Philippe
 */
public class Pawn extends Piece implements Pieceable {

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
    public void update(Board board, Coordinates coord){
        
    }
}
