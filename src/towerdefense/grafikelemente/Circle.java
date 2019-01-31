package towerdefense.grafikelemente;

import towerdefense.Punkt;

public class Circle extends Grafikelemente {

    public double radius;
    public Punkt position;
    boolean filled=true;

    public Circle(Punkt Position, double radius){
        this.radius=radius;
        this.position=Position;
    }
}
