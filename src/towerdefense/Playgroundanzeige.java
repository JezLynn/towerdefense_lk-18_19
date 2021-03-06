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
    public Color[][] hintergrund;
    public int zoom=49;
    public Playground Pground;



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
        String[] datei = Playground.dateilesen("Levels/level_01.txt");
        
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
        }
        Playground myPGround = new Playground(level);

        Mygui2 gui = new Mygui2("Swolodefense", level.length*myPGround.zoom, level[0].length*myPGround.zoom+100);//erstelle ein FEnster mit den Maßen des Arrays

        boolean isLevelGood = isLevelPlayable(level);
        System.out.println("Is the Level playable?: " + isLevelGood);
        if (!isLevelGood) System.exit(42);


        myPGround.Me = new Player(100,100);
        Playgroundanzeige myAnzeige = new Playgroundanzeige(myPGround);
        gui.add(myAnzeige);
        myAnzeige.updateUI();


        Tower T = new Tower(new Punkt(5.5, 4.5), myPGround);

        myPGround.add(T);

        while (true) {
            for (int i=1; i <= 100; i++) {
                System.out.println("------------------");
                System.out.println("New Wave incoming!!");
                System.out.println("WaveCount: " + i);
                System.out.println("EnemyCount: " + i*2);
                myPGround.newWave(i*2, gui);

                try {
                    //print something here
                    Thread.sleep(1000); //sleep
                    //print something else here
                } catch (InterruptedException e) {
                    System.out.println("Got interrupted!");
                }
            }
        }
    }

    private static boolean isLevelPlayable(char[][] level) {

        boolean good = false;
        boolean spawnExists = false;
        int[] spawnCoords = new int[2];
        boolean goalExists = false;
        boolean pathExists = false;
        boolean placeAbleExists = false;


        for (int i=0; i < level.length; i++) {
            if (level[i].length != level[0].length) {System.out.println("Broke!!!"); break;}

            for (int j=0; j < level[i].length; j++) {
                if (("S").equals(String.valueOf(level[i][j]))) {
                    spawnExists = true;
                    spawnCoords[0] = i;
                    spawnCoords[1] = j;
                }
                if (("X").equals(String.valueOf(level[i][j]))) goalExists = true;
                if (("_").equals(String.valueOf(level[i][j]))) placeAbleExists = true;
            }
        }

        if (spawnExists) { pathExists = tracePath(level, spawnCoords, null); }
        if (spawnExists && goalExists && pathExists && placeAbleExists)  good = true;

        System.out.println("Does a Spawn exist?: " + spawnExists);
        System.out.println("Does a Goal exist?: " + goalExists);
        System.out.println("Does a path exist?: " + pathExists);
        System.out.println("Does a place for a Tower exist?: " + placeAbleExists);

        return good;
    }
    private static boolean tracePath(char[][] level, int[] aktCoords, int[] letztes) {

        System.out.println("------------------------");
        System.out.println("AktCoords - x: " + aktCoords[1] + " y: " + aktCoords[0]);

        if (("S").equals(String.valueOf(level[aktCoords[0]][aktCoords[1]]))) {
            if (tracePath(level, new int[]{aktCoords[0] + 1, aktCoords[1]}, aktCoords) ||
                    tracePath(level, new int[]{aktCoords[0] - 1, aktCoords[1]}, aktCoords) ||
                    tracePath(level, new int[]{aktCoords[0], aktCoords[1] + 1}, aktCoords) ||
                    tracePath(level, new int[]{aktCoords[0], aktCoords[1] - 1}, aktCoords)) {
                System.out.println("Spawn found, proceeding...");
                return true;
            }
        } else if (aktCoords[0] == letztes[0] && aktCoords[1] == letztes[1]) return false;
        else if (("X").equals(String.valueOf(level[aktCoords[0]][aktCoords[1]]))) return true;
        else if (("<").equals(String.valueOf(level[aktCoords[0]][aktCoords[1]]))) return tracePath(level, new int[]{aktCoords[0], aktCoords[1] - 1}, aktCoords);
        else if ((">").equals(String.valueOf(level[aktCoords[0]][aktCoords[1]]))) return tracePath(level, new int[]{aktCoords[0], aktCoords[1] + 1}, aktCoords);
        else if (("V").equals(String.valueOf(level[aktCoords[0]][aktCoords[1]]))) return tracePath(level, new int[]{aktCoords[0] + 1, aktCoords[1]}, aktCoords);
        else if (("^").equals(String.valueOf(level[aktCoords[0]][aktCoords[1]]))) return tracePath(level, new int[]{aktCoords[0] - 1, aktCoords[1]}, aktCoords);

        return false;
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
                    case 'V':
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
        paintstats(g);
        paintdeath(g);
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

    private void paintstats(Graphics g){
        Player Me =Pground.Me;
        g.setColor(Color.BLACK);
        draw(Me.G,g);
    }
    private void paintdeath(Graphics g){
        Player Me =Pground.Me;
        g.setColor(Color.RED);
        draw(Me.D,g);
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
        } else if (G instanceof  text){
            text Text =(text) G;
            g.drawString(Text.content,(int)Text.position.x,(int)Text.position.y);
        }
    }
} // end of class Playgroundanzeige

