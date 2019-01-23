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
    int zoom = 30;                                              //umrechnungsfaktor von koordinaten in pixel
    char[][] level;                                             //codiertes Feld
    Color[][] feld;                                             //Feld mit den Anzeigefarben
    ArrayList<Enemy> Enemys = new ArrayList<Enemy>();           //Liste der Enemys
    ArrayList<Tower> Towers = new ArrayList<Tower>();           //Liste der Türme
    ArrayList<Particle> Particles = new ArrayList<Particle>();  //Liste der Particles

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

                } // end of if

            } // end of for
        } // end of for
    }

    /**
     * erzeugt ein Enemy auf der Position des Spawns
     */
    public Enemy newenemy() {
        Enemy E = new Enemy(startx + 0.5, starty + 0.5);
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
                }
                if (E.ziel) {                           //wenn Enemy im Ziel
                }                     //TODO zieht dem player hp ab
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

    public void update() {                              //Dauerschleife die
        moveenemys();                                   //Enemys bewegt
        updatetowers();                                 //Türme updated
        updateparticles();                              //und Particles updated

    }
} // end of class Playground



