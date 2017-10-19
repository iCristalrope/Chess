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

    public Color currentColor;
    private Board board;
    private ArrayList<Player> players;
    public Coordinates coordLastKing;

    public Game() {
        board = new Board();
        players = new ArrayList<>();
        players.add(new Player(Color.WHITE));
        players.add(new Player(Color.BLACK));
        currentColor = Color.WHITE;
        coordLastKing = new Coordinates(0,4);
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

    /**
     * Checks if the game is over by checking if the opponent king is in pat or is checkmate
     *
     * @return true if king is in pat or in checkmate, false otherwise
     */
    public boolean isOver() {
        ArrayList<Coordinates> opponentPieces = new ArrayList<>();
        coordLastKing = null;
        boolean isOver = false;
        if (currentColor == Color.WHITE) {
            opponentPieces = board.getBlacks();
        } else {
            opponentPieces = board.getWhites();
        }

        for (Coordinates coord : opponentPieces) {
            if (board.getPiece(coord).getClass().getSimpleName().equals("King")) {
                coordLastKing = coord;
            }
        }

        if (board.isKingCheckMate(coordLastKing) || board.isKingPat(coordLastKing)) {
            isOver = true;
        }
        return isOver;
    }
}

//move + validité
