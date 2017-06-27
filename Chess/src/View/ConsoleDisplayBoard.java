package View;

import Model.Board;
import static Model.Board.*;
import Model.Color;
import Model.Coordinates;
import Model.Piece;

/**
 * Class containing a method to display the board in the console in an enhanced way
 *
 * @author Olivier De Cannière
 */
public class ConsoleDisplayBoard {

    private static final int ROWS_DISPLAY = 1 + 2 * MAX_ROWS;
    private static final int COLS_DISPLAY = 1 + 5 * MAX_COLUMNS;

    private static final String[][] CLEAR_SCREEN = new String[ROWS_DISPLAY][COLS_DISPLAY];
    private static String[][] screen;

    public static void draw(Board board) {
        Piece piece;
        String str = "";

        screen = CLEAR_SCREEN.clone();

        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLUMNS; j++) {
                Coordinates coord = new Coordinates(i, j);
                piece = board.getPiece(coord);

                str = tileColored(" ", coord);
                screen[1 + i * 2][1 + j * 5] = str;
                screen[1 + i * 2][4 + j * 5] = str;
                if (piece == null) {
                    screen[1 + i * 2][2 + j * 5] = str;
                    screen[1 + i * 2][3 + j * 5] = str;
                } else {
                    str = tileColored(pieceColored("" + piece.getClass().getSimpleName().charAt(0), piece), coord);
                    screen[1 + i * 2][2 + j * 5] = str;
                    str = tileColored(pieceColored("" + piece.getClass().getSimpleName().charAt(1), piece), coord);
                    screen[1 + i * 2][3 + j * 5] = str;
                }
            }
        }

        for (String[] strings : screen) { // print array
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    /**
     * fills the CLEAR_SCREEN array according to the size of the board
     */
    public static void init() {
        screen = new String[ROWS_DISPLAY][COLS_DISPLAY];
        for (int i = 0; i < COLS_DISPLAY; i++) {
            fillLine(0, i, "╔", "═", "╦", "╗");
        }
        for (int i = 1; i < ROWS_DISPLAY - 1; i++) {
            for (int j = 0; j < COLS_DISPLAY; j++) {
                if (i % 2 == 0) {
                    fillLine(i, j, "╠", "═", "╬", "╣");
                } else {
                    fillLine(i, j, "║", " ", "║", "║");
                }
            }
        }
        for (int i = 0; i < COLS_DISPLAY; i++) {
            fillLine(ROWS_DISPLAY - 1, i, "╚", "═", "╩", "╝");
        }
    }

    private static void fillLine(int i, int j, String a, String b, String c, String d) { //String dans l'ordre d'apparition
        String str = "";
        if (j == 0) {
            str = a;
        } else if (j == COLS_DISPLAY - 1) {
            str = d;
        } else {
            if (j % 5 == 0) {
                str = c;
            } else {
                str = b;
            }
        }
        CLEAR_SCREEN[i][j] = str;
    }

    /**
     * Colors a text according to the spot it sits on
     *
     * @param coord the spot to check
     * @return the colored string
     */
    private static String tileColored(String str, Coordinates coord) {
        int i = (coord.getRow() * Board.MAX_COLUMNS + coord.getColumn()) % 2;
        if (coord.getRow() % 2 == 1){
            i++;
        }
        if (i == 1) {
            str = DisplayColor.toColorBckgrnd(str, DisplayColor.BCKGRD_CYAN);
        } else {
            str = DisplayColor.toColorBckgrnd(str, DisplayColor.BCKGRD_GREY);
        }
        return str;
    }

    /**
     * Colors a text according to the spot it sits on
     *
     * @param coord the spot to check
     * @return the colored string
     */
    private static String pieceColored(String str, Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            str = DisplayColor.toColorForgrnd(str, DisplayColor.WHITE);
        }
        return str;
    }
}
