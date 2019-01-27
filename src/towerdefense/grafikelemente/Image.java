package towerdefense.grafikelemente;


import towerdefense.Punkt;

import java.awt.*;

/*
 *Ein Image Element
 */
public class Image extends Grafikelemente {
    public Punkt position;
    public java.awt.Image png;
    public int size;


    public Image(Punkt position, String path, int size){
        this.position=position;
        Toolkit t=Toolkit.getDefaultToolkit();
        png=t.getImage(path);
        this.size=size;
    }
}