/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horses;

import javax.swing.JPanel;

/**
 *
 * @author sasha
 */
public class PistaDaCorsa extends JPanel{
    private int distanza_dal_muro = 10;
    private int distanza_cavalli = 10;
    private int num_cavalli = 2;
    private int num_threads = 2;
    AvanzamentoCavallo cavalli[] = new AvanzamentoCavallo[num_cavalli];
    Thread threads[] = new Thread[num_threads];
    
    
    public PistaDaCorsa(){
        
    }
    
    public void pulisciThread(){
       for(int i=0;i<threads.length;i++){
           threads[i] = null;
       }
    }
    
    public void sgombroCavalli(){
       for(int i=0;i<cavalli.length;i++){
           cavalli[i] = null;
       }
    }
    
    public void start(){
        pulisciThread();
        
        for(int i=0;i<threads.length;i++){
            Thread t=new Thread(cavalli[i]);
            t.start();
            threads[i] = t;
        }
    }
    
    public void reset(){
        sgombroCavalli();
        
        for(int i=0;i<num_cavalli;i++){
            cavalli[i]=new AvanzamentoCavallo((i * distanza_cavalli) + distanza_dal_muro);
        }
    }
}
