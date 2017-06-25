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
    
    public Bishop(Color color){
        this.color = color;
    }
    
    /**
     * Getter of color attributes.
     * 
     * @return the color
     */
    public Color getColor(){
        return this.color;
    }
    
    public static List<Coordinates> accessible(Coordinates pos, Board bord){
        List <Coordinates> accessible = new ArrayList<>();
        Coordinates otherPos;
        //haut droite
        for(int col = pos.getColumn(), row = pos.getRow(); col < MaxColumns && row < MaxRows; col++, row++){
            otherPos = new Coordinates(row, col);
            if(bord.getPiece(otherPos) != null){
                accessible.add(otherPos);
                break;
            }
        }
    }
}
