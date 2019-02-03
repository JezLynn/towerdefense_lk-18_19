package towerdefense;
/**
  *
  * Beschreibung:
  * Die Hauptklasse für einen Enemy
  *
  * @version 1.0 vom 12.12.2018
  * @author 
  */
 import towerdefense.grafikelemente.*;

public class Enemy implements pos{
 
  Grafikelemente G; //Welche Grafik hat der Enemy
  private String path="Grafiken/kylo.png"; //Dateipfad für die Position des Bildes
  

  int speed=1;  //Geschwindigkeit der Schritte
  double schrittweite=0.05;  //Länge der Schritte
 
  
  Punkt position;  //Speichere die Position
  int HP;
  boolean ziel=false; //ist das Ziel erreicht
  boolean dead=false;
  boolean aktiv=true;
  int value;   //Schaden den der Enemy macht und Geld beim Tot
  
  /**
  * Konstruktor des Enemys
  * Parameter Koordinaten für Position
  */
  Enemy(double x,double y){
   position=new Punkt(x,y);
   value=10;
   HP=100;
   try {
       this.G = new Image(position, path, 1);
   }catch( Exception e){
       e.printStackTrace();
       this.G=new Circle(position,0.3);
   }
  }

  Enemy(double x,double y,String shape){
      position=new Punkt(x,y);
      value=10;
      HP=100;
      switch (shape) {
          case "kylo":
              try {
                this.G = new Image(position, path, 1);
              } catch (Exception e) {
                e.printStackTrace();
                this.G = new Circle(position, 0.3);
              }
              break;
          case "Circle": case "C":
                this.G = new Circle(position, 0.3);
                break;
      }
  }
 
  /**
  *Get-Methode für die Position
  */
  public Punkt getPos(){
   return this.position;
  }
 
  /**
  * Teste ob der Enemy tot ist
  */
  public void update(){
    if(HP<=0){
      aktiv=false;
      dead=true;
    }
  }
 


  /**
  * bewegt den Enemy abhängig von den Pfeilen auf dem Playground
  *
  *Parameter @level enthält einen char mit der Karte mit Pfeilen für die Bewegungsrichtung des Enemys 
  */
  public void move(char[][] level){ 
    for (int i=0;i<speed ;i++ ) {

      int X=(int)(position.x);
      int Y=(int)(position.y);
      Vektor v=new Vektor(); //wohin geht der Enemy
     
      double dx=-2*(position.x-X-0.5)*schrittweite; // Enemy bewegt sich richtung Mitte des Weges
      double dy=-2*(position.y-Y-0.5)*schrittweite;
  
      switch (level[Y][X]) { //für jedes Zeichen auf dem der Enemy ist wird eine Richtung bestimmt
        case  '>':
         v.sety(true,dy,schrittweite);
         break;
        case  '<':
         v.sety(false,dy,schrittweite);
         break;
        case  'V':
         v.setx(dx,true,schrittweite);
         break;
        case  '^':
         v.setx(dx,false,schrittweite);
         break;
        case  'X':ziel=true;aktiv=false; //beim Ziel muss der Enemy sich nicht mehr bewegen
         break;
        default:
        
        
        char c; //angrenzendes Feld, da beim Anfang die Richtung unbekannt ist
        
        c=level[Y+1][X]; //für jedes angrenzende Feld wird getestet ob es eine Richtung ist
        if(c=='>'||c=='<'||c=='v'||c=='^'){
         v.setx(dx,true,schrittweite);
         break;
        }
        c=level[Y-1][X];
        if(c=='>'||c=='<'||c=='v'||c=='^'){
         v.setx(dx,false,schrittweite);
         break;
        }
        c=level[Y][X+1];
        if(c=='>'||c=='<'||c=='v'||c=='^'){
         v.sety(true,dy,schrittweite);     
         break;
        }
        c=level[Y][X-1];
        if(c=='>'||c=='<'||c=='v'||c=='^'){
         v.sety(false,dy,schrittweite);     
         break;
        }
        break;   //hier muss eine Fehlermeldung rein
      
      } // end of switch
            
        position.add(v);

    } // end of for
    
  }
  
}
