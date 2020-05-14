/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horses;

import java.awt.Graphics2D;

/**
 *
 * @author sasha
 */
public class AvanzamentoCavallo implements Runnable{
    
    private int x;
    private int y;
    private Cavallo cavallo;
    public final int partenza_x=1;
    public final int partenza_y=1;
    
    public AvanzamentoCavallo(int offset_y){
        this.x = partenza_x;
        this.y = partenza_y * offset_y;
        this.cavallo = new Cavallo(x,y);
        
        
    }
    
    public Cavallo spostaCavallo(Cavallo cavallo){
        cavallo = new Cavallo(x++,y);
        return cavallo;
    }
    
    public void renderingCavallo(Graphics2D disegno){
        this.cavallo = spostaCavallo(cavallo);
        cavallo.Crea(disegno);
    }
    
    public void run() {
        
    }
}
