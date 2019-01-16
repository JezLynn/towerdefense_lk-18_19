/**
 *
 * Beschreibung
 * behilfsmäßig
 * erstellt ein Jframe
 *
 * @version 1.0 vom 14.11.2018
 * @author Konstantin Bachem
 */
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics; 
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseMotionListener;
//import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

import java.util.ArrayList;
public class Mygui2 extends JFrame {
  
  

  public static int randomint(int min,int max){return ((int)(Math.random() * (max - min+1) ) + min);}
 
  Mygui2(String s){
//    SwingUtilities.invokeLater(new Runnable() {
//    public void run() {
    super(s);
    System.out.println("Created GUI on EDT? "+
    SwingUtilities.isEventDispatchThread());
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    
    this.setSize(250,250);
    this.setVisible(true);  
//    }
//  });
}       
  Mygui2(){this("fenster");}
    
}



















  
            

