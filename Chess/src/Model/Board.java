package Model;

import Model.PieceClasses.*;

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
        pieces = new Piece[MAX_ROWS][MAX_COLUMNS];
        init();
    }
    
    private final void init(){
        //putPiece(Piece piece, Coordinates coord)
        placePieces(Color.BLACK, 0);
        placePawns(Color.BLACK, 1);
        
        placePawns(Color.WHITE, MAX_ROWS-2);
        placePieces(Color.WHITE, MAX_ROWS-1);
        
    }
    
    /*Places a full row of pawns of the given color at the given row.*/
    private final void placePawns(Color color, int row){
        for(int col = 0; col < MAX_COLUMNS; col++){
            pieces[MAX_ROWS-2][col] = new Pawn(color);
        }
    }
    
    /*Places a normal row of 8 special pieces with the given color and row.*/
    private final void placePieces(Color color, int row){
        pieces[row][0] = new Rook(color);
        pieces[row][1] = new Knight(color);
        pieces[row][2] = new Bishop(color);
        
        
        pieces[row][5] = new Bishop(color);
        pieces[row][6] = new Knight(color);
        pieces[row][7] = new Rook(color);
    }

    /**
     * Moves a piece on the board according to the rules and takes the piece at the destination if necessary
     *
     * @param board the board containing the pieces
     * @param origin the tile on which the moving piece starts
     * @param destination the tile on which the moving piece ends
     */
    public void move(Board board, Coordinates origin, Coordinates destination) {
        //TODO implement move
    }

    /**
     * Returns a piece at a position on the board
     *
     * @param coord the position of the piece to return
     * @return the piece at coord
     */
    public Piece getPiece(Coordinates coord) {
        return this.pieces[coord.getRow()][coord.getColumn()];
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
}
