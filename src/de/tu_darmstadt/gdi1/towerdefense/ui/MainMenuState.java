package de.tu_darmstadt.gdi1.towerdefense.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.towerdefense.classes.GameEngine;
import de.tu_darmstadt.gdi1.towerdefense.controller.ControllSetUp;

/**
 * @author Michael Schlittenbauer
 * @version 0.3
 *
 */
public class MainMenuState extends BasicGameState {

	private int stateID;
	
	private SpriteSheet level; //the SpriteSheet with all default Menulevel SubImages
	private SpriteSheet levelicy; //the SpriteSheet with all icy Menulevel SubImages
	private SpriteSheet levelut; //the SpriteSheet with all ut Menulevel SubImages
	private Image back; //the Background of the menu
	public static Image high; //the Highscore Image
	public static Image l1; //the SubImage for Level1
	public static Image l2; //the SubImage for Level2
	public static Image l3; //the SubImage for Level3
	public static Image l4; //the SubImage for Level4
	public static Image l5; //the SubImage for Level5
	private Image button; //the round selection button
	private Image buttonActivated; //the round selection is activated button
	private Image header_back; //Credits back
	
	public static float lc1; //the scale account for level 1
	public static float lc2; //the scale account for level 2
	public static float lc3; //the scale account for level 3
	public static float lc4; //the scale account for level 4
	public static float lc5; //the scale account for level 5
	public static float scaleHigh; //the scale account for Highscore
	
	public static boolean atHigh = false; //indicates if mouse is at Highscore
	public static boolean atLevel1 = false; //indicates if mouse is at Level1
	public static boolean atLevel2 = false; //indicates if mouse is at Level2
	public static boolean atLevel3 = false; //indicates if mouse is at Level3
	public static boolean atLevel4 = false; //indicates if mouse is at Level4
	public static boolean atLevel5 = false; //indicates if mouse is at Level5
	static boolean icyS = false; //indicates if icy Skin is selected
	static boolean utS = false; //indicates if ut Skin is selected
	private static boolean defaultS = true; //indicates if default Skin is selected
	private boolean easy = true; //indicates if game should be easy
	private boolean medium = false; //indicates if game should be medium
	private boolean hard = false; //indicates if game should be hard
	private boolean credits = false; //indicates if the credits should be shown

	public static GameEngine game; //get a GameEngine
	
	/**
	 * set the state ID to the given value
	 * 
	 * @param stateID the ID for this menu state
	 */
	MainMenuState (int stateID){
		this.stateID = stateID;
	}
	
	public static GameEngine getGame(){
		return game;
	}
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {	
		
		back = new Image(
				"de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\menu\\menu.png");
		
		high = new Image(
				"de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\menu\\high.png");
		
		level = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\map\\MapSheet.png", 108, 108);
		
		levelicy = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\map\\IcyMapSheet.png", 108, 108);
		
		levelut = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\map\\utMapSheet.png", 108, 108);
		
		SpriteSheet buttonSpriteSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\object_default_spriteSheet.png", 48, 48);
		
		button = buttonSpriteSheet.getSubImage(1,3);
		buttonActivated = buttonSpriteSheet.getSubImage(0,3);
		
		//loads the header Background Image
				header_back = new Image(
						"de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\menu\\header_back.png");
		
		l1 = level.getSubImage(0, 0);
		l2 = level.getSubImage(1, 0);
		l3 = level.getSubImage(2, 0);
		l4 = level.getSubImage(3, 0);
		l5 = level.getSubImage(4, 0);

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		
		
		back.draw(0, 0);
		header_back.draw(gc.getWidth()-header_back.getWidth(), gc.getHeight()-header_back.getHeight());
		g.drawString("Credits", gc.getWidth()-header_back.getWidth()+10, gc.getHeight()-header_back.getHeight()+9);

		
		g.drawString("Please choose level of difficulty: ", 50, 250);
		if(easy){
			buttonActivated.drawCentered(59, 282);
			button.drawCentered(59, 302);
			button.drawCentered(59, 322);
		}else if(medium){
			button.drawCentered(59, 282);
			buttonActivated.drawCentered(59, 302);
			button.drawCentered(59, 322);
		}else if(hard){
			button.drawCentered(59, 282);
			button.drawCentered(59, 302);
			buttonActivated.drawCentered(59, 322);
		}
		g.drawString("easy\nmedium\nhard", 75, 272);
		
		if(defaultS){
			buttonActivated.draw(400, 255);
			button.draw(400, 275);
			button.draw(400, 295);
		}else if(utS){
			button.draw(400, 255);
			button.draw(400, 275);
			buttonActivated.draw(400, 295);
		}else if(icyS){
			button.draw(400, 255);
			buttonActivated.draw(400, 275);
			button.draw(400, 295);
		}
		
		g.drawString("Please choose Skin: ", 418, 250);
		g.drawString("default\nicy\nut", 440, 270);
		
		l1.draw(74, 370, lc1);
		l2.draw(186, 370, lc2);
		l3.draw(296, 370, lc3);
		l4.draw(406, 370, lc4);
		l5.draw(516, 370, lc5);
		
		high.draw(202, 500, scaleHigh);
		
		if(credits){
			g.setColor(Color.black);
			g.fillRect((gc.getWidth()-500)/2f, (gc.getHeight()-250)/2f, 500, 250);
			String credits = "This game was coded and designed by:\n\n" +
					"Alavi, Saed\n" +
					"Lichtblau, Martin\n" +
					"Schlittenbauer, Michael\n" +
					"Schönleber, Markus\n\n" +
					"Thanks to: \n" +
					"our Tutor and every one who supported us.";
			g.setColor(Color.white);
			g.drawString(credits, (gc.getWidth()-500)/2f+5, (gc.getHeight()-250)/2f+5);
			
		}
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
	
		Input mc = gc.getInput();
		int mouseX = mc.getMouseX();
		int mouseY = mc.getMouseY();
		float scaleAtImage = 1.05f;
		ControllSetUp controll = new ControllSetUp();
		
		//Checks if the User want to display the Credits
		credits = (mouseX >= 1024 - header_back.getWidth() && mouseX <= 1024) &&
				(mouseY >= 768 - header_back.getHeight() && mouseY <= 768) &&
				mc.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		
		//check which level Skin should be loaded
		if(((mouseX >= 418 && mouseX <= 438)
				&& (mouseY >= 273 && mouseY <= 293))
				 && mc.isMousePressed(Input.MOUSE_LEFT_BUTTON)
				){
			defaultS = true;
			icyS = false;
			utS = false;
		}else if(((mouseX >= 418 && mouseX <= 438)
				&& (mouseY >= 293 && mouseY <= 313))
				 && mc.isMousePressed(Input.MOUSE_LEFT_BUTTON)
				){
			icyS = true;
			defaultS = false;
			utS = false;
		}else if(((mouseX >= 418 && mouseX <= 438)
				&& (mouseY >= 313 && mouseY <= 333))
				 && mc.isMousePressed(Input.MOUSE_LEFT_BUTTON)
				){
			icyS = false;
			defaultS = false;
			utS = true;
		}
		
		//check which level of difficulty is set
		if(((mouseX >= 50 && mouseX <= 68)
				&& (mouseY >= 273 && mouseY <= 291))
				 && mc.isMousePressed(Input.MOUSE_LEFT_BUTTON)
				){
			easy = true;
			medium = false;
			hard = false;
		}else if(((mouseX >= 50 && mouseX <= 68)
				&& (mouseY >= 293 && mouseY <= 311))
				 && mc.isMousePressed(Input.MOUSE_LEFT_BUTTON)
				){
			easy = false;
			medium = true;
			hard = false;
		}else if(((mouseX >= 50 && mouseX <= 68)
				&& (mouseY >= 313 && mouseY <= 331))
				 && mc.isMousePressed(Input.MOUSE_LEFT_BUTTON)
				){
			easy = false;
			medium = false;
			hard = true;
		}
		
		if(icyS){
			l1 = levelicy.getSubImage(0, 0);
			l2 = levelicy.getSubImage(1, 0);
			l3 = levelicy.getSubImage(2, 0);
			l4 = levelicy.getSubImage(3, 0);
			l5 = levelicy.getSubImage(4, 0);
		}else if(defaultS){
			l1 = level.getSubImage(0, 0);
			l2 = level.getSubImage(1, 0);
			l3 = level.getSubImage(2, 0);
			l4 = level.getSubImage(3, 0);
			l5 = level.getSubImage(4, 0);
		}else if(utS){
			l1 = levelut.getSubImage(0, 0);
			l2 = levelut.getSubImage(1, 0);
			l3 = levelut.getSubImage(2, 0);
			l4 = levelut.getSubImage(3, 0);
			l5 = levelut.getSubImage(4, 0);
			}
		
		controll.whichLevel(mouseX, mouseY, scaleAtImage);
		
		if(atLevel1){
			if(controll.setUp(mc, getLOD(),  "src/de/tu_darmstadt/gdi1/resources/levels/level_01.txt"))
			sbg.enterState(TowerDefense.game);
		}else if (atLevel2){
			if(controll.setUp(mc, getLOD(), "src/de/tu_darmstadt/gdi1/resources/levels/level_02.txt"))
			sbg.enterState(TowerDefense.game);
		}else if (atLevel3){
			if(controll.setUp(mc, getLOD(), "src/de/tu_darmstadt/gdi1/resources/levels/level_03.txt"))
			sbg.enterState(TowerDefense.game);
		}else if (atLevel4){
			if(controll.setUp(mc, getLOD(), "src/de/tu_darmstadt/gdi1/resources/levels/level_04.txt"))
			sbg.enterState(TowerDefense.game);
		}else if (atLevel5){
			if(controll.setUp(mc, getLOD(), "src/de/tu_darmstadt/gdi1/resources/levels/OwnLevel.txt"))
			sbg.enterState(TowerDefense.game);
		}else if(atHigh)
			if (mc.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				sbg.enterState(TowerDefense.high);
			}	
		
		//End an enter Fullscreen
				if(mc.isKeyPressed(Input.KEY_ESCAPE)&&gc.isFullscreen()){
					gc.setFullscreen(false);
				}else if(mc.isKeyPressed(Input.KEY_F)&& !gc.isFullscreen()){
					gc.setFullscreen(true);
				}
	}
	
	private String getLOD(){
		if(easy){
			return "easy";
		}else if(medium){
			return "medium";
		}else if(hard){
			return "hard";
		}else return "";
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		return stateID;
	}

}