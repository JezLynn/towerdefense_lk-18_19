package grafikelemente;



public class Circle extends Grafikelemente {

    public double radius;
    public Punkt position;
    boolean filled=true;

    Circle(Punkt Position, double radius){
        this.radius=radius;
        this.position=Position;
    }
}
