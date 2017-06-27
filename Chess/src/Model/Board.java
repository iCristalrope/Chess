package Model;

import Model.PieceClasses.*;
import java.util.List;

/**
 * Class representing the board of the game
 *
 * @author Olivier De Cannière
 */
public class Board {

    public static final int MAX_ROWS = 8;
    public static final int MAX_COLUMNS = 8;

    Piece[][] pieces;

    public Board() {
        this(false);
    }

    public Board(boolean isEmpty) {
        pieces = new Piece[MAX_ROWS][MAX_COLUMNS];
        if (!isEmpty) {
            init();
        }
    }

    private final void init() {
        placePieces(Color.BLACK, 0);
        placePawns(Color.BLACK, 1);

        placePawns(Color.WHITE, MAX_ROWS - 2);
        placePieces(Color.WHITE, MAX_ROWS - 1);
    }

    /*Places a full row of pawns of the given color at the given row.*/
    private final void placePawns(Color color, int row) {
        for (int col = 0; col < MAX_COLUMNS; col++) {
            pieces[row][col] = new Pawn(color);
        }
    }

    /*Places a normal row of 8 special pieces with the given color and row.*/
    private final void placePieces(Color color, int row) {
        pieces[row][0] = new Rook(color);
        pieces[row][1] = new Knight(color);
        pieces[row][2] = new Bishop(color);
        pieces[row][3] = new Queen(color);
        pieces[row][4] = new King(color);
        pieces[row][5] = new Bishop(color);
        pieces[row][6] = new Knight(color);
        pieces[row][7] = new Rook(color);
    }

    /**
     * Moves a piece on the board according to the rules and takes the piece at the destination if necessary
     *
     * @param origin the tile on which the moving piece starts
     * @param destination the tile on which the moving piece ends
     */
    public void move(Coordinates origin, Coordinates destination) {
        if (origin == null || destination == null){
            throw new GameException("origin or destination coordinates are null");
        }
        if (getPiece(origin) == null) {
            throw new GameException("No piece to move");
        }
        Piece piece = getPiece(origin);
        piece.update(this, origin);
        List<Coordinates> access = piece.getAccessible();
        List<Coordinates> captur = piece.getCaptureable();
        if (!access.contains(destination) && !captur.contains(destination)) {
            throw new GameException("Spot is not reachable");
        }

        pieces[origin.getRow()][origin.getColumn()] = null;
        pieces[destination.getRow()][destination.getColumn()] = piece;
    }

    /**
     * Places a piece on the board if the specified spot is free
     *
     * @param piece the piece to place
     * @param coord the coordinates of the spot on which to place the piece
     * @throws GameException if piece or coord are null if the coordinates point to a spot outside the board if the spot
     * is not free
     */
    public void putPiece(Piece piece, Coordinates coord) throws GameException {
        if (piece == null || coord == null) {
            throw new GameException("piece to put or the coordinates are null");
        }
        if (!isOnBoard(coord)) {
            throw new GameException("coord is not on the board");
        }
        if (pieces[coord.getRow()][coord.getColumn()] != null) {
            throw new GameException("The spot is not free");
        }

        pieces[coord.getRow()][coord.getColumn()] = piece;
    }

    /**
     * Returns a piece at a position on the board
     *
     * @param coord the position of the piece to return
     * @return the piece at coord
     */
    public Piece getPiece(Coordinates coord) throws GameException {
        if (coord == null) {
            throw new GameException("the coordinates are null");
        }
        if (!isOnBoard(coord)) {
            throw new GameException("coord is not on the board");
        }

        return this.pieces[coord.getRow()][coord.getColumn()];
    }

    public Piece removePiece(Coordinates coord) throws GameException {
        if (coord == null) {
            throw new GameException("the coordinates are null");
        }
        if (!isOnBoard(coord)) {
            throw new GameException("coord is not on the board");
        }
        if (pieces[coord.getRow()][coord.getColumn()] == null) {
            throw new GameException("No piece to remove");
        }

        Piece p = pieces[coord.getRow()][coord.getColumn()];
        pieces[coord.getRow()][coord.getColumn()] = null;
        return p;
    }

    /**
     * Checks if a position is on the board
     *
     * @param coord the position to check
     * @return true if the position is on the board, false if it is outside
     */
    public static boolean isOnBoard(Coordinates coord) {
        return coord.getRow() >= 0 && coord.getRow() <= MAX_ROWS - 1 && coord.getColumn() >= 0
                && coord.getColumn() <= MAX_COLUMNS - 1;
    }

    /**
     * Checks if a position is attackable by a piece of a certain color. Uses the isOnBoard method
     *
     * @param coord the tile to check
     * @param color the color of the piece attacking
     * @return true if the piece at that position is of the other color than the one received
     */
    public boolean isAttackable(Coordinates coord, Color color) {
        return (isOnBoard(coord) && getPiece(coord) != null && getPiece(coord).getColor() != color);
    }

    @Override
    public String toString() {
        String str = "";
        for (Piece[] piece : pieces) {
            for (Piece piece1 : piece) {
                if (piece1 != null) {
                    if (piece1.getColor() == Color.BLACK) {
                        str += "B";
                    } else {
                        str += "W";
                    }

                    str += piece1.getClass().getSimpleName().substring(0, 2) + " ";
                } else {
                    str += "NUL ";
                }
            }
            str += System.getProperty("line.separator");
        }
        return str;
    }
}
