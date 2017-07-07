package Model;

/**
 * Class representing a player of the game
 *
 * @author Olivier De Cannière
 */
public class Player {
    private final Color color;
    private int score;

    public Player(Color color){
        this.color = color;
        this.score = 0;
    }
    
}
