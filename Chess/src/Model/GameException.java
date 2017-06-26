package Model;

/**
 * Indicates conditions that a reasonable game might want to catch.
 *
 * @author esiProf
 */
public class GameException extends RuntimeException {

    /**
     * Creates a new instance of <code>GameException</code> without detail message.
     */
    GameException() {
    }

    /**
     * Constructs an instance of <code>GameException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    GameException(String msg) {
        super(msg);
    }
}
