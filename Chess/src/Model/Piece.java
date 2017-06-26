package Model;

import java.util.List;

/**
 * Class representing the concept of a piece and containing methods that apply to all of them
 *
 * @author Olivier De Cannière
 */
public abstract class Piece {

    protected Color color;
    protected List<Coordinates> accessible;
    protected List<Coordinates> captureable;

    /**
     * Getter for the color attribute
     *
     * @return color
     */
    public Color getColor() {
        return this.color;
    }
}
