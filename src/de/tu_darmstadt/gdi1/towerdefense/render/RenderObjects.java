/**
 * 
 */
package de.tu_darmstadt.gdi1.towerdefense.render;

import java.awt.Point;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import de.tu_darmstadt.gdi1.towerdefense.classes.GameEngine;
import de.tu_darmstadt.gdi1.towerdefense.tower.Tower;

/**
 * <h1>RenderObjects</h1><br>
 * <li>looks up the piece in the HashMap and get its Image</li>
 * @author Michael Schlittenbauer
 */
public class RenderObjects {

	Image image; //the image of the piece
	int x;	//the x Position
	int y; //the y Position

	/**
	 * Constructor of the Class
	 * 
	 * @param image the Image to be rendered
	 * @param x the x Position
	 * @param y the y Position
	 */
	public RenderObjects(Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;

	}
	
	/**
	 * Get the Image of the RenderMap Object
	 * 
	 * @return the image of the RenderMap Object
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Get x of the RenderMap Object
	 * 
	 * @return x of the RenderMap Object
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get y of the RenderMap Object
	 * 
	 * @return y of the RenderMap Object
	 */
	public int getY() {
		return y;
	}

	/**
	 * looks up the given piece in the HashMap, get its Image
	 * and its Coordinates
	 * 
	 * @param Map the HashMap with all Pieces and Positions
	 * @param objectSheet	the SpriteSheet with all subImages
	 * @param iName	the Name searching for
	 * @param scale	the scale of the field
	 * 
	 * @return RenderMap with Image and Coordinates
	 */
	public static RenderObjects render(Map<String, Point> Map,
			SpriteSheet objectSheet, SpriteSheet towerSheet, String iName, float scale) {
		int x = (int) Map.get(iName).getX();
		int y = (int) Map.get(iName).getY();
		RenderObjects object = null; 
		
		if (iName.indexOf("monster", 0) != -1) { //search for the Monster
			if ((GameEngine.monsterMap.get(iName)).getSpeed() < 2) { //how fast is the Monster
				object =  new RenderObjects(objectSheet.getSubImage(0, 0), (int) (x * scale), (int) (y * scale));
			} else if ((GameEngine.monsterMap.get(iName)).getSpeed() < 5){
				object =  new RenderObjects(objectSheet.getSubImage(0, 1), (int) (x * scale), (int) (y * scale));
			} else 	object = new RenderObjects(objectSheet.getSubImage(1, 0), (int) (x * scale), (int) (y * scale));
			
			
		} else if(iName.indexOf("tower", 0) != -1){
			Tower t = GameEngine.towerMap.get(iName);
			
			if(t.type == 't'){
				if(t.getRank()==1){
				object = new RenderObjects(towerSheet.getSubImage(0, 0), (int) (x * scale), (int) (y * scale));
				}
				if(t.getRank()==2){
					object = new RenderObjects(towerSheet.getSubImage(1, 0), (int) (x * scale), (int) (y * scale));
					}
				if(t.getRank()==3){
					object = new RenderObjects(towerSheet.getSubImage(2, 0), (int) (x * scale), (int) (y * scale));
					}
				if(t.getRank()==4){
					object = new RenderObjects(towerSheet.getSubImage(3, 0), (int) (x * scale), (int) (y * scale));
					}
			}else if(t.type == 'i'){
				if(t.getRank()==1){
				object =  new RenderObjects(towerSheet.getSubImage(0, 1), (int) (x * scale), (int) (y * scale));
				}
				if(t.getRank()==2){
					object =  new RenderObjects(towerSheet.getSubImage(1, 1), (int) (x * scale), (int) (y * scale));
					}
				if(t.getRank()==3){
					object =  new RenderObjects(towerSheet.getSubImage(2, 1), (int) (x * scale), (int) (y * scale));
					}
				if(t.getRank()==4){
					object =  new RenderObjects(towerSheet.getSubImage(3, 1), (int) (x * scale), (int) (y * scale));
					}
			}else if(t.type == 'p'){
				if(t.getRank()==1){
				object =  new RenderObjects(towerSheet.getSubImage(0, 2), (int) (x * scale), (int) (y * scale));
				}
				if(t.getRank()==2){
					object =  new RenderObjects(towerSheet.getSubImage(1, 2), (int) (x * scale), (int) (y * scale));
					}
				if(t.getRank()==3){
					object =  new RenderObjects(towerSheet.getSubImage(2, 2), (int) (x * scale), (int) (y * scale));
					}
				if(t.getRank()==4){
					object =  new RenderObjects(towerSheet.getSubImage(3, 2), (int) (x * scale), (int) (y * scale));
					}
			}
		} else if(iName.indexOf("hit", 0)!=-1){
			object = new RenderObjects(objectSheet.getSubImage(0, 2), (int) (x * scale), (int) (y * scale));
		}
		return object;
	}
}
