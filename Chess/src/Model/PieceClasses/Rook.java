package Model.PieceClasses;

import Model.Board;
import Model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the rook
 *
 * @author Olivier De Cannière
 */
public class Rook extends Piece implements Pieceable {

    /**
     * Creates a rook of the specified color
     *
     * @param color the color of the rook
     */
    public Rook(Color color) {
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

    private List<Coordinates> accessible(Board board, Coordinates coord) {
        ArrayList<Coordinates> accessibles = new ArrayList<>();

        return accessibles;
    }

    private List<Coordinates> captureable(Board board, Coordinates coord) {
        ArrayList<Coordinates> captureables = new ArrayList<>();

        return captureables;
    }
}
