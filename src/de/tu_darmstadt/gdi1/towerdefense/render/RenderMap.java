/**
 * 
 */
package de.tu_darmstadt.gdi1.towerdefense.render;

import java.awt.Point;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * <h1>RenderMap</h1><br>
 * <li>looks up the piece in the HashMap and get its Image</li>
 * @author Michael Schlittenbauer
 */
public class RenderMap {
	
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
	public RenderMap(Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * Get the Image of the RenderMap Object
	 * 
	 * @return the image of the RenderMap Object
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Get x of the RenderMap Object
	 * 
	 * @return x of the RenderMap Object
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Get y of the RenderMap Object
	 * 
	 * @return y of the RenderMap Object
	 */
	public int getY(){
		return y;
	}

	/**
	 * looks up the given piece in the HashMap, get its Image
	 * and its Coordinates
	 * 
	 * @param Map the HashMap with all Pieces and Positions
	 * @param sheet	the SpriteSheet with all subImages
	 * @param iName	the Name searching for
	 * @param scale	the scale of the field
	 * 
	 * @return RenderMap with Image and Coordinates
	 */
	public static RenderMap render(Map<String, Point> Map, SpriteSheet sheet, String iName, float scale) {
				int x = (int) Map.get(iName).getX();
				int y = (int) Map.get(iName).getY();
				
				if (iName.indexOf("border", 0)!= -1) {
					if(iName.indexOf("border_ne", 0)!=-1){
					return new RenderMap(sheet.getSubImage(0, 6), (int) (x*scale), (int) (y*scale));
					}else if(iName.indexOf("border_se", 0)!=-1){
						return new RenderMap(sheet.getSubImage(1, 5), (int) (x*scale), (int) (y*scale));
					}else if(iName.indexOf("border_sw", 0)!=-1){
						return new RenderMap(sheet.getSubImage(1, 5).getFlippedCopy(true, false), (int) (x*scale), (int) (y*scale));
					}else if(iName.indexOf("border_nw", 0)!=-1){
						return new RenderMap(sheet.getSubImage(0, 6).getFlippedCopy(true, false), (int) (x*scale), (int) (y*scale));
					}else if(iName.indexOf("border_e", 0)!=-1 || iName.indexOf("border_w", 0)!=-1){
						return new RenderMap(sheet.getSubImage(2, 5), (int) (x*scale), (int) (y*scale));
					}else 
						return new RenderMap(sheet.getSubImage(0, 5), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("tower", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(0, 0), (int) (x*scale), (int) (y*scale));
				
				} else if (iName.indexOf("field", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(0, 0), (int) (x*scale), (int) (y*scale));

				}else if (iName.indexOf("flower", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(1, 0), (int) (x*scale), (int) (y*scale));
				
				}else if (iName.indexOf("tree", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(3, 5), (int) (x*scale), (int) (y*scale));
				
				} else if (iName.indexOf("finish_north", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(2, 4), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("finish_south", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(3, 4), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("finish_east", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(0, 4), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("finish_west", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(1, 4), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("start_north", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(2, 2), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("start_south", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(3, 2), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("start_east", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(1, 2), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("start_west", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(0, 2), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("cws", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(3, 1), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("cwn", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(0, 1), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("cen", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(1, 1), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("ces", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(2, 1), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("swe", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(3, 0), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("sns", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(2, 0), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("tcs", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(3, 3), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("tce", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(2, 3), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("tcw", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(1, 3), (int) (x*scale), (int) (y*scale));
					
				} else if (iName.indexOf("tcn", 0)!= -1) {
					return new RenderMap(sheet.getSubImage(0, 3), (int) (x*scale), (int) (y*scale));
					
				}else return null;
	}
}
