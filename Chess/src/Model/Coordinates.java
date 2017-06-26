package Model;

/**
 * Class representing objects that are coordinates
 *
 * @author Olivier De Cannière
 */
public class Coordinates {

    private final int row;
    private final int column;

    /**
     * Creates an object of type Coordinates and sets its attributes to the parameters received
     *
     * @param row value to assign to the row attribute
     * @param column value to assign to the column attribute
     */
    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * row getter
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * column getter
     *
     * @return column
     */
    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.row;
        hash = 97 * hash + this.column;
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
        final Coordinates other = (Coordinates) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }
}
