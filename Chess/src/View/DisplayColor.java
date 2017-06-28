package View;

/**
 * Enum containing colors to change the text printed to the screen
 *
 * @author Philippe
 */
public enum DisplayColor {
    BLACK("0"),
    RED("31"),
    GREEN("32"),
    YELLOW("33"),
    BLUE("34"),
    WHITE("1;37"),
    BCKGRD_YELLOW("43"),
    BCKGRD_BLUE("44"),
    BCKGRD_MAGENTA("45"),
    BCKGRD_CYAN("46"),
    BCKGRD_GREY("47"),
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
     * @return
     */
    public static String toColorForgrnd(String string, DisplayColor fore) {
        return "\033[" + fore.CODE + "m" + string + "\033[0m";
    }

    /**
     * Method allowing to change the fore- and background color of a String received
     *
     * @param string the string to change the color of
     * @param back the color to set to the background
     * @return
     */
    public static String toColorBckgrnd(String string, DisplayColor back) {
        return "\033[" + back.CODE + "m" + string + "\033[049m";
    }
}
