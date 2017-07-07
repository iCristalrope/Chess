package Controller;

import Model.*;
import View.ConsoleDisplayBoard;
import java.util.ArrayList;

/**
 * Controller of the game
 *
 * @author Olivier De Cannière
 */
public class Game {

    private Color currentColor;
    private Board board;
    private ArrayList<Player> players;

    public Game() {
        board = new Board();
        players = new ArrayList<>();
        players.add(new Player(Color.WHITE));
        players.add(new Player(Color.BLACK));
        ConsoleDisplayBoard.init(); //init SCREEN_CLEAR 
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * changes the current player
     */
    public void nextPlayer() {
        if (this.currentColor == Color.WHITE) {
            this.currentColor = Color.BLACK;
        } else {
            this.currentColor = Color.WHITE;
        }
    }

    //move + validité
    //isOver (pat mat egalité probleme eficasité (verif a chaque move))
    //roque
    //pion au bout + upgrade
}
