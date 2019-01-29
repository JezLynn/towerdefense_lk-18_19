/**
 * 
 */
package de.tu_darmstadt.gdi1.towerdefense.controller;

import java.awt.Point;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;

import de.tu_darmstadt.gdi1.towerdefense.classes.GameEngine;
import de.tu_darmstadt.gdi1.towerdefense.classes.GuiObject;
import de.tu_darmstadt.gdi1.towerdefense.tower.Tower;
import de.tu_darmstadt.gdi1.towerdefense.ui.GameState;

/**
 * <h1>ControllTower</h1><br>
 * <li>To set remove or upgrade Tower</li>
 * <li>To remove tree's on the Map</li>
 * @author Michael Schlittenbauer
 * @version 1.3 
 * 
 */
public class ControllTower {

	private Map<String, Point> map;
	private Map<String, Point> objects;
	private Point mouse;
	private GameEngine game;
	private SpriteSheet infoSheet;
	private SpriteSheet upgradeSheet;
	private Input in;
	public static Point currentTower;
	private static Point currentField;
	private static Point currentTree;
	private static String tree;
	public static String tower;

	/**
	 * Constructor of the Class
	 * 
	 * @param map Map of all Parts off the GameMap
	 * @param objects Map with all Objects in the Game
	 * @param mouse the actual mouse Position
	 * @param game the running GameEngine
	 * @param infoSheet the SpriteSheet with info images
	 * @param setTower the Input of the GameContainer
	 */
	public ControllTower(Map<String, Point> map, Map<String, Point> objects,
			Point mouse, GameEngine game, SpriteSheet infoSheet, SpriteSheet upgradeSheet, Input setTower) {
		this.map = map;
		this.objects = objects;
		this.mouse = mouse;
		this.game = game;
		this.infoSheet = infoSheet;
		this.upgradeSheet = upgradeSheet;
		this.in = setTower;
	}

	/**
	 * Check if the field valid to built a tower
	 * 
	 * @param scale the factor of scale of the images
	 * 
	 * @return true if the field is valid else false
	 */
	public boolean isFieldValid(float scale) {
		String field = GuiObject.getKey(map, mouse, scale);

		if (field != null && (field.indexOf("field", 0) != -1 || field.indexOf("flower", 0) != -1)) {
			currentField = GameState.Map.get(field);
			return true;
		} else
			return false;
	}
	
	/**
	 * Check if the field is a tree
	 * 
	 * @param scale the factor of scale of the images
	 * 
	 * @return true if the field is a tree
	 */
	public boolean isFieldTree(float scale) {
		String field = GuiObject.getKey(map, mouse, scale);

		if (field != null && field.indexOf("tree", 0) != -1) {
			currentTree = GameState.Map.get(field);
			tree = field;
			return true;
		} else
			return false;
	}

	/**
	 * Check if on the field is a Tower
	 * 
	 * @param scale the factor of scale of the images
	 * 
	 * @return true if there is a Tower else false
	 */
	public boolean isThereTower(float scale) {
		if(GameState.towerUgradeVisible!=true){
		String object = GuiObject.getKey(objects, mouse, scale);
		if (object != null && object.indexOf("tower", 0) != -1) {
			currentTower = GameState.Objects.get(object);
			tower = object;
			return true;
		} else
			return false;
		}else 
			return false;
	}
	
	/**
	 * To find which Tower on the Image is pointed with the mouse
	 * 
	 * @param oldMouse the mouse position at left-click
	 * @param width the width of the choose picture
	 * @param height the last y of the picture
	 * 
	 * @return return true if mouse inside this Tower, else false
	 */
	public boolean whichTower(Point oldMouse, int width, int height){
		return ((oldMouse.getX() <= mouse.getX() && oldMouse.getX() + width * 2 >= mouse.getX())
				&& (oldMouse.getY() <= mouse.getY() && oldMouse.getY() + height >= mouse.getY()));
	}


	/**
	 * to get the Position and SubImage for the tower info
	 * and set a tower if MouseButton left is pressed
	 * 
	 * @param oldMouse the mouse position at left-click
	 * @param width the width of the choose picture
	 * @param pictureSize the pictureSize of the map pictures
	 * 
	 * @return the info picture of the chosen Tower
	 */
	public Image infoField(Point oldMouse, int width, int pictureSize) {
		
		Image towerInfo = null;
		if(GameState.towerUgradeVisible != true && GameState.towerChooseVisible){
			if (whichTower(oldMouse, width, 50)) {
				//normal Tower
				GameState.towerInfoVisible = true;
				towerInfo = infoSheet.getSubImage(0, 1);
				
				if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON) && game.Gold-30>=0) {
					game.setTower((int) currentField.getY() / pictureSize,
							(int) currentField.getX() / pictureSize, 't');
					GameState.string = "To upgrade or delete a Tower just right click the Tower and choose the option";
					GameState.towerChooseVisible = false;
				}

			} else if (whichTower(oldMouse, width, 100)) {
				//icy Tower
				GameState.towerInfoVisible = true;
				towerInfo = infoSheet.getSubImage(1, 1);
				
				if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON) && game.Gold-60>=0) {
					game.setTower((int) currentField.getY()
							/ pictureSize,
							(int) currentField.getX()
									/ pictureSize, 'i');
					GameState.string = "To upgrade or delete a Tower just right click the Tower and choose the option";
					GameState.towerChooseVisible = false;
				}
				
			} else if (whichTower(oldMouse, width, 150)) {
				//poison Tower
				GameState.towerInfoVisible = true;
				towerInfo = infoSheet.getSubImage(2, 1);
				
				if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON) && game.Gold-45>=0) {
					game.setTower((int) currentField.getY()
							/ pictureSize,
							(int) currentField.getX()
									/ pictureSize, 'p');
					GameState.string = "To upgrade or delete a Tower just right click the Tower and choose the option";
					GameState.towerChooseVisible = false;
				}
			} else
				GameState.towerInfoVisible = false;
		}
			return towerInfo;
		
	}
	
	/**
	 * Checks if mouse is over button
	 * 
	 * @param button the x and y of the button
	 * 
	 * @return true if the mouse is over the button, else false
	 */
	public boolean overUpdateOrDelete(Point button){
		if((mouse.getX()>=button.getX() && mouse.getX()<=button.getX()+108)
				&& (mouse.getY()>=button.getY() && mouse.getY()<=button.getY()+20))
			return true;
			
		else return false;
	}
	
	/**
	 * To get the correct image and highlight the button
	 * 
	 * @param oldMouse the mouse position at left-click
	 * 
	 * @return the upgrade Image for the Tower with highlighted buttons
	 */
	public Image upgradeTower(Point oldMouse){
		Image tower = null;
		
		if(GameState.towerUgradeVisible){
			Point buttonU = new Point((int)oldMouse.getX(), (int)oldMouse.getY()+100);
			Point buttonD = new Point((int)oldMouse.getX(), (int)oldMouse.getY()+120);
			
			Tower t = GameEngine.towerMap.get(ControllTower.tower);
			
			if(t.getType() == 't'){
				if(t.getRank()==1){
					tower = upgradeSheet.getSubImage(0, 0);
					if(overUpdateOrDelete(buttonU)){
						tower = upgradeSheet.getSubImage(1, 0);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							
							game.UpgradeTower(t.getName());
							
							GameState.towerUgradeVisible=false;
							GameState.string = "Now try to remove a tree. Right click on a tree and choose Remove.";
							GameState.skip = "If your money is less than 15 you have to wait and shoot monster";
						}
					}else if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(2, 0);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.string = "Now try to remove a tree. Right click on a tree and choose Remove.";
							GameState.skip = "If your money is less than 15 you have to wait and shoot monster";
							GameState.towerUgradeVisible=false;
						}
					}
				}else if(t.getRank()==2){
					tower = upgradeSheet.getSubImage(0, 1);
					if(overUpdateOrDelete(buttonU)){
						tower = upgradeSheet.getSubImage(1, 1);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							
							game.UpgradeTower(t.getName());
							GameState.towerUgradeVisible=false;
							}
					}else if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(2, 1);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.towerUgradeVisible=false;
						}
					}
				}else if(t.getRank()==3){
					tower = upgradeSheet.getSubImage(0, 2);
					if(overUpdateOrDelete(buttonU)){
						tower = upgradeSheet.getSubImage(1, 2);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							
							game.UpgradeTower(t.getName());
							GameState.towerUgradeVisible=false;
							}
					}else if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(2, 2);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);GameState.towerUgradeVisible=false;
						}
					}
				}else if(t.getRank()==4){
					tower = upgradeSheet.getSubImage(3, 5);
						
						if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(5, 5);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.towerUgradeVisible=false;
							}
						}
					}
				
			}else if(t.getType() == 'i'){
				if(t.getRank()==1){
					tower = upgradeSheet.getSubImage(3, 0);
					if(overUpdateOrDelete(buttonU)){
						tower = upgradeSheet.getSubImage(4, 0);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							
							game.UpgradeTower(t.getName());
							
							GameState.towerUgradeVisible=false;
							GameState.string = "Now try to remove a tree. Right click on a tree and choose Remove.";
							GameState.skip = "If your money is less than 15 you have to wait and shoot monster";
						}
					}else if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(5, 0);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.string = "Now try to remove a tree. Right click on a tree and choose Remove.";
							GameState.skip = "If your money is less than 15 you have to wait and shoot monster";
							GameState.towerUgradeVisible=false;
						}
					}
				}else if(t.getRank()==2){
					tower = upgradeSheet.getSubImage(3, 1);
					if(overUpdateOrDelete(buttonU)){
						tower = upgradeSheet.getSubImage(4, 1);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							
							game.UpgradeTower(t.getName());
							GameState.towerUgradeVisible=false;
							}
					}else if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(5, 1);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.towerUgradeVisible=false;
						}
					}
				}else if(t.getRank()==3){
					tower = upgradeSheet.getSubImage(3, 2);
					if(overUpdateOrDelete(buttonU)){
						tower = upgradeSheet.getSubImage(4, 2);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							
							game.UpgradeTower(t.getName());
							GameState.towerUgradeVisible=false;
							}
					}else if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(5, 2);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.towerUgradeVisible=false;
						}
					}
				}else if(t.getRank()==4){
					tower = upgradeSheet.getSubImage(3, 4);
						
						if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(5, 4);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.towerUgradeVisible=false;
							}
						}
					}
			}else if(t.getType() == 'p'){

				if(t.getRank()==1){
					tower = upgradeSheet.getSubImage(0, 3);
					if(overUpdateOrDelete(buttonU)){
						tower = upgradeSheet.getSubImage(1, 3);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							
							game.UpgradeTower(t.getName());
							
							GameState.towerUgradeVisible=false;
							GameState.string = "Now try to remove a tree. Right click on a tree and choose Remove.";
							GameState.skip = "If your money is less than 15 you have to wait and shoot monster";
						}
					}else if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(2, 3);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.string = "Now try to remove a tree. Right click on a tree and choose Remove.";
							GameState.skip = "If your money is less than 15 you have to wait and shoot monster";
							GameState.towerUgradeVisible=false;
						}
					}
				}else if(t.getRank()==2){
					tower = upgradeSheet.getSubImage(0, 4);
					if(overUpdateOrDelete(buttonU)){
						tower = upgradeSheet.getSubImage(1, 4);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							
							game.UpgradeTower(t.getName());
							GameState.towerUgradeVisible=false;
							}
					}else if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(2, 4);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.towerUgradeVisible=false;
						}
					}
				}else if(t.getRank()==3){
					tower = upgradeSheet.getSubImage(0, 5);
					if(overUpdateOrDelete(buttonU)){
						tower = upgradeSheet.getSubImage(1, 5);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							
							game.UpgradeTower(t.getName());
							GameState.towerUgradeVisible=false;
							}
					}else if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(2, 5);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.towerUgradeVisible=false;
						}
					}
				}else if(t.getRank()==4){
					tower = upgradeSheet.getSubImage(3, 3);
					
						if(overUpdateOrDelete(buttonD)){
						tower = upgradeSheet.getSubImage(5, 3);
						if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
							game.SellTower(ControllTower.tower);
							GameState.towerUgradeVisible=false;
							}
						}
					}
				}
		}
		return tower;
	}
	
	/**
	 * to get the image for removing objects on the map
	 * and remove it on left click
	 * 
	 * @param oldMouse the position of the mouse at click
	 * 
	 * @return the image for removing objects
	 */
	public Image removeTree(Point oldMouse){
		Image remove = infoSheet.getSubImage(1, 0);
		Point buttonD = null;
		if(oldMouse!=null)
			buttonD = new Point((int)oldMouse.getX(), (int)oldMouse.getY()+115);
		
		if(GameState.treeVisible && overUpdateOrDelete(buttonD)){
			remove = infoSheet.getSubImage(2, 0);
			if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON) && game.Gold-15>=0){
				String numb = tree.substring(tree.indexOf("tree"));
				GameState.Map.remove(tree);
				GameState.Map.put("field"+numb, currentTree);
				game.Gold = game.Gold - 15;
				GameState.string = "Now we want to Test Fullscreen.";
				GameState.skip = "Please press f to enter Fullscreen.";
				GameState.treeVisible = false;
			}
		}
		return remove;
	}
}