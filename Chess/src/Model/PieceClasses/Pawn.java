package Model.PieceClasses;

import Model.*;
import static Model.Board.*;

/**
 *
 * @author Philippe
 */
public class Pawn extends Piece implements Pieceable {

    private final static int[][] DIRECTION_UPDATE = {{1, 0}, {1, 1}, {1, -1}};

    /**
     * Creates a Pawn of the specified color
     *
     * @param color the color of the bishop
     */
    public Pawn(Color color) {
        super(color);
    }

    /**
     * Updates the accessible and captureable lists of the this piece
     *
     * @param board the board containing the pieces
     * @param coord the coordinates of this piece
     */
    @Override
    public void update(Board board, Coordinates coord) {
        int multColor = this.color == Color.WHITE ? -1 : 1;
        int row, col, dirUpdt = 0;
        Coordinates coord2;

        for (int[] directionUpdate : DIRECTION_UPDATE) {
            row = coord.getRow() + multColor * directionUpdate[0];
            col = coord.getColumn() + directionUpdate[1];
            coord2 = new Coordinates(row, col);

            if (isOnBoard(coord2) && board.getPiece(coord2) == null && dirUpdt == 0) {
                accessible.add(coord2);
                //TODO check if free (vois pas le probleme)
            } else if (dirUpdt != 0 && board.isAttackable(coord2, color)) {
                captureable.add(coord2);
            }
            dirUpdt++;
        }

        if (board.getPiece(coord).getColor() == Color.WHITE) {
            specialUpdateWhite(board, coord);
        } else {
            specialUpdateBlack(board, coord);
        }
    }

    /**
     * Completes the accessible list to account for special moves the pawn can make (White)
     *
     * @param board the board containing the pieces
     * @param coord the coordinates of the pawn
     */
    private void specialUpdateWhite(Board board, Coordinates coord) {
        Coordinates coordPassant = board.getCoordDoubleMove();

        if (coord.getRow() == 6) {
            accessible.add(new Coordinates(coord.getRow() - 2, coord.getColumn()));
        }
        if (coordPassant != null && board.getPiece(coordPassant).getColor() == Color.BLACK) {
            if (new Coordinates(coord.getRow(), coord.getColumn() + 1).equals(coordPassant)
                    || new Coordinates(coord.getRow(), coord.getColumn() - 1).equals(coordPassant)) {
                accessible.add(new Coordinates(coordPassant.getRow() - 1, coordPassant.getColumn()));
            }
        }
    }

    /**
     * Completes the accessible list to account for special moves the pawn can make (Black)
     *
     * @param board the board containing the pieces
     * @param coord the coordinates of the pawn
     */
    private void specialUpdateBlack(Board board, Coordinates coord) {
        Coordinates coordPassant = board.getCoordDoubleMove();

        if (coord.getRow() == 1) {
            accessible.add(new Coordinates(coord.getRow() + 2, coord.getColumn()));
        }
        if (coordPassant != null && board.getPiece(coordPassant).getColor() == Color.WHITE) {
            if (new Coordinates(coord.getRow(), coord.getColumn() + 1).equals(coordPassant)
                    || new Coordinates(coord.getRow(), coord.getColumn() - 1).equals(coordPassant)) {
                accessible.add(new Coordinates(coordPassant.getRow() + 1, coordPassant.getColumn()));
            }
        }
    }
}
