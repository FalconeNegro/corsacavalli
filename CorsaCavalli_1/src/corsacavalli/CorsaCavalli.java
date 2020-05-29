/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corsacavalli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CorsaCavalli extends JFrame implements ActionListener{
    
    private int rientro_client = 50;
    private int altezza_schermo;
    private int larghezza_schermo;
    private Interfaccia interfaccia;
    private Gara gara;
    
    public CorsaCavalli(){
        Dimension grandezza_schermo = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println("grandezza_schermo : " + grandezza_schermo);
        altezza_schermo = grandezza_schermo.height;
        larghezza_schermo = grandezza_schermo.width;
        //System.out.println("altezza_schermo : " + altezza_schermo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        interfaccia = new Interfaccia();
        gara = new Gara(rientro_client,larghezza_schermo,altezza_schermo,interfaccia.getAltezza());
        add(gara,BorderLayout.NORTH);
        add(interfaccia,BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args){
       CorsaCavalli corsa_cavalli = new CorsaCavalli();
       corsa_cavalli.abilitaConferma();
    }
    
    public void abilitaConferma(){
       interfaccia.getConferma().setActionCommand("SetConferma");
       interfaccia.getConferma().addActionListener(this);
    }   

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("SetConferma"))
        {
            if(interfaccia.checkInput() == true){
                gara.setNumeroCavalli(Integer.valueOf(interfaccia.getInput().getText()));
                interfaccia.setup();
                gara.setupPiste();
                gara.impostaListeners(interfaccia);
            }
            else{
                interfaccia.setLabel(" ERRORE : Il valore inserito deve essere compreso tra 2 e 10");
                pack();
                abilitaConferma();
            }
        }
    }
}
