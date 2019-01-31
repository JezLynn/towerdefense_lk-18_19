package towerdefense; /**
  *
  * Beschreibung Objekt eines Turms mit allen wichtigen Attributen und Funktionen/Methoden
  *
  * @version 1.0 vom 12.12.2018
  * @author Konstantin Bachem
  */
   
import towerdefense.grafikelemente.*;

import java.util.ArrayList;

public class Tower {
  // Anfang Attribute
  Playground playground;
  public Grafikelemente G;
  public String path="Grafiken/tower.png";

  private double range;             //Reichweite des Turms
  int reload;               //Nachladezeit des  Turms in ticks
  int damage;              //Staerke des Turms pro Hit
  
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
  Tower(Punkt position,Playground playground) {
    this.position=position;
    this.playground=playground;
    this.range=4.0;
    this.reload=5;
    this.damage=10;
    try {
      this.G = new Image(position, path, 1);
    }catch( Exception e){
      e.printStackTrace();
      this.G=new Circle(position,0.3);
    }
  }
  
  // Anfang Methoden
  /**
    *wenn laden > reload (wenn lange genug nachgeladen worden ist) wird geschossen
    *
    *@param Enemys Eine Liste von Enemys die erreicht werden können
    */
  public void update(ArrayList<Enemy> Enemys){
    if(laden>reload){

      //neues target wird gesucht wenn es existiert/aktiv ist/in reichweite ist
      if(target == null || !target.aktiv || !inrange(target)){
          ArrayList<Enemy> Enemyinrange = inrange(Enemys);
          if(Enemyinrange!=null) { target=Enemyinrange.get(0); }
          else{target=null;}
      }

      //auf das target wird geschossen
      if(target!=null && inrange(target)) {
        shoot();
        //playground.Particles.add(new Particle(10,new Line(position,target.position.copy())));
        playground.Particles.add(new Particle(10,new Line(position,target.position)));
        laden=0;
      }                                                      
    } else { laden++; }
  }
  public void shoot(){
    target.HP -= damage;
    System.out.println(target.HP);
    target.update();
  }

  
  /**
    * Gibt eine Liste zurueck, die aus den Enemys besteht,
    * die ein Tower von seiner Position aus mit seinem Radius erreichen kann.
    *
    *@param Enemys Eine Liste aller Enemys auf dem Playground
    *@return ArrayList<Enemy> Eine Liste aller Enemys die erreichbar sind
    */
  public ArrayList<Enemy> inrange( ArrayList<Enemy> Enemys){
    ArrayList<Enemy> Enemyinrange = new ArrayList<Enemy>();
    for (Enemy enemy : Enemys) {
      if (enemy.position.distance(this.position) < this.range) {
        Enemyinrange.add(enemy);
      }
    }
    if(Enemyinrange.size()==0) { return null; }
    else { return Enemyinrange;}
  }
  
  /**
    * Gibt wahr zurueck, wenn ein Enemy von einer innerhalb der Reichweite des Turms ist
    *
    *@param E zu ueberpruefender Enemy
    *@return boolean liegt E in der Reichweite des Turms?
    */
  public boolean inrange(Enemy E){
    return (E.aktiv && E.position.distance(this.position)<this.range );
  }
  // Ende Methoden
} // end of Tower
