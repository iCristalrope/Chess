package Model;

import java.util.ArrayList;
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

    public Piece(Color color){
        this.accessible = new ArrayList<>();
        this.captureable = new ArrayList<>();
        this.color = color;
    }
    
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
    public String toString(){
        return this.color + " " + this.getClass().getSimpleName();
    }
}
