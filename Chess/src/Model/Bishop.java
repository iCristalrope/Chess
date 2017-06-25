/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Board.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Philippe
 */
public class Bishop implements Pieceable {

    private Color color;

    public Bishop(Color color) {
        this.color = color;
    }

    /**
     * Getter of color attributes.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    public static List<Coordinates> accessible(Coordinates pos, Board board) {
        List<Coordinates> accessible = new ArrayList<>();
        Coordinates otherPos;
        int col = pos.getColumn(), row = pos.getRow();
        
        //haut droite
        while ( col < MAX_COLUMNS && row < MAX_ROWS) {
            otherPos = new Coordinates(row, col);
            if (board.getPiece(otherPos) != null) {
                accessible.add(otherPos);
                break;
            }
            update();
        }
        col = pos.getColumn();
        row = pos.getRow();
        //bas droite 
        for (; col < MAX_COLUMNS && row < MAX_ROWS; col++, row--) {
            otherPos = new Coordinates(row, col);
            if (board.getPiece(otherPos) != null) {
                accessible.add(otherPos);
                break;
            }
        }
        return accessible;
    }
    
    public static List<Coordinates> captureable(Coordinates pos, Board board){
        return null;
    }
}
