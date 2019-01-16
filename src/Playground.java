/**
 * Beschreibung
 * Anzeige des Spielfeldes
 *
 * @version 1.0 vom 12.12.2018
 * @author Konstantin Bachem
 */

import java.util.ArrayList;
import java.awt.Color;
import java.io.*;//dateilesen

public class Playground {
    int startx, starty;
    int zoom = 30;//umrechnungsfaktor von koordinaten in pixel
    char[][] level;
    Color[][] feld;
    ArrayList<Enemy> Enemys = new ArrayList<Enemy>();
    ArrayList<Tower> Towers = new ArrayList<Tower>();
    ArrayList<Particle> Particles = new ArrayList<Particle>();

    /**
     *Playground wird initialisiert
     *nimmt ein 2D char Array an was das level darstellt
     */
    Playground(char[][] c) {


        this.level = c;
        for (int X = 0; X < level[0].length; X++) { //initialisiert das spielfeld d.h.setzt farben
            for (int Y = 0; Y < level.length; Y++) {

                if (c[Y][X] == 'S') {
                    startx = X;
                    starty = Y;
                    break;
                } // end of switch

            } // end of for
        } // end of for
    }

    /**
     * erzeugt ein Enemy für dieses spielfeld
     */
    public Enemy newenemy() {
        Enemy E = new Enemy(startx + 0.5, starty + 0.5);
        E.zoom = zoom;
        E.speed = 1;
        return E;
    }

    /**
     * fügt ein Enemy in dieses spielfeld ein
     */
    public void add(Enemy E) {
        Enemys.add(E);
    }

    /**
     * fügt ein Enemy in dieses spielfeld ein
     */
    public void add(Tower T) {
        Towers.add(T);
    }

    /**
     * fügt ein Turm in dieses spielfeld ein
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
     * updatet und bewegt enemys
     * enemys im ziel weden entfernt
     */
    public void moveenemys() {
        for (int i = 0; i < Enemys.size(); i++) {
            Enemy E = Enemys.get(i);
            E.move(level);
            if (E.aktiv == false) {
                if (E.dead) {
                }
                if (E.ziel) {
                }                     //TODO zieht dem player hp ab
                Enemys.remove(i);
                i--;
            } //wenn enemy im ziel ist wird er entfernt
        } // end of for
        //    for (Enemy E:Enemys) {
        //      E.move(level);
        //    } // end of for
    }

    public void updatetowers() {
        for (int i = 0; i < Towers.size(); i++) {
            Tower T = Towers.get(i);
            T.update(Enemys);

        } // end of for
    }

    public void updateparticles() {
        for (int i = 0; i < Particles.size(); i++) {
            Particle P = Particles.get(i);

            if (P.lifetime-- <= 0) {
                Particles.remove(i);
                i--;
            }

            //if(P instanceof line){}//TODO
        }
    }

    public void update() {
        moveenemys();
        updatetowers();
        updateparticles();

    }
} // end of class Playground



