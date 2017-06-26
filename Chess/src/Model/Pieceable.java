package Model;

/**
 * The interface all the pieces of the game implement. It standardises the methods among the pieces
 *
 * @author Olivier De Cannière
 */
public interface Pieceable {

    /**
     * Updates the accessible and captureable lists of the this piece
     *
     * @param board the board containing the pieces
     * @param coord the coordinates of this piece
     */
    public void update(Board board, Coordinates coord);
}
