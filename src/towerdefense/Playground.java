package towerdefense; /**
 * Beschreibung
 *
 * @version 1.0 vom 12.12.2018
 * @author Konstantin Bachem
 */

import java.util.ArrayList;
import java.awt.Color;
import java.io.*;                                               //dateilesen

public class Playground {
    int startx, starty;                                         //Startpunkt für Enemys
    int zoom = 50;                                              //umrechnungsfaktor von koordinaten in pixel
    int textpositiony, textpositionx;           //postion der stats abhängig der Feldgröße
    char[][] level;                                             //codiertes Feld
    public ArrayList<Enemy> Enemys = new ArrayList<Enemy>();           //Liste der Enemys
    public ArrayList<Tower> Towers = new ArrayList<Tower>();           //Liste der Türme
    public ArrayList<Particle> Particles = new ArrayList<Particle>();  //Liste der Particles
    public Player Me;

    /**
     * Playground wird initialisiert
     * nimmt ein 2D char Array an was das level darstellt
     */
    Playground(char[][] c) {


        this.level = c;
        for (int X = 0; X < level[0].length; X++) {             //sucht Spawnpunkt auf dem Level
            for (int Y = 0; Y < level.length; Y++) {  

                if (c[Y][X] == 'S') {
                    startx = X;
                    starty = Y;

                }

            }
        }
    }


    public void newWave(int enemyCount, Mygui2 gui) {
        textpositiony=gui.height;
        textpositionx=gui.width;
        int i = 0;
        System.out.println("New Wave Confirmed!");

        while (true) {
            int j=0;
            while (i < enemyCount) {
                //System.out.println("Horray!");

                if (j++ >= 40) {
                    this.add(this.newenemy());
                    //System.out.println("New Enenmy!");
                    i++;
                    j=0;
                }
                this.update();
                gui.repaint();

                try {
                    Thread.sleep(30);
                } catch (Exception e) {
                    System.out.println("Got interrupted!");
                }
            }
            this.update();
            gui.repaint();

            try {
                Thread.sleep(30);
            } catch (Exception e) {
                System.out.println("Got interrupted!");
            }
            if (Enemys.size() == 0) break;
        }
    }

    /**
     * erzeugt ein Enemy auf der Position des Spawns
     */
    public Enemy newenemy() {
        Enemy E = new Enemy(startx + 0.5, starty + 0.5);
        E.speed = 1;
        return E;
    }
    public Enemy newenemy(String shape) {
        Enemy E = new Enemy(startx + 0.5, starty + 0.5,shape);
        E.speed = 1;
        return E;
    }

    /**
     * fügt ein Enemy in die Liste der Enemys ein, die auf dem Spielfeld angezeigt werden.
     */
    public void add(Enemy E) {
        Enemys.add(E);
    }

    /**
     * fügt ein Tower in die Liste der Towers ein, die auf dem Spielfeld angezeigt wird.
     */
    public void add(Tower T) {
        Towers.add(T);
    }

    /**
     * Gibt Array von Zeilen in der Datei zurück
     */
    public static String[] dateilesen(String pfad) {
        try {
            ArrayList<String> datei = new ArrayList<String>();//Neue Liste für die Zeilen

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pfad), "UTF-8")); //Reader initialisierung
            String zeile = "";                          //zeile wird zurück gesetzt
            while ((zeile = br.readLine()) != null) {   //solange zeilen vorhanden sind 
                datei.add(zeile);                       //werden Zeilen an Liste angehängt
            }
            br.close();                                 //Reader wird geschlossen
            return datei.toArray(new String[datei.size()]); //Liste wird zu array umgeformt und zurück gegeben
        } catch (IOException e) {                       //Falls die datei nicht vorhanden ist.
            e.printStackTrace();
        }
        return null;                                    //Falls Datei leer ist wir nichts zurück gegeben
    }


    /**
     * updatet und bewegt enemys
     * enemys im ziel werden entfernt
     */
    public void moveenemys() {
        for (int i = 0; i < Enemys.size(); i++) {       //für jedes Enemy
            Enemy E = Enemys.get(i);                    //aktuell bearbeitetes Enemy
            E.move(level);                              //Enemy wird mit der Levelstruktur zum Bewegen aufgerufen
            if (E.aktiv == false) {                     // Sonderfälle mit Enemys
                if (E.dead) {                           //wenn Enemy tot
                    Me.addGeld(10);
                }
                if (E.ziel) {                           //wenn Enemy im Ziel
                    Me.decreaseLebenspunkte(10);
                }
                Enemys.remove(i);                       //wenn enemy im ziel ist wird er entfernt
                i--;
            }         
        } // end of for
        //    for (Enemy E:Enemys) {
        //      E.move(level);
        //    } // end of for
    }

    public void updatetowers() {                        //Towers werden geupdated
        for (int i = 0; i < Towers.size(); i++) {       //Für jeden Tower
            Tower T = Towers.get(i);                    //wird der aktuelle Turm angefordert
            T.update(Enemys);                           //und geupdated

        } // end of for
    }

    public void updateparticles() {                     //Particle werden geupdated
        for (int i = 0; i < Particles.size(); i++) {    //für jeden Particle
            Particle P = Particles.get(i);              //der Particle wird angefordert

            if (P.lifetime-- <= 0) {                    //Wenn die Lebenszeit abgelaufen
                Particles.remove(i);                    //wird der Particle entfernt
                i--;
            }

            //if(P instanceof towerdefense.grafikelemente.Line){}//TODO
        }
    }


    public void update() { //Dauerschleife die
        if(!Me.IsDeath()) {                     //Wenn der Player lebt
            moveenemys();                                   //Enemys bewegt
            updatetowers();                                 //Türme updated
            updateparticles();                              //und Particles updated
            Me.updatestats(textpositiony);
        }else{
            Punkt p= new Punkt(textpositionx/2,textpositiony/2-20);
            Me.showdeath(p);
        }
    }
} // end of class Playground



