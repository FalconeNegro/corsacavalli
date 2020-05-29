/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corsacavalli;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sasha
 */
public class Interfaccia extends JPanel  {
    private int altezza = 62;
    private JTextField input;
    private JLabel label;
    private JButton conferma;
    private JButton partenza;
    private JButton azzera;
    
    public Interfaccia(){
        input = new JTextField(2);
        label = new JLabel("inserisci il numero di cavalli compreso fra 2 e 10");
        conferma = new JButton("Conferma");
        partenza = new JButton("Partenza");
        azzera = new JButton("Azzera");
        setLayout(new BorderLayout());
        add(label,BorderLayout.NORTH);
        add(input,BorderLayout.CENTER);
        add(conferma,BorderLayout.SOUTH);
    }
    
    public JButton getConferma(){
        return conferma;
    }
    
    public JTextField getInput(){
        return input;
    }

    
    public boolean checkInput(){
        if(!isNullOrEmpty(input.getText())){
            int value = Integer.valueOf(input.getText());
            if(value < 2 || value > 10){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
    
    public JButton getPartenza(){
        return partenza;
    }
    
    public JButton getAzzera(){
        return azzera;
    }
    
    public JLabel getLabel(){
        return label;
    }
    
    public int getAltezza(){
        return altezza;
    }
    
    public void setLabel(String s){
        label.setText(s);
    }
    
    static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
        // Or return s == null  s.isBlank(); in Java 11+
    } 
    
    public void setup(){
        removeAll();
        revalidate();
        repaint();
        setLayout(new FlowLayout());
        add(partenza);
        add(azzera);
        
    }  
}
