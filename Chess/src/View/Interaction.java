package View;

import Model.Color;
import Model.Coordinates;
import Model.GameException;
import Model.Piece;
import Model.PieceClasses.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class used to receive various inputs from the user in a safe and controlled way
 *
 * @author G43368
 */
public class Interaction {

    private static final Scanner CLAVIER = new Scanner(System.in);
    private static final int NB_ROWS = 5;
    private static final int NB_COLUMNS = 6;

    /**
     * Private constructor in order to prevent instantiation
     */
    private Interaction() {

    }

    /**
     * Shows a message and tries to read an int. Once it succeeds, returns it.
     *
     * @param msg String to display before asking for the input to help the user understand what he has to enter
     * @return the int read from the keyboard
     */
    public static int getNextInt(String msg) {
        int i = 0;
        boolean inputOK = false;
        while (!inputOK) {
            try {
                System.out.print(msg);
                i = CLAVIER.nextInt();
                inputOK = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter an integer.");
                CLAVIER.next(); //empties the buffer
            }
        }
        return i;
    }

    /**
     * Shows a message and tries to read a Coordinates. Once it succeeds, returns it.
     *
     * @param msg String to display before asking for the input to help the user understand what he has to enter
     * @return the Coordinates read from the keyboard
     */
    public static Coordinates getNextCoordinates(String msg) {
        Coordinates coord = null;
        boolean inputOK = false;
        int x;
        String y;
        while (!inputOK) {
            System.out.println(msg);
            x = getNextInt("Row number : ");
            System.out.print("Column letter : ");
            y = CLAVIER.next();
            try {
                coord = new Coordinates(x, y);
                System.out.println(coord);
                inputOK = true;
            } catch (GameException e) {
                System.out.println("Invalid input."); //TODO probleme ne lecture en trop si refuse les coords
                System.out.println(msg);
                CLAVIER.next(); //empties the buffer
            }
        }
        return coord;
    }

    /**
     * Shows a message and tries to read a type of piece a pawn can upgrade to. Once it succeeds, it returns the piece
     * that will replace the pawn
     *
     * @param color the color of the pawn to upgrade
     * @return the upgraded piece
     */
    public static Piece getUpgradePiece(Color color) {
        boolean inputOK = false;
        String input;
        Piece upgraded = null;
        System.out.println("You can upgradeyou pawn!" + System.getProperty("line.separator")
                + "Choose between Queen, Rook, Bishop and Knight");
        while (!inputOK) {
            input = CLAVIER.next();
            inputOK = true;
            switch (input.toLowerCase()) {
                case "queen":
                    upgraded = new Queen(color);
                    break;
                case "rook":
                    upgraded = new Rook(color);
                    break;
                case "bishop":
                    upgraded = new Bishop(color);
                    break;
                case "knight":
                    upgraded = new Knight(color);
                    break;
                default:
                    inputOK = false;
            }
        }
        return upgraded;
    }
}
