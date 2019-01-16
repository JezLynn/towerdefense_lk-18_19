public abstract class hitbox {
pos entety;
public Punkt getPos(){return entety.getPos();}

abstract boolean enthaelt(Punkt P);
abstract boolean ueberschneidet(hitbox H);
hitbox(pos entety){this.entety=entety;}
}
