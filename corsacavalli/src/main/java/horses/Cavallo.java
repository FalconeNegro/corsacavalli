/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horses;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author sasha
 */
public class Cavallo {
    private final int larghezza= 8;
    private final int altezza = 8;
    private int x;
    private int y;
    
    public Cavallo(int _x,int _y){
        this.x = _x;
        this.y = _y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setX(int _x){
        this.x = _x;
    }
    
    public void setY(int _y){
        this.y = _y;
    }
    
    public void Crea(Graphics2D disegno){
       Rectangle2D.Double forma_cavallo = new Rectangle2D.Double(x, y,larghezza,altezza);
       disegno.setColor(Color.yellow);
       disegno.fill(forma_cavallo);
       disegno.setColor(Color.red);
       disegno.draw(forma_cavallo);
    }
}
