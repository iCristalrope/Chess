package View;

/**
 * Enum containing colors to change the text printed to the screen
 *
 * @author Philippe
 */
public enum DisplayColor {
    BLACK("0"),
    RED("0;31"),
    GREEN("0;32"),
    BLUE("0;34"),
    BCKGRD_YELLOW("43"),
    BCKGRD_BLUE("44"),
    BCKGRD_MAGENTA("45"),
    BCKGRD_CYAN("46"),
    BCKGRD_WHITE("107");

    private final String CODE;

    private DisplayColor(String code) {
        this.CODE = code;
    }

    /**
     * Method allowing to change the fore- and background color of a String received
     *
     * @param string the string to change the color of
     * @param fore the color to set to the foreground
     * @param back the color to set to the background
     * @return
     */
    public static String toColor(String string, DisplayColor fore, DisplayColor back) {
        return "\033[" + fore.CODE + ";" + back.CODE + "m" + string + "\033[0;39m";
    }
}
