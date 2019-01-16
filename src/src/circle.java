public class circle extends hitbox {
double r=0.3;//radius
    circle(pos entety) {
        super(entety);
    }
    circle(pos entety,double radius) {
        super(entety);
        this.r=radius;
    }
     boolean enthaelt(Punkt P){
        return P.distance(entety.getPos())<r;
     }

     boolean ueberschneidet(hitbox H){
        if(H instanceof circle){
            return ueberschneidet((circle)H);
        }
        return false;//todo
     }
    boolean ueberschneidet(circle C){
            return C.getPos().distance(this.getPos())<C.r+this.r;
        }


}
