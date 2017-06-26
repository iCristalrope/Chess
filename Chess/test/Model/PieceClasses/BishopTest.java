package Model.PieceClasses;

import Model.Board;
import Model.Coordinates;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class BishopTest {

    /**
     * Test of update method, of class Bishop.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Board board = new Board();
        Coordinates coord = new Coordinates(0, 0);
        Bishop instance = null;
        instance.update(board, coord);
    }
    
}
