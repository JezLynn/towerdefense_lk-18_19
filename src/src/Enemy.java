/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 12.12.2018
  * @author 
  */
 import grafikelemente.Grafikelemente;

 import java.awt.Graphics;
 import java.awt.Color; 
public class Enemy implements pos{
  //  boolean active=false;
  Grafikelemente G;

  int speed=1;
  double schrittweite=0.05;
  int zoom=10;
  
  Punkt position; //position
  int HP=100;
  boolean ziel=false;
  boolean dead=true;
  boolean aktiv=true;

  public Punkt getPos(){return this.position;}
  //char[][] level;
  Enemy(double x,double y){//,char[][] level){
   position=new Punkt(x,y);//this.level=level;
//  this.x=x;this.y=y;
  }
  public void update(){
    if(HP<=0){
      aktiv=false;
      dead=true;
    }
  }
  public int getxpixel(){return (int)(zoom*position.x); }//position in pixeln
  public int getypixel(){return (int)(zoom*position.y); }

  
  public void move(char[][] level){ //bewegt den enemy
    //    if (!active) {
    //      return false;
    //    } // end of if
    for (int i=0;i<speed ;i++ ) {
      //System.out.println(1.0*position.x/zoom+"x y"+1.0*position.y/zoom);
      int X=(int)(position.x);
      int Y=(int)(position.y);
      Vektor v=new Vektor();
     
      double dx=-2*(position.x-X-0.5)*schrittweite; // sorgt daf�r das enemy in der mitte des weges ist
      double dy=-2*(position.y-Y-0.5)*schrittweite;
  
//      System.out.println(level[Y][X]);
      switch (level[Y][X]) {
        case  '>'://position.x+=schrittweite;position.y+=dy;
        v.sety(true,dy,schrittweite);     
        break;
        case  '<'://position.x-=schrittweite;position.y+=dy;  
        v.sety(false,dy,schrittweite);   
        break;
        case  'v'://position.y+=schrittweite;position.x+=dx;  
        v.setx(dx,true,schrittweite);
        break;
        case  '^'://position.y-=schrittweite;position.x+=dx;   
        v.setx(dx,false,schrittweite);
        break;
//        break;
        case  'X':ziel=true;aktiv=false;
        break;
        default:
        
        
        char c;
        
        c=level[Y+1][X];
        if(c=='>'||c=='<'||c=='v'||c=='^'){ v.setx(dx,true,schrittweite);break;}
        c=level[Y-1][X];
        if(c=='>'||c=='<'||c=='v'||c=='^'){ v.setx(dx,false,schrittweite);break;}
        c=level[Y][X+1];
        if(c=='>'||c=='<'||c=='v'||c=='^'){v.sety(true,dy,schrittweite);     break;}
        c=level[Y][X-1];
        if(c=='>'||c=='<'||c=='v'||c=='^'){v.sety(false,dy,schrittweite);     break;}
        break;   
      
      } // end of switch
            
        position.add(v);

    } // end of for
    
  }
  
}
