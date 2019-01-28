package towerdefense.grafikelemente;


import towerdefense.Punkt;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/*
 *Ein Image Element
 */
public class Image extends Grafikelemente {
    public Punkt position;
    public java.awt.Image png;
    public int size;
    public double ofsetx=0,ofsety=0;



    public Image(Punkt position, String path, int size)throws java.io.IOException{
        this.position=position;
        //Toolkit t=Toolkit.getDefaultToolkit();
        //png=t.getImage(path);
        this.size=size;
        ofsetx=size/2.0;
        ofsety=size/2.0;
        png = ImageIO.read(new File(path));
    }
}