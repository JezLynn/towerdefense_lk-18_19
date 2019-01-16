/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 19.12.2018
 * @author Konstantin Bachem
 */

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
 import java.util.ArrayList;
 import java.io.*;//dateilesen
public class Playgroundanzeige extends JPanel {
  Color[][] hintergrund;
  int zoom=23;
  Playground Pground;
  Playgroundanzeige(Playground ground){
    this.Pground=ground;
    levelupdate(ground.level);    
  }
  
  public static void main(String[] args) {
    Mygui2 gui=new Mygui2();

        String[] datei=dateilesen("Levels/level_01.txt");
    //    String[] datei=dateilesen("level_01.txt");
    /*String[] datei=new String[10];
    datei[0]= "##########";
    datei[1]= "#________#";
    datei[2]= "#___v<<<_#";
    datei[3]= "#___v__^_#";
    datei[4]= "#_X<<__^_#";
    datei[5]= "#______^_#";
    datei[6]= "#___S>>^_#";
    datei[7]= "#________#";
    datei[8]= "#________#";
    datei[9]= "##########";*/


    char[][] level=new char[datei.length][]; //eingelesenes level als char array
    for (int i=0;i<datei.length ;i++ ) {
      level[i]=datei[i].toCharArray();
      System.out.println(datei[i]);
    } // end of for
    Playground hex=new Playground(level);
    //    gui.s
    hex.zoom=23;
    //    hex.zoom=50;
    Playgroundanzeige anzeige=new Playgroundanzeige(hex);
    gui.add(anzeige);
    anzeige.updateUI();


    Tower T=new Tower(new Punkt(2.5,2.5),hex);
    T.range=5;
    T.reload=10;

    hex.add(T);

    int i=100;
    while(true) {
      if(--i<0){
        i=1000;
      hex.add(hex.newenemy());
      }
      hex.update();

      gui.repaint();
      //      if (Math.random()>0.95) {
      //        hex.add(hex.newenemy());
      //      } // end of if
      //      hex.add(hex.newenemy());}
      try{
        //print something here
        Thread.sleep(10); //sleep
        //print something else here
      }
      catch(InterruptedException e){    System.out.println("got interrupted!");
      }

    } // end of for

  } // end of main

   public static String[] dateilesen(String pfad) {
    try{
      ArrayList<String> datei = new ArrayList<String>();

      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pfad), "UTF-8"));
      String zeile = "";
      while( (zeile = br.readLine()) != null ){
        datei.add(zeile);
      }
      br.close();
      return datei.toArray(new String[datei.size()]);
    } catch(IOException e) {e.printStackTrace();}
    return null;
  }

  public void levelupdate(char[][] level){
    hintergrund=new Color[level.length][level[0].length];
    for (int X=0;X<level[0].length ;X++ ) { //initialisiert das spielfeld d.h.setzt hintergrund farben
      for (int Y=0;Y<level.length ;Y++ ) {
        Color f=Color.orange;
        switch (level[Y][X]) {
          case  '#':
            f=Color.BLACK;
            break;
          case  '>':case  '<': case  'v': case  '^':
            f=Color.RED;
            break;
          case  'S':
            f=Color.GREEN;
            break;
          case  'X':
            f=Color.BLUE;
            break;
          case  '_':
            f=Color.WHITE;
            break;
          default:

        } // end of switch
        hintergrund[Y][X]=f;
      } // end of for
    } // end of for
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    boolean rahmen=true;
    for (int X=0;X<hintergrund[0].length ;X++ ) { //malt das feld
      for (int Y=0;Y<hintergrund.length ;Y++ ) {
        g.setColor(hintergrund[Y][X]==null?Color.GRAY:hintergrund[Y][X]);

        g.fillRect(X*zoom,Y*zoom,zoom,zoom);
        //    g.setColor(Color.BLACK);
        //    g.drawRect(xPos,yPos,width,height);
      } // end of for
    } // end of for
    if (rahmen){
      g.setColor(Color.BLACK);
      for (int X=0;X<hintergrund[0].length ;X++ ) {
        for (int Y=0;Y<hintergrund.length ;Y++ ) {
          g.drawRect(X*zoom,Y*zoom,zoom,zoom);
        } // end of for
      } // end of for
    }

    //    for (Enemy E:Enemys) {//malt enemys
    //      E.paint(g);
    //    } // end of for

    paintenemy(g);
    painttower(g);
    paintparticle(g);
  }

  public void paintparticle(Graphics g){
    for (int i=0;i<Pground.Particles.size();i++ ) {
      Particle P=Pground.Particles.get(i);
      draw(P.G,g);
     //if(P instanceof line){}//TODO
    }
  }

  public void paintenemy(Graphics g){
    for (int i=0;i<Pground.Enemys.size();i++ ) {
      Enemy E=Pground.Enemys.get(i);
      if (E.aktiv) {
        g.setColor(Color.YELLOW);
        //g.fillRect((int)((position.x-0.3)*zoom),(int)((position.y-0.3)*zoom),(int)(2*zoom*0.3),(int)(2*zoom*0.3));  //quadrat
        g.fillOval((int)((E.position.x-0.3)*zoom),(int)((E.position.y-0.3)*zoom),(int)(2*zoom*0.3),(int)(2*zoom*0.3));    //kreis
      } // end of for
    }
  }

  public void painttower(Graphics g){
    for (int i=0;i<Pground.Towers.size();i++ ) {
      Tower T=Pground.Towers.get(i);

        g.setColor(Color.BLUE);
        //g.fillRect((int)((position.x-0.3)*zoom),(int)((position.y-0.3)*zoom),(int)(2*zoom*0.3),(int)(2*zoom*0.3));  //quadrat
        g.fillOval((int)((T.position.x-0.3)*zoom),(int)((T.position.y-0.3)*zoom),(int)(2*zoom*0.3),(int)(2*zoom*0.3));    //kreis
      } // end of for
    }

    public void draw(line Linie,Graphics g){g.drawLine((int)(Linie.P1.x*zoom),(int)(Linie.P1.y*zoom),(int)(Linie.P2.x*zoom),(int)(Linie.P2.y*zoom));}

  public void draw(grafikelemente G,Graphics g){
    if(G instanceof line){draw((line)G,g);}

  }
} // end of class Playgroundanzeige

