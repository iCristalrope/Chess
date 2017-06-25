package Model;

import java.util.List;

/**
 * The interface all the pieces of the game implement. It standardises the methods among the pieces
 *
 * @author Olivier De Cannière
 */
public interface Pieceable {
      
    /**
     * Returns the list of tiles accessible by an attack of a piece of this type sitting at that position
     *
     * @param board the board containing the pieces
     * @param coord the tile on which the piece sits
     * @return the list of coordinates attackable by the piece
     */
    public List<Coordinates> accessible(Board board, Coordinates coord);

    /**
     * Returns the list of tiles reachable by simple displacement of a piece of this type at that position
     *
     * @param board the board containing the pieces
     * @param coord the tile on which the piece sits
     * @return the list of coordinates reachable by the piece
     */
    public List<Coordinates> captureable(Board board, Coordinates coord);

    /**
     * Returns the color of this piece
     *
     * @return color
     */
    public Color getColor();
}
