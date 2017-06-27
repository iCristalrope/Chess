package View;

import Model.Board;
import static Model.Board.*;

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
        for (String[] strings : CLEAR_SCREEN) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
        //TODO pieces
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
            fillLine(ROWS_DISPLAY-1, i, "╚", "═", "╩", "╝");
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
}
