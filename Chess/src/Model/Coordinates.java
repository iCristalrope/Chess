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

    public Coordinates(String letter, int column) {
        this.column = column;
        if (column > 8 || column < 1) {
            throw new GameException("Column out of board");
        }
        letter = letter.toUpperCase().substring(0,0);
        switch (letter) {
            case "A":
                this.row = 8;
                break;
            case "B":
                this.row = 7;
                break;
            case "C":
                this.row = 6;
                break;
            case "D":
                this.row = 5;
                break;
            case "E":
                this.row = 4;
                break;
            case "F":
                this.row = 3;
                break;
            case "G":
                this.row = 2;
                break;
            case "H":
                this.row = 1;
                break;
            default :
                throw new GameException("Rows out of the board");
        }
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

    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
