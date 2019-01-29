package de.tu_darmstadt.gdi1.towerdefense.controller;

import org.newdawn.slick.Input;

import de.tu_darmstadt.gdi1.towerdefense.MapBuilder.Builder;
import de.tu_darmstadt.gdi1.towerdefense.classes.GameEngine;
import de.tu_darmstadt.gdi1.towerdefense.classes.GuiObject;
import de.tu_darmstadt.gdi1.towerdefense.ui.GameState;
import de.tu_darmstadt.gdi1.towerdefense.ui.MainMenuState;

/**
 * @author Michael Schlittenbauer
 */
public class ControllSetUp {

	/**
	 * Determines which level is Pointed with the mouse
	 * an set the appropriated scale
	 *  
	 * @param mouseX the x Position of the mouse
	 * @param mouseY the y Position of the mouse
	 * @param scaleAtImage scale account to allocate 
	 */
	public void whichLevel(int mouseX, int mouseY, float scaleAtImage){
		if((mouseX >= 74 && mouseX <= 74+MainMenuState.l1.getWidth()) //mouse at Level 1
				&& (mouseY >= 370 && mouseY <= 370+MainMenuState.l1.getHeight())){
			MainMenuState.atLevel1 = true;
			MainMenuState.atLevel2 = false;
			MainMenuState.atLevel3 = false;
			MainMenuState.atLevel4 = false;
			MainMenuState.atLevel5 = false;
			MainMenuState.atHigh = false;
			MainMenuState.lc1 = scaleAtImage;
			MainMenuState.scaleHigh = 1;
			MainMenuState.lc2 = 1;
			MainMenuState.lc3 = 1;
			MainMenuState.lc4 = 1;
			MainMenuState.lc5 = 1;
		}else if((mouseX >= 186 && mouseX <= 186+MainMenuState.l2.getWidth()) //mouse at Level 2
				&& (mouseY >= 370 && mouseY <= 370+MainMenuState.l2.getHeight())){
			MainMenuState.atLevel1 = false;
			MainMenuState.atLevel2 = true;
			MainMenuState.atLevel3 = false;
			MainMenuState.atLevel4 = false;
			MainMenuState.atLevel5 = false;
			MainMenuState.atHigh = false;
			MainMenuState.lc2 = scaleAtImage;
			MainMenuState.scaleHigh = 1;
			MainMenuState.lc1 = 1;
			MainMenuState.lc3 = 1;
			MainMenuState.lc4 = 1;
			MainMenuState.lc5 = 1;
		}else if((mouseX >= 296 && mouseX <= 296+MainMenuState.l3.getWidth()) //mouse at Level 3
				&& (mouseY >= 370 && mouseY <= 370+MainMenuState.l3.getHeight())){
			MainMenuState.atLevel1 = false;
			MainMenuState.atLevel2 = false;
			MainMenuState.atLevel3 = true;
			MainMenuState.atLevel4 = false;
			MainMenuState.atLevel5 = false;
			MainMenuState.atHigh = false;
			MainMenuState.lc3 = scaleAtImage;
			MainMenuState.scaleHigh = 1;
			MainMenuState.lc1 = 1;
			MainMenuState.lc2 = 1;
			MainMenuState.lc4 = 1;
			MainMenuState.lc5 = 1;
		}else if((mouseX >= 406 && mouseX <= 406+MainMenuState.l4.getWidth()) //mouse at Level 4
				&& (mouseY >= 370 && mouseY <= 370+MainMenuState.l4.getHeight())){
			MainMenuState.atLevel1 = false;
			MainMenuState.atLevel2 = false;
			MainMenuState.atLevel3 = false;
			MainMenuState.atLevel4 = true;
			MainMenuState.atLevel5 = false;
			MainMenuState.atHigh = false;
			MainMenuState.lc4 = scaleAtImage;
			MainMenuState.scaleHigh = 1;
			MainMenuState.lc1 = 1;
			MainMenuState.lc2 = 1;
			MainMenuState.lc3 = 1;
			MainMenuState.lc5 = 1;
		}else if((mouseX >= 516 && mouseX <= 516+MainMenuState.l5.getWidth()) //mouse at MapCreator
				&& (mouseY >= 370 && mouseY <= 370+MainMenuState.l5.getHeight())){
			MainMenuState.atLevel1 = false;
			MainMenuState.atLevel2 = false;
			MainMenuState.atLevel3 = false;
			MainMenuState.atLevel4 = false;
			MainMenuState.atLevel5 = true;
			MainMenuState.atHigh = false;
			MainMenuState.lc5 = scaleAtImage;
			MainMenuState.scaleHigh = 1;
			MainMenuState.lc1 = 1;
			MainMenuState.lc2 = 1;
			MainMenuState.lc3 = 1;
			MainMenuState.lc4 = 1;
		}else if((mouseX >= 202 && mouseX <= 202+MainMenuState.high.getWidth()) //mouse at Highscore
				&& (mouseY >= 489 && mouseY <= 489+MainMenuState.high.getHeight())){
			MainMenuState.atLevel1 = false;
			MainMenuState.atLevel2 = false;
			MainMenuState.atLevel3 = false;
			MainMenuState.atLevel4 = false;
			MainMenuState.atLevel5 = false;
			MainMenuState.atHigh = true;
			MainMenuState.scaleHigh = scaleAtImage;
			MainMenuState.lc1 = 1;
			MainMenuState.lc2 = 1;
			MainMenuState.lc3 = 1;
			MainMenuState.lc4 = 1;
			MainMenuState.lc5 = 1;
		}else 
			{
			MainMenuState.atLevel1 = false;
			MainMenuState.atLevel2 = false;
			MainMenuState.atLevel3 = false;
			MainMenuState.atLevel4 = false;
			MainMenuState.atLevel5 = false;
			MainMenuState.atHigh = false;
			MainMenuState.scaleHigh = 1;
			MainMenuState.lc1 = 1;
			MainMenuState.lc2 = 1;
			MainMenuState.lc3 = 1;
			MainMenuState.lc4 = 1;
			MainMenuState.lc5 = 1;
			}		
	}
	
	/**
	 * set up the Game addicted to the level of difficulty
	 * 
	 * @param mc Input of the GameContainer 
	 * @param getLOD the selected level of difficulty
	 * @param level the path to the textfile to be loaded
	 * 
	 * @return is the game effectually been initialized
	 */
	public boolean setUp(Input mc, String getLOD, String level) {
		if (mc.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(level.endsWith("OwnLevel.txt")){
				Builder b = new Builder();
					b.createField();
					b.makeFile();
					
			}
			switch (getLOD) {
				case "easy":
					MainMenuState.game = new GameEngine(1, 60, 60, level);
					GameState.Map.putAll(GuiObject.getGuiMap());
					MainMenuState.game.StartGame();
					GameState.setInitMoney(60);
					GameState.setInitLP(60);
					return true;

				case "medium":
					MainMenuState.game = new GameEngine(2, 30, 50, level);
					GameState.Map.putAll(GuiObject.getGuiMap());
					MainMenuState.game.StartGame();
					GameState.setInitMoney(50);
					GameState.setInitLP(30);
					return true;

				case "hard":
					MainMenuState.game = new GameEngine(2, 15, 30, level);
					GameState.Map.putAll(GuiObject.getGuiMap());
					MainMenuState.game.StartGame();
					GameState.setInitMoney(30);
					GameState.setInitLP(15);
					return true;
			} 
		}
		return false;
	}
}
