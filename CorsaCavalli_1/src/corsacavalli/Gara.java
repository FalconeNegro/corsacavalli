/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corsacavalli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import static javafx.application.Platform.exit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Sasha
 */
public class Gara extends JPanel implements ActionListener {
    
        private int larghezza;
        private int altezza;
        private int rientro_client;
        private int larghezza_schermo;
        private int altezza_schermo;
        private int altezza_interfaccia;
        private int altezza_tracciato;
        private int altezza_cavallo = 100;
        private int distanza_minima_piste = 10;
        private int distanza_minima_bordo_cavallo = 5;
        private int numero_cavalli = 2;
        private int larghezza_pista;
        private int altezza_pista;
        private int rientro_arrivo = 50;
        private JLabel partenza;
        private JLabel arrivo;
        private JLabel vincitore;
        private Pista piste[];
        private String lista_colori[] = {"black","blue","green","grey","orange","pink","purple","red","turquoise","white","yellow"};
        private ArrayList<String> colori_cavalli;
        private Cavallo cavalli[];
        private Interfaccia interfaccia;
        /*
        private Cavallo[];
        */
    
    public Gara(int _rientro_client,int _larghezza_schermo,int _altezza_schermo,int _altezza_interfaccia){
        rientro_client = _rientro_client;
        larghezza_schermo = _larghezza_schermo;
        altezza_schermo = _altezza_schermo;
        altezza_interfaccia = _altezza_interfaccia;
        setLayout(null);
    }
    
    public void setNumeroCavalli(int value){ 
        numero_cavalli = value;
    }
    
    public void setupPiste(){
        setBackground(Color.decode("#C48702"));
        piste = new Pista[numero_cavalli];
        cavalli = new Cavallo[numero_cavalli];
        // 100,75,50 grandezze cavalli
        calcoloAltezzaCavallo();
        inizializzaVincitore();
        inizializzaPiste();
        inizializzaCavalli();
        inizializzaPartenza();
        inizializzaArrivo();
        generaTracciato();
    }
    
    public void calcoloAltezzaCavallo(){
        // System.out.println("altezza_cavallo : " + altezza_cavallo);
        int altezza_disponibile = altezza_schermo - (rientro_client * 2) - altezza_interfaccia;
        altezza_pista = altezza_cavallo + distanza_minima_bordo_cavallo * 2;
        larghezza_pista = larghezza_schermo - (rientro_client * 2);
        //System.out.println("altezza_pista : " + altezza_pista);
        int spazio_separatori = (numero_cavalli + 1) * distanza_minima_piste;
        altezza_tracciato = spazio_separatori + (altezza_pista) * numero_cavalli;
        int altezza_frame = altezza_tracciato + altezza_interfaccia;
        //System.out.println("altezza_frame : " + altezza_frame);
        //System.out.println("altezza_disponibile : " + altezza_disponibile);
        if(altezza_frame > altezza_disponibile) {
            altezza_cavallo = altezza_cavallo - 25;
            if(altezza_cavallo < 50){
                exit();
            }
            else{
                //System.out.println("lo spazio non basta riduco");
                calcoloAltezzaCavallo();
            }
        }
        else{
            larghezza = larghezza_pista;
            altezza = altezza_tracciato;
            setPreferredSize(new Dimension(larghezza, altezza));
            SwingUtilities.getWindowAncestor(this).pack();
            SwingUtilities.getWindowAncestor(this).setLocation(rientro_client,rientro_client);
            //System.out.println(" ok ");
            
        }
        
    }
    
    public void inizializzaVincitore(){
        vincitore = new JLabel();
        vincitore.setSize(100,100);
        vincitore.setLocation((larghezza_pista/2)-50,(altezza_tracciato/2)-50);
        vincitore.setOpaque(true);
        vincitore.setVisible(false);
        add(vincitore);
    }
    public void inizializzaPiste(){
        int ordinata = distanza_minima_piste;
        for(int i = 0; i < numero_cavalli; i++){
            piste[i] = new Pista(0,ordinata,larghezza_pista,altezza_pista);
            ordinata = ordinata + altezza_pista + distanza_minima_piste;
        }
    }
    
    public void inizializzaArrivo(){
        arrivo = new JLabel();
        arrivo.setBackground(Color.RED);
        arrivo.setSize(distanza_minima_piste,altezza_tracciato);
        arrivo.setLocation(larghezza_pista-rientro_arrivo,0);
        arrivo.setOpaque(true);
    }
    
    public void inizializzaPartenza(){
        partenza = new JLabel();
        partenza.setBackground(Color.GREEN);
        partenza.setSize(distanza_minima_piste,altezza_tracciato);
        partenza.setLocation(cavalli[0].getTestaCavallo() + 3,0);
        partenza.setOpaque(true);
    }
    
    public void inizializzaCavalli(){
        int x = 0;
        int y = 0;
        inizializzaColori();
        int numero_colore;
        String colore;
        for(int i = 0; i < numero_cavalli; i++){
            numero_colore = (int)(Math.random()*(colori_cavalli.size()));
            colore = colori_cavalli.remove(numero_colore);
            x = piste[i].getPos_x();
            y = piste[i].getPos_y() + distanza_minima_bordo_cavallo;   
            cavalli[i] = new Cavallo(x,y,altezza_cavallo,colore, this);
        }
    }
    
    public void inizializzaColori(){
        colori_cavalli = new ArrayList<String>();
        for(String colore: lista_colori){
            colori_cavalli.add(colore);
        }
    }
    
    public void generaTracciato() {
        add(vincitore);
        for (Cavallo cavallo: cavalli){
            add(cavallo);
        }
        add(partenza);
        add(arrivo);
        for (Pista pista: piste) {
            add(pista);
        }
    }
    
    public void avviaGara(){
        for(Cavallo cavallo: cavalli){
            cavallo.setIcon(cavallo.getAnimazioneCorre());
            SwingUtilities.invokeLater(cavallo);
        }
    }
    
    public void azzeraGara(){
        for(Cavallo cavallo: cavalli){
            removeAll();
            revalidate();
            repaint();
            inizializzaVincitore();
            inizializzaPiste();
            inizializzaCavalli();
            inizializzaPartenza();
            inizializzaArrivo();
            generaTracciato();
            
        }
    }
    
    public int getArrivo(){
        return arrivo.getLocation().x;
    }
    
    public void vittoria(String nome){
        for(Cavallo cavallo : cavalli){
            cavallo.setIcon(cavallo.getAnimazioneFermo());
            cavallo.getGaloppo().cancel(true);
        }
        vincitore.setText("HA VINTO  " + nome);
        vincitore.setVisible(true);
    }
    
    public void impostaListeners(Interfaccia _interfaccia){
        interfaccia = _interfaccia;
        interfaccia.getPartenza().setActionCommand("SetPartenza");
        interfaccia.getPartenza().addActionListener(this);
        interfaccia.getAzzera().setActionCommand("SetAzzera");
        interfaccia.getAzzera().addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("SetPartenza"))
        {
            avviaGara();
        }else if(e.getActionCommand().equals("SetAzzera")){
            azzeraGara();
        }
            
    }
}
