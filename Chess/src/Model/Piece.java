package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class representing the concept of a piece and containing methods that apply to all of them
 *
 * @author Olivier De Cannière
 */
public abstract class Piece {

    protected Color color;
    protected List<Coordinates> accessible;
    protected List<Coordinates> captureable;

    public Piece(Color color) {
        this.accessible = new ArrayList<>();
        this.captureable = new ArrayList<>();
        this.color = color;
    }

    /**
     * Updates the accessible and captureable lists
     *
     * @param board the board containing the pieces
     * @param coord the position of the piece to update
     */
    public abstract void update(Board board, Coordinates coord);

    /**
     * Getter for the color attribute
     *
     * @return color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Getter for the accessible attribute
     *
     * @return accessible
     */
    public List<Coordinates> getAccessible() {
        return accessible;
    }

    /**
     * Getter for the captureable attribute
     *
     * @return captureable
     */
    public List<Coordinates> getCaptureable() {
        return captureable;
    }

    @Override
    public String toString() {
        return this.color + " " + this.getClass().getSimpleName();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.color);
        hash = 79 * hash + Objects.hashCode(this.accessible);
        hash = 79 * hash + Objects.hashCode(this.captureable);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        if (this.color != other.color) {
            return false;
        }
        if (!Objects.equals(this.accessible, other.accessible)) {
            return false;
        }
        if (!Objects.equals(this.captureable, other.captureable)) {
            return false;
        }
        return true;
    }
}
