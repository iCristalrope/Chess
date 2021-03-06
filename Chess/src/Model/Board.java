package Model;

import Model.PieceClasses.*;
import View.Interaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the board of the game
 *
 * @author Olivier De Cannière
 */
public class Board {

    public static final int MAX_ROWS = 8;
    public static final int MAX_COLUMNS = 8;

    private Piece[][] pieces;
    private ArrayList<Coordinates> whites;
    private ArrayList<Coordinates> blacks;
    private Coordinates coordDoubleMove;

    public Board() {
        this(false);

    }

    public Board(boolean isEmpty) {
        pieces = new Piece[MAX_ROWS][MAX_COLUMNS];
        if (!isEmpty) {
            initBoard();
        }
        initLists();
        coordDoubleMove = null;
    }

    /**
     * getter of attribute whites
     *
     * @return whites
     */
    public ArrayList<Coordinates> getWhites() {
        return this.whites;
    }

    /**
     * getter of attribute blacks
     *
     * @return blacks
     */
    public ArrayList<Coordinates> getBlacks() {
        return this.blacks;
    }

    /**
     * getter of attribute coordDoubleMove
     *
     * @return coordDoubleMove
     */
    public Coordinates getCoordDoubleMove() {
        return coordDoubleMove;
    }

    /**
     * Moves a piece on the board according to the rules and takes the piece at the destination if necessary
     *
     * @param origin the tile on which the moving piece starts
     * @param destination the tile on which the moving piece ends
     */
    public void move(Coordinates origin, Coordinates destination) {
        if (origin == null || destination == null) {
            throw new GameException("origin or destination coordinates are null");
        }
        if (getPiece(origin) == null) {
            throw new GameException("No piece to move");
        }
        if (origin.equals(destination)) {
            return;
        }

        Piece piece = getPiece(origin);
        piece.update(this, origin);

        List<Coordinates> access = piece.getAccessible();
        List<Coordinates> captur = piece.getCaptureable();
        if (!access.contains(destination) && !captur.contains(destination)) {
            throw new GameException("Spot is not reachable");
        }

        if (canSpecialMove(origin, destination)) {
            specialMove(origin, destination);
        } else {
            movePiece(piece, origin, destination); // normal move
        }

        if (canUpgrade(destination)) {
            upgrade(destination);
        }

        if (coordDoubleMove != null && getPiece(coordDoubleMove).getColor() != piece.getColor()) {
            coordDoubleMove = null;
        }
    }

    /**
     * Effectively moves a piece from one spot to another on the board and updates the whites and blacks lists. No
     * integrity tests are done (assumed already checked)
     *
     * @param piece the piece to move
     * @param origin the coordinates of the piece
     * @param destination the coordinates the piece the piece has to move to
     */
    private void movePiece(Piece piece, Coordinates origin, Coordinates destination) {
        pieces[origin.getRow()][origin.getColumn()] = null;
        pieces[destination.getRow()][destination.getColumn()] = piece;
        if (piece.getColor() == Color.WHITE) {
            if (getPiece(destination) != null) {
                blacks.remove(destination);
            }
            whites.remove(origin);
            whites.add(destination);
        } else {
            blacks.remove(origin);
            blacks.add(destination);
            if (getPiece(destination) != null) {
                whites.remove(destination);
            }
        }
    }

    private boolean canSpecialMove(Coordinates origin, Coordinates destination) {
        boolean canSpecialMove = false;
        Piece piece = getPiece(origin);
        if (canCastle(origin)) {
            canSpecialMove = true;
        } else if (canEnPassant(origin, destination)) {
            canSpecialMove = true;
        } else if (canDoubleMove(origin, destination)) {
            canSpecialMove = true;
        }

        return canSpecialMove;
    }

    /**
     * method that handles the special moves such as enPassant, doubleMove and Castling
     */
    private void specialMove(Coordinates origin, Coordinates destination) {
        if (canEnPassant(origin, destination)) {
            enPassant(origin, destination);
        } else if (canDoubleMove(origin, destination)) {
            movePiece(getPiece(origin), origin, destination);
        } else if (canCastle(origin)) {
            castle(origin);
        } else {
            throw new IllegalStateException("canSpecialMove mais pas specialMove");
        }
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

    /**
     * Returns a piece at a position on the board and clear the position
     *
     * @param coord the position of the piece to return
     * @return the piece at coord
     */
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

    /**
     * Checks whether the king at a given position is in check
     *
     * @param coord the position of the King
     * @return true if it is in check, false otherwise
     */
    public boolean isKingInCheck(Coordinates coord) {
        boolean inCheck = false;
        if (getPiece(coord) == null || !getPiece(coord).getClass().getSimpleName().equals("King")) {
            throw new GameException("No king at this position");
        }
        King king = (King) getPiece(coord);
        ArrayList<Coordinates> enemies;
        if (king.getColor() == Color.WHITE) {
            enemies = blacks;
        } else {
            enemies = whites;
        }

        for (Coordinates enemy : enemies) {
            Piece enem = getPiece(enemy);
            King kng;
            if (enem.getClass().getSimpleName().equals("King")) {
                kng = (King) enem;
                kng.simpleUpdate(this, enemy);
            } else {
                enem.update(this, enemy);
            }

            if (enem.getCaptureable().contains(coord)) {
                inCheck = true;
                break;
            }
        }

        return inCheck;
    }

    public boolean isKingCheckMate(Coordinates coord) {
        return isKingInCheck(coord) && !hasKingMoveLeft(coord);
    }

    public boolean isKingPat(Coordinates coord) { //TODO not ok ?
        boolean cond1 = !isKingInCheck(coord) && !hasKingMoveLeft(coord);
        boolean cond2 = true;
        ArrayList<Coordinates> liste;
        if (getPiece(coord).getColor() == Color.WHITE) {
            liste = whites;
        } else {
            liste = blacks;
        }
        for (Coordinates c : liste) {
            Piece piece = getPiece(c);
            piece.update(this, c);
            if (!piece.accessible.isEmpty() || !piece.captureable.isEmpty()) {
                cond2 = false;
                break;
            }
        }
        return cond1 && cond2;
    }

    /**
     * Checks if the king at the coordinates received can castle
     *
     * @param kingCoord the position of the king
     * @return true if the king can castle, false otherwise
     */
    public boolean canCastle(Coordinates kingCoord) { 
//TODO erreur de logique je crois -> à réecrire
//TODO changer les listes de cases accessibles aussi
        boolean canCastle = true;
        if (!isOnBoard(kingCoord)) {
            throw new GameException("coordinates given for the king are not on the board");
        }
        if (kingCoord.getColumn() != 4) {
            return false;
        }
        Piece piece = getPiece(new Coordinates(kingCoord.getRow(), kingCoord.getColumn() + 3));
        Rook rook; //cant cast a knight in rook for example, have to check first
        Piece kingPiece = getPiece(kingCoord);
        if (kingPiece == null) {
            throw new GameException("King is null");
        }
        King king = null;
        if (kingPiece.getClass().getSimpleName().equals("King")) {
            king = (King) kingPiece;
        }else{
            return false;
        }

        Coordinates tmp;

        Color color = king.getColor();

        if (king.getHasMoved()) {
            canCastle = false;
        } else {
            if (piece == null || !piece.getClass().getSimpleName().equals("Rook") || piece.getColor() != color) {
                canCastle = false;
            } else {
                rook = (Rook) piece;
                if (rook.getHasMoved()) {
                    canCastle = false;
                }
            }
        }

        //tests tiles in between are free
        boolean tile2OK = getPiece(new Coordinates(kingCoord.getRow(), kingCoord.getColumn() + 1)) == null;
        boolean tile3OK = getPiece(new Coordinates(kingCoord.getRow(), kingCoord.getColumn() + 2)) == null;
        if (!tile2OK || !tile3OK) {
            canCastle = false;
        }

        //tests checks of the king
        if (canCastle) {
            boolean inCheck1 = isKingInCheck(kingCoord);

            tmp = new Coordinates(kingCoord.getRow(), kingCoord.getColumn() + 1);
            movePiece(king, kingCoord, tmp);
            boolean inCheck2 = isKingInCheck(tmp);

            tmp = new Coordinates(kingCoord.getRow(), kingCoord.getColumn() + 2);
            movePiece(king, new Coordinates(kingCoord.getRow(), kingCoord.getColumn() + 1), tmp);
            boolean inCheck3 = isKingInCheck(tmp);

            movePiece(king, tmp, kingCoord);
            if (inCheck1 || inCheck2 || inCheck3) {
                canCastle = false;
            }
        }

        return canCastle;
    }
    
    public boolean canCastle2(Coordinates kingCoord, boolean left) {
        if (!isOnBoard(kingCoord)) {
            throw new GameException("Coordinates given for the king are not on the board");
        }
        //TODO method getPiece(Coordinates, Class) ?
        //get king if not hasMoved
        Piece piece = getPiece(kingCoord);
        if (!(piece instanceof King)) {
            return false;
        }
        King king = (King) piece;
        if (king.getHasMoved()) {
            return false;
        }
        //get rook if not hasMoved
        Coordinates rookCoord;
        if (left) {
            rookCoord = new Coordinates(kingCoord.getRow(), 0);
        } else {
            rookCoord = new Coordinates(kingCoord.getRow(), MAX_COLUMNS - 1);
        }
        piece = getPiece(rookCoord);
        if (!(piece instanceof Rook)) {
            return false;
        }
        Rook rook = (Rook) piece;
        if (rook.getHasMoved()) {
            return false;
        }
        //TODO method isFreeBetween(kingCoord, rookCoord) ?
        //TODO finish method
        return false;
    }

    /**
     * Checks if a piece can doubleMove
     *
     * @param origin the position of the piece at the start
     * @param destination the position the piece is trying to reach
     * @return true if the piece is a pawn that can double move, false otherwise
     */
    private boolean canDoubleMove(Coordinates origin, Coordinates destination) {
        boolean canDoubleMove = true;
        Piece piece = getPiece(origin);
        if (!piece.getClass().getSimpleName().equals("Pawn")) {
            canDoubleMove = false;
        }

        if (piece.getColor() == Color.WHITE) {
            if (origin.getRow() != 6 || getPiece(new Coordinates(4, origin.getColumn())) != null) {
                canDoubleMove = false;
            }
        } else {
            if (origin.getRow() != 1 || getPiece(new Coordinates(3, origin.getColumn())) != null) {
                canDoubleMove = false;
            }
        }

        return canDoubleMove;
    }

    /**
     * Checks if a piece is a pawn that can make the en passant move on another pawn
     *
     * @param origin the position of the pawn trying to en passant
     * @param destination the position the pawn would have after completing the en passant
     * @return true if the pawn can en passant, false otherwise
     */
    private boolean canEnPassant(Coordinates origin, Coordinates destination) {
        if (origin == null || destination == null) {
            throw new GameException("origin or destination coordinates are null");
        }

        if (!isOnBoard(origin) || !isOnBoard(destination)) {
            throw new GameException("origin or destination not on the board");
        }

        if (getPiece(origin) == null) {
            return false;
        }

        if (coordDoubleMove == null) {
            return false;
        }

        Color doubleMoveColor = getPiece(coordDoubleMove).getColor();
        Piece piece = getPiece(origin);

        if (getPiece(destination) != null || piece == null) {
            return false;
        }
        if (!piece.getClass().getSimpleName().equals("Pawn")) {
            return false;
        }

        Color pieceColor = piece.getColor();

        if (pieceColor == doubleMoveColor) {
            return false;
        }

        if (pieceColor == Color.WHITE) {
            if (new Coordinates(origin.getRow() - 1, origin.getColumn()).equals(coordDoubleMove)) {
                return true;
            }
        } else {
            if (new Coordinates(origin.getRow() + 1, origin.getColumn()).equals(coordDoubleMove)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Performs the enPassant move of the pawn
     *
     * @param origin the position of the pawn trying to en passant
     * @param destination the position the pawn will have after completing the en passant
     */
    private void enPassant(Coordinates origin, Coordinates destination) {
        Piece piece = getPiece(origin);
        movePiece(piece, origin, destination);
        if (piece.getColor() == Color.WHITE) {
            removePiece(new Coordinates(origin.getRow() - 1, origin.getColumn()));
        } else {
            removePiece(new Coordinates(origin.getRow() + 1, origin.getColumn()));
        }
    }

    /**
     * Checks if a piece is a pawn that can upgrade at the end of a move action
     *
     * @param origin the position
     * @return true if the piece is a pawn that can upgrade
     */
    private boolean canUpgrade(Coordinates coord) {
        boolean canUpgrade = true;
        Piece piece = getPiece(coord);
        if (!piece.getClass().getSimpleName().equals("Pawn")) {
            canUpgrade = false;
        }

        if (piece.getColor() == Color.WHITE) {
            if (coord.getRow() != 0) {
                canUpgrade = false;
            }
        } else {
            if (coord.getRow() != 7) {
                canUpgrade = false;
            }
        }

        return canUpgrade;
    }

    public void upgrade(Coordinates destination) {
        Piece piece = getPiece(destination);
        Piece upgraded;
        if (piece == null) {
            throw new GameException("Pawn to upgrade is null");
        }
        if (!piece.getClass().getSimpleName().equals("Pawn")) {
            throw new GameException("Piece is not a pawn");
        }
        upgraded = Interaction.getUpgradePiece(piece.getColor()); //faire qqchose avec upgraded ou pas?
    }

    /**
     * Moves the king and the rook to castle. Use canCastle method to check if it is possible
     *
     * @param kingCoord the position of the king
     */
    public void castle(Coordinates kingCoord) {
        Coordinates rookCoord = new Coordinates(kingCoord.getRow(), kingCoord.getColumn() + 3);
        move(kingCoord, new Coordinates(kingCoord.getRow(), kingCoord.getColumn() + 2));
        move(rookCoord, new Coordinates(kingCoord.getRow(), kingCoord.getColumn() + 1));
    }

    /*Checks if the king has any non check accessible positions left*/
    private boolean hasKingMoveLeft(Coordinates coord) {
        if (getPiece(coord) == null || !getPiece(coord).getClass().getSimpleName().equals("King")) {
            throw new GameException("No king at this position");
        }
        King king = (King) getPiece(coord);
        king.update(this, coord);

        Coordinates save = coord, onMoveSave = coord;
        boolean hasMove = false;
        for (Coordinates accessible : king.accessible) {
            move(onMoveSave, accessible);
            onMoveSave = accessible;
            if (!isKingInCheck(accessible)) {
                hasMove = true;
                break;
            }
        }
        move(onMoveSave, save);
        return hasMove;
    }

    ///////////////////////////////////////////////////////////////////////////////
    /*Puts the pieces needed to start a new game*/
    private void initBoard() {
        placePieces(Color.BLACK, 0);
        placePawns(Color.BLACK, 1);

        placePawns(Color.WHITE, MAX_ROWS - 2);
        placePieces(Color.WHITE, MAX_ROWS - 1);
    }

    /*Places a full row of pawns of the given color at the given row.*/
    private void placePawns(Color color, int row) {
        for (int col = 0; col < MAX_COLUMNS; col++) {
            pieces[row][col] = new Pawn(color);
        }
    }

    /*Places a normal row of 8 special pieces with the given color and row.*/
    private void placePieces(Color color, int row) {
        pieces[row][0] = new Rook(color);
        pieces[row][1] = new Knight(color);
        pieces[row][2] = new Bishop(color);
        pieces[row][3] = new Queen(color);
        pieces[row][4] = new King(color);
        pieces[row][5] = new Bishop(color);
        pieces[row][6] = new Knight(color);
        pieces[row][7] = new Rook(color);
    }

    private void initLists() {
        blacks = new ArrayList<>();
        whites = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j < MAX_COLUMNS; j++) {
                blacks.add(new Coordinates(i, j));
            }
        }
        for (int i = MAX_ROWS - 2; i <= MAX_ROWS - 1; i++) {
            for (int j = 0; j < MAX_COLUMNS; j++) {
                whites.add(new Coordinates(i, j));
            }
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (Piece[] piece : pieces) {
            for (Piece piece1 : piece) {
                if (piece1 != null) {
                    str += piece1.getColor().toString().charAt(0);

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
