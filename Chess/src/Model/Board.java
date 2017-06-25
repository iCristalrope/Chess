package Model;

/**
 * Class representing the board of the game
 *
 * @author Olivier De Cannière
 */
public class Board {
    public static final int maxRows = 8;
    public static final int maxColumns = 8;

    Pieceable[][] pieces;

    public Board() {
        pieces = new Pieceable[maxRows][maxColumns];
    }
}

