/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corsacavalli;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 *
 * @author Sasha
 */
class Cavallo extends JLabel implements Runnable{
    
    
    private SwingWorker<Void,Void> galoppo;
    private String nome_cavallo;
    private ImageIcon animazione_corre;
    private BufferedImage first_frame;
    private ImageIcon animazione_fermo;
    private int pos_x;
    private int pos_y;
    private Gara gara;
    private int velocita_min;
    private int velocita_max;
    private int testa_del_cavallo; //lato destro della label
    
    public Cavallo(int _pos_x, int _pos_y, int _altezza_cavallo,String _colore, Gara _gara){
        nome_cavallo = _colore;
        String name = "horse_";
        if(_altezza_cavallo > 99){
            name = name + "big_";
        }
        else if(_altezza_cavallo > 74){
            name = name + "medium_";
        }
        else{
            name = name + "small_";
        }
        name = name + _colore + ".gif";
        gara = _gara;
        //System.out.println(name);
        URL url = gara.getClass().getResource(name);
        animazione_corre = new ImageIcon(url);
        try
            {
            // the line that reads the image file
            first_frame = ImageIO.read(url);
            //BufferedImage image = ImageIO.read(new File("/Users/al/some-picture.jpg")
            } 
        catch (IOException e)
            {
              // log the exception
              // re-throw if desired
            }
        animazione_fermo = new ImageIcon(first_frame);
        //System.out.println(animazione_corre);
        pos_x = _pos_x;
        pos_y = _pos_y;
        setLocation(pos_x, pos_y);
        setIcon(animazione_fermo);
        setSize(animazione_corre.getIconWidth(), animazione_corre.getIconHeight());
        testa_del_cavallo = (getSize().width / 4) * 3;
        velocita_min = 2;
        velocita_max = 20;
        inizializzaGaloppo();
    }
    
    public void setPosX(int pos) {
        pos_x = pos;
    }
    
    public void setPosY(int pos) {
        pos_y = pos;
    }
    
    public int getPosX() {
        return pos_x;
    }
    
    public int getPosY() {
        return pos_y;
    }
    
    public SwingWorker<Void,Void> getGaloppo(){
        return galoppo;
    }
    
    public ImageIcon getAnimazioneCorre(){
        return animazione_corre;
    }
    
    public ImageIcon getAnimazioneFermo(){
        return animazione_fermo;
    }
    
    public int getTestaCavallo(){
        return testa_del_cavallo;
    }

    public void inizializzaGaloppo(){
        galoppo = new SwingWorker <Void,Void> () {
            @Override
            protected Void doInBackground() throws Exception{
                while(!isCancelled()){
                    //System.out.println(nome_cavallo +": start");
                    pos_x ++;
                    testa_del_cavallo ++;
                    //System.out.println(nome_cavallo +": incrementa");
                    int random = (int)(Math.random()*((velocita_max-velocita_min)+1))+velocita_min;
                    //System.out.println(nome_cavallo+ ":" + String.valueOf(random));
                    for (int i=0; i<2; i++){
                        try {
                            Thread.sleep( random);
                        } catch (InterruptedException e) {
                            //System.out.println(nome_cavallo +": ERRORE");
                            //e.printStackTrace();
                        }
                    }
                    //System.out.println(nome_cavallo +": aggiorna label");
                    if(testa_del_cavallo > gara.getArrivo()){
                        gara.vittoria(nome_cavallo);
                    }
                    else{
                        setLocation(pos_x,pos_y);
                    //System.out.println(nome_cavallo +": esce");
                    }
                }
                return null;
            }
            
            protected void done(){
                //System.out.println(nome_cavallo +": elimino");
                galoppo = null;
            }
        };
}
    @Override
    public void run() {
        galoppo.execute();
    }
}
