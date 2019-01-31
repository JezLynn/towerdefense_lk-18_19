package towerdefense.grafikelemente;

import towerdefense.Punkt;

public class text extends Grafikelemente{
    public Punkt position;
    public String content;

    public text(Punkt Position, String content){
        this.content=content;
        this.position=Position;
    }
}
