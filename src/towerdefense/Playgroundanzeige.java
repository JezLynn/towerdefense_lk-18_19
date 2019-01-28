package towerdefense;
/**
 * Zeigt alle Elemente an
 *
 * @version 1.1 vom 23.1.2018
 * @author Konstantin Bachem
 */

import towerdefense.grafikelemente.*;
import towerdefense.grafikelemente.Image;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.io.*; // dateilesen

public class Playgroundanzeige extends JPanel {
    Color[][] hintergrund;
    int zoom = 23;
    Playground Pground;



    /**
    * Erstellt eine neue Playgroundanzeige mit dem {@link Playground} ground
    * und aktualisiert die Farben für die Anzeige 
    *
    * @param ground {@link Playground}
    */
    Playgroundanzeige(Playground ground) {
        this.Pground = ground;
        levelupdate(ground.level);
    }

    
    public static void main(String[] args) {
        Mygui2 gui = new Mygui2();
        String[] datei = dateilesen("Levels/level_01.txt");
        
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

        char[][] level = new char[datei.length][]; //eingelesenes level als char array
        for (int i = 0; i < datei.length; i++) {
            level[i] = datei[i].toCharArray();
            System.out.println(datei[i]);
        } // end of for
        //System.out.println(datei.length);

        Playground myPGround = new Playground(level);
        //System.out.println(myPGround.startx+" "+myPGround.starty);
        //    gui.s
        myPGround.zoom = 23;
        //    hex.zoom=50;
        Playgroundanzeige myAnzeige = new Playgroundanzeige(myPGround);
        gui.add(myAnzeige);
        myAnzeige.updateUI();


        Tower T = new Tower(new Punkt(2.5, 2.5), myPGround);
        T.range = 5;
        T.reload = 5;

        myPGround.add(T);

        int i = 100;
        while (true) {
            if (--i < 0) {
                i = 100;
                myPGround.add(myPGround.newenemy());
            }
            myPGround.update();
            gui.repaint();
            //      if (Math.random()>0.95) {
            //        myPGround.add(myPGround.newenemy());
            //      } // end of if
            //      myPGround.add(myPGround.newenemy());}
            try {
                //print something here
                Thread.sleep(30); //sleep
                //print something else here
            } catch (InterruptedException e) {
                System.out.println("Got interrupted!");
            }
        } // end of for
    } // end of main

    /**
    * Liest die Datei am Pfad pfad in ein Array,
    * das die Zeilen beinhaltet, aus
    * 
    * @param pfad String
    * @return String[] datei
    * @throws IOException
    */
    public static String[] dateilesen(String pfad) {
        try {
            ArrayList<String> datei = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pfad), "UTF-8"));
            String zeile = "";
            while ((zeile = br.readLine()) != null) {
                datei.add(zeile);
            }
            br.close();
            return datei.toArray(new String[datei.size()]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * Aktualsiert die Farben des Hintergrundes,
    * je nach Zeichen des Levels
    * 
    * @param level char[][]
    */
    public void levelupdate(char[][] level) {
        hintergrund = new Color[level.length][level[0].length];
        for (int X = 0; X < level[0].length; X++) {
            for (int Y = 0; Y < level.length; Y++) {
                Color f = Color.orange;
                switch (level[Y][X]) {
                    case '#':
                        f = Color.BLACK;
                        break;
                    case '>':
                    case '<':
                    case 'v':
                    case '^':
                        f = Color.RED;
                        break;
                    case 'S':
                        f = Color.GREEN;
                        break;
                    case 'X':
                        f = Color.BLUE;
                        break;
                    case '_':
                        f = Color.WHITE;
                        break;
                    default:

                } // end of switch
                hintergrund[Y][X] = f;
            } // end of for
        } // end of for
    }

    /**
    * Aktualisiert die Grafiken auf der sichtbaren Oberfläche
    * (Von Swing ausgeführt)
    * 
    * @param g {@link Graphics}
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        painthintergrund(g);
        paintenemy(g);
        painttower(g);
        paintparticle(g);
    }
    
    /**
    * Aktualisiert den Hintergrund auf den sichtbaren Bereich
    * 
    * @param g {@link Graphics}
    */
    private void painthintergrund(Graphics g){
        boolean rahmen = true;
        for (int X = 0; X < hintergrund[0].length; X++) { //malt das feld
            for (int Y = 0; Y < hintergrund.length; Y++) {
                g.setColor(hintergrund[Y][X] == null ? Color.GRAY : hintergrund[Y][X]);

                g.fillRect(X * zoom, Y * zoom, zoom, zoom);
                g.setColor(Color.BLACK);
                g.drawRect(X * zoom, Y * zoom, zoom, zoom);
            } // end of for
        } // end of for
    }

    /**
    * Aktualisiert die Partikel auf den sichtbaren Bereich
    * 
    * @param g {@link Graphics}
    */
    private void paintparticle(Graphics g) {
        for (int i = 0; i < Pground.Particles.size(); i++) {
            Particle P = Pground.Particles.get(i);
            g.setColor(Color.BLUE);
            draw(P.G, g);
        }
    }

    /**
    * Aktualisiert die Enemys auf den sichtbaren Bereich
    * 
    * @param g {@link Graphics}
    */
    private void paintenemy(Graphics g) {
        for (int i = 0; i < Pground.Enemys.size(); i++) {
            Enemy E = Pground.Enemys.get(i);
            if (E.aktiv) {
                g.setColor(Color.YELLOW);
                draw(E.G, g);
            } // end of for
        }
    }

    /**
    * Aktualisiert die Türme auf den sichtbaren Bereich
    * 
    * @param g {@link Graphics}
    */
    private void painttower(Graphics g) {
        for (int i = 0; i < Pground.Towers.size(); i++) {
            Tower T = Pground.Towers.get(i);
            g.setColor(Color.BLUE);
            draw(T.G,g);
        } // end of for
    }

    /**
    * Zeichnet das eingegebene Grafikelement G auf die grafische Ebene g
    * 
    * @param G {@link Grafikelemente}
    * @param g {@link Graphics}
    */
    public void draw(Grafikelemente G, Graphics g) {
        if (G instanceof Line) {
            Line Linie = (Line) G;
            g.drawLine((int) (Linie.P1.x * zoom), (int) (Linie.P1.y * zoom), (int) (Linie.P2.x * zoom), (int) (Linie.P2.y * zoom));
        } else if (G instanceof Circle) {
            Circle Kreis = (Circle) G;
            g.fillOval((int) ((Kreis.position.x -Kreis.radius) * zoom), (int) ((Kreis.position.y -Kreis.radius) * zoom), (int) (2 * zoom * Kreis.radius), (int) (2 * zoom * Kreis.radius));
        } else if (G instanceof Image) {
            Image Bild =(Image) G;

            g.drawImage(Bild.png,(int)((Bild.position.x-Bild.ofsetx)* zoom),(int)((Bild.position.y-Bild.ofsety)* zoom),Bild.size* zoom,Bild.size* zoom,null);
            //g.drawImage(Bild.png,(int)Bild.position.x* zoom,(int)Bild.position.y* zoom,Bild.size,Bild.size,null);
        }
    }
} // end of class Playgroundanzeige

