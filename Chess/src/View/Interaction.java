package View;

import Model.Coordinates;
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
                CLAVIER.next();
            }
        }
        return i;
    }

    /**
     * Shows a message and tries to read a Species. Once it succeeds, returns it.
     *
     * @param msg String to display before asking for the input to help the user understand what he has to enter
     * @return the Species read from the keyboard
     */
    /*public static Species getNextSpecies(String msg) {
    Species s = Species.GAZELLE;
    String str;
    boolean inputOK = false;
    while (!inputOK) {
    System.out.print(msg);
    str = CLAVIER.next();
    str = str.toLowerCase();
    
    inputOK = true;
    if (null == str) {
    inputOK = false;
    System.out.println("Invalid input. Enter a species. You can "
    + "choose from : Gazelle, Zebra, Crocodile, Lion, "
    + "Elephant");
    } else {
    switch (str) {
    case "gazelle":
    s = Species.GAZELLE;
    break;
    case "zebra":
    s = Species.ZEBRA;
    break;
    case "crocodile":
    s = Species.CROCODILE;
    break;
    case "lion":
    s = Species.LION;
    break;
    case "elephant":
    s = Species.ELEPHANT;
    break;
    default:
    inputOK = false;
    System.out.println("Invalid input. Enter a species. "
    + "You can choose from : Gazelle, Zebra, "
    + "Crocodile, Lion, Elephant");
    break;
    }
    }
    }
    return s;
    }*/
    /**
     * Shows a message and tries to read a Coordinates. Once it succeeds, returns it.
     *
     * @param msg String to display before asking for the input to help the user understand what he has to enter
     * @return the Coordinates read from the keyboard
     */
    public static Coordinates getNextCoordinates(String msg) {
        Coordinates c = null;
        boolean inputOK = false;
        int x;
        int y;
        while (!inputOK) {
            System.out.println(msg);
            x = getNextInt("Row at which to place the animal : ");
            y = getNextInt("Column at which to place the animal : ");
            if (x >= 0 && x <= NB_ROWS && y >= 0 && y <= NB_COLUMNS) {
                inputOK = true;
                c = new Coordinates(x, y);
            } else {
                System.out.println("Invalid input. Row should be in [0, 4] "
                        + "and column in [0, 5]");
            }
        }
        return c;
    }
}
