/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corsacavalli;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Sasha
 */
class Pista extends JLabel{
    
    private Color colore = Color.decode("#298000");
    private int pos_x;
    private int pos_y;
    private int larghezza;
    private int altezza;
    
    public Pista(int _pos_x,int _pos_y,int _larghezza,int _altezza){
        pos_x = _pos_x;
        pos_y = _pos_y;
        larghezza = _larghezza;
        altezza = _altezza;
       // System.out.println("creao pista: " + _pos_x + "," + _pos_y + "," + _larghezza + "," + _altezza);
        setBackground(colore);
        setSize(larghezza,altezza);
        setLocation(pos_x,pos_y);
        setOpaque(true);
    }
    
    public int getPos_x(){
        return pos_x;
    }
    
    public int getPos_y(){
        return pos_y;
    }
}
