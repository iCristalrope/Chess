package Model;

/**
 * Class representing the board of the game
 *
 * @author Olivier De Cannière
 */
public class Board {

    public static final int MAX_ROWS = 8;
    public static final int MAX_COLUMNS = 8;

    Pieceable[][] pieces;

    public Board() {
        pieces = new Pieceable[MAX_ROWS][MAX_COLUMNS];
    }

    /**
     * Moves a piece on the board according to the rules and takes the piece at the destination if necessary
     *
     * @param board the board containing the pieces
     * @param origin the tile on which the moving piece starts
     * @param destination the tile on which the moving piece ends
     */
    public void move(Board board, Coordinates origin, Coordinates destination) {

    }

    /**
     * Returns a piece at a position on the board
     *
     * @param coord the position of the piece to return
     * @return the piece at coord
     */
    public Pieceable getPiece(Coordinates coord) {
        return pieces[coord.getRow()][coord.getColumn()];
    }
}
