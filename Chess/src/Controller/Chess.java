package Controller;

import Model.Color;
import Model.Coordinates;
import Model.Piece;
import View.ConsoleDisplayBoard;
import View.DisplayColor;
import View.Interaction;

/**
 *
 * @author Olivier De Cannière
 */
public class Chess {

    static Game game;

    public static void main(String[] args) {
        game = new Game();

        boolean inputOK;
        Piece originPiece = null;

        while (!game.isOver()) {
            Coordinates origin = null;
            Coordinates destination = null;

            showHeader();
            ConsoleDisplayBoard.draw(game.getBoard());
            System.out.println("");
            System.out.println(DisplayColor.toColorForgrnd(game.currentColor + " player's turn.", DisplayColor.GREEN));

            inputOK = false;
            while (!inputOK) {
                origin = nonNullPieceCoord("Enter the row and then the column of the piece you want to move");
                originPiece = game.getBoard().getPiece(origin);
                originPiece.update(game.getBoard(), origin);
                if (originPiece.getColor() == game.currentColor) {
                    if (!originPiece.getAccessible().isEmpty() || !originPiece.getCaptureable().isEmpty()) {
                        inputOK = true;
                        System.out.println(originPiece);/////////////////////}
                    } else {
                        System.out.println(originPiece);/////////////////////
                        System.out.println("can't move that");
                    }
                }
            }
            System.out.println("");
            System.out.println("The piece you have chosen can : ");
            System.out.println("Move onto : " + originPiece.getAccessible());
            System.out.println("Attack : " + originPiece.getCaptureable());

            //origin
            ////////
            //destination
            inputOK = false;
            while (!inputOK) {    //TODO impossible pour une piece blache d'aller sur la ligne 8?
                System.out.println("");
                destination = Interaction.getNextCoordinates("Select the position to attack/move onto");
                if (originPiece.getAccessible().contains(destination) || originPiece.getCaptureable().contains(destination)) {
                    inputOK = true;
                } else {
                    System.out.println("can't move there");
                }
            }
            //MOVE
            game.getBoard().move(origin, destination);//TODO test si pas mis roi en echec

            if (!game.isOver()) {//TODO check pas si piece qui met en echec peut etre tuée
                game.currentColor = nextColor(game.currentColor);
            }
            for (int i = 0; i < 50; i++) {
                System.out.println("");
            }
        }

        if (game.getBoard().isKingPat(game.coordLastKing)) {
            System.out.println("Pat!");
        } else {
            System.out.println("Player with color  " + game.currentColor + " has won!");
        }
    }

    private static void showHeader() {
        System.out.println("       ╔═══════════════════════════════╗\n"
                + "       ║   ________  __________________║\n"
                + "       ║  / ____/ / / / ____/ ___/ ___/║\n"
                + "       ║ / /   / /_/ / __/  \\__ \\\\__ \\ ║\n"
                + "       ║/ /___/ __  / /___ ___/ /__/ / ║\n"
                + "       ║\\____/_/ /_/_____//____/____/  ║\n"
                + "       ║                               ║\n"
                + "       ╚═══════════════════════════════╝\n");
    }

    private static Color nextColor(Color current) {
        if (current == Color.WHITE) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    private static Coordinates nonNullPieceCoord(String msg) {
        boolean coordSelectionOK = false;
        Coordinates origin = null;
        while (!coordSelectionOK) {
            origin = Interaction.getNextCoordinates(msg);
            if (game.getBoard().getPiece(origin) == null) {
                System.out.println("No piece at that position");
            } else {
                coordSelectionOK = true;
            }
        }
        return origin;
    }
}
