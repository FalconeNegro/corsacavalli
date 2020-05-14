/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horses;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sasha
 */
public class RacePanel extends JFrame {
    private JPanel race_panel;
    private JButton start_race = new JButton("Start");
    private JButton reset_race = new JButton("Reset");
    private PistaDaCorsa pista = new PistaDaCorsa();
    public int finestra_altezza = 768;
    public int finestra_larghezza = 1024;
    
    public RacePanel(){
        setSize(finestra_larghezza,finestra_altezza);
    }
}
