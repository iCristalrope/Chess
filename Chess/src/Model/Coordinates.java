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

    public Coordinates(int row, String letter) {
        this.row = Board.MAX_ROWS - row;
        if (row > 7 || row < 0) {
            throw new GameException("row out of board");
        }
        letter = letter.toUpperCase().substring(0,1);
        int col = letter.charAt(0) - 65;
        if (col < 0 || col > 7){
            throw new GameException("column not on the board");
        }
        this.column = col;
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
