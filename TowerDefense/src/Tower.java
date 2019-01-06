/**
  *
  * Beschreibung Objekt eines Turms mit allen wichtigen Attributen und Funktionen/Methoden
  *
  * @version 1.0 vom 12.12.2018
  * @author Konstantin Bachem
  */
   
import java.util.ArrayList;
public class Tower {
  
  // Anfang Attribute
  double range=2;             //Reichweite des Turms
  int reload=100;             //Nachladezeit des  Turms in ticks
  int damage=50;              //Staerke des Turms pro Hit
  
  Punkt position;             // Punkt der die Position des Turms definiert
  int zoom;                   // evtl noch brauchbar
  
  int laden;                  //wenn laden > reload wird geschossen
  Enemy target;               //Aktuell angezieltes Enemy
  // Ende Attribute
  
  
  /**
    *Tower wird an einer Position erstellt
    *
    *@param position Ein Punkt an dem der Turm erstellt wird 
    */
  
  Tower(Punkt position) {
    this.position=position;
  }
  
  // Anfang Methoden
  /**
    *wenn laden > reload (wenn lange genug nachgeladen worden ist) wird geschossen
    *
    *@param Enemys Eine Liste von Enemys die erreicht werden k�nnen
    */
  public void shoot( ArrayList<Enemy> Enemys){
    if(++laden>reload){
      laden=0;
      if(inrange(target)){}                                                     //TODO
      
    }
  }
  
  /**
    *Gibt eine Liste zurueck, die aus den Enemys besteht die ein Tower von seiner Position aus mit seinem Radius erreichen kann.
    *
    *@param Enemys Eine Liste aller Enemys auf dem Playground
    *@return ArrayList<Enemy> Eine Liste aller Enemys die erreichbar sind
    */
  
  public ArrayList<Enemy> inrange( ArrayList<Enemy> Enemys){
    ArrayList<Enemy> Enemyinrange=new ArrayList<Enemy>();
    for (Enemy enemy : Enemys) {
      if (enemy.position.distance(this.position) < this.range) Enemyinrange.add(enemy);
    } // end of for
    return Enemyinrange;
  }
  
  /**
    *Gibt wahr zurueck, wenn ein Enemy von einer Position
    *
    *@param E zu ueberpruefender Enemy
    *@return boolean liegt E in der Reichweite des Turms?
    */
  public boolean inrange(Enemy E){
    return (E.position.distance(this.position)<this.range && E.aktiv);
  }
  // Ende Methoden
} // end of Turm
