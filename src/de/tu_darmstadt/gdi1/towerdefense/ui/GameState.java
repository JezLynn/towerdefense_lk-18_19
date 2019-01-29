/**
 * 
 */
package de.tu_darmstadt.gdi1.towerdefense.ui;

import java.awt.Font;
import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.towerdefense.classes.GameEngine;
import de.tu_darmstadt.gdi1.towerdefense.classes.GuiObject;
import de.tu_darmstadt.gdi1.towerdefense.controller.ControllTower;
import de.tu_darmstadt.gdi1.towerdefense.highscore.HighScore;
import de.tu_darmstadt.gdi1.towerdefense.render.RenderMap;
import de.tu_darmstadt.gdi1.towerdefense.render.RenderObjects;
import de.tu_darmstadt.gdi1.towerdefense.tower.Tower;

/**
 * <h1>GameState</h1>
 * <li>renders every Picture</li>
 * <li>updates the whole GameEngine</li>
 * @author Michael Schlittenbauer
 * @version 1.8
 *
 */
@SuppressWarnings("deprecation")
public class GameState extends BasicGameState {
	
	private SpriteSheet mapSheet = null; //the SpriteSheet with Map Images
	private SpriteSheet objectSheet = null; //the SpriteSheet with the monster
	private SpriteSheet towerSheet = null; //the Tower spriteSheet
	private SpriteSheet infoSheet = null; //the SpriteSheet with all Images to choose and build a Tower
	private SpriteSheet upgradeSheet = null; //the SpriteSheet with all Images to upgrade a Tower
	private Image header_back = null; //the Image for the header Background
	private Image towerInfo; //the actual subImage of the infoSheet with TowerInfo
	private Image towerChoose; //the subImage of the infoSheet with all Towers
	private Image towerUpgrade; //the subImage to upgrade a Tower
	private Image tree; //the subImage to remove a Tree 
	
	static public Music sound; //Background music
	private Sound shoot; //shoot Sound
	
	private int x; //Width of the GameContainer
	private int y; //Height of the GameContainer
	private static float scale; //the amount of scale for pictures if map is bigger than 16*12
	private int money; //the amount of money
	int stateID = -1; //id to find this menu state
	public int time=0; //counts how often the game is been updated
	private static int initLifepionts; //the lifepoints at the GameStart
	private static int initMoney; //the money at the GameStart
	private float lifepionts; //for the life points
	private Point oldMouse; //save the X and Y position of the mouse at click
	private int pictureSize = GuiObject.pictureSize; //saves how big the pictures
	private int waveTimer = 12000; //counts 20 seconds, if 0 newWave()
	private String waveNr = ""; //counts the Number of the Waves
	private String nameToShort = ""; //come up if the name is to short
	public static String string; //The String for the Tut
	public static String skip = "If you know how to play and want to skip the Tutorial play the game.";
	
	private Rectangle lps; //Rectangle which visualize the life points
	private Rectangle lpsback; //box for life points
	private Circle towerRange; //its an circle symbolize the TowerRange
	
	
	public static Map<String, Point> Map = new HashMap<String, Point>(); //saves only the map of the actual game
	public static Map<String, Point> Objects; //saves the Position of all Objects in the game
	
	public static boolean towerChooseVisible = false; //indicates if the TowerChooser is visible
	public static boolean towerInfoVisible = false; //indicate if the TowerInfo is visible
	public static boolean towerUgradeVisible = false; //indicate if the TowerUpdate is visible
	public static boolean treeVisible = false; //indicates if you clicked on a tree
	private boolean towerRangeVisible = false; //indicates if the TowerRange is visible
	private boolean bonusLevelVisible = false; //indicates if the bonusLevel Question is visible
	private boolean highscoreVisible = false; //indicates if the Highscore Question is visible
	public static boolean canTowerSet = false; //indicates if you can set a Tower
	public static boolean gameLost = false; //indicates if Game is Lost
	public static boolean gameWon = false; //indicates if Game is Won
	private boolean tutVisible = true; //is the Tutorial visible
	public static boolean restart = false; //should the Game restart
	private boolean firstTime = false; //checks if the game were restarted after Tut
	
	private TextField name;
	
	TrueTypeFont uFontTut;
	/**
	 * set the state ID to the given value
	 * 
	 * @param stateID the ID for this menu state
	 */
	GameState (int stateID){
		this.stateID = stateID;
	}
	

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		return stateID;
	}
	

	/**
	 * To set the scale of the pictures
	 * 
	 * @param mapScale the which is needed
	 */
	public static void setScale(float mapScale){
		scale = mapScale;
	}
	
	/**
	 * Set the initiated life-points for math
	 * @param lp the initiated life-points
	 */
	public static void setInitLP(int lp){
		initLifepionts = lp;
	}
	
	/**
	 * Set the initiated money for reset
	 * @param m the initiated money
	 */
	public static void setInitMoney(int m){
		 initMoney = m;
	}
	

	@Override
	public void enter (GameContainer gc, StateBasedGame sbg) throws SlickException{
		super.enter(gc, sbg);
		//loads the mapSheet, Sound and loop the given Sound on entering the GameState
		if(MainMenuState.icyS){//icy Sheet
			mapSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\map\\map_icy_spriteSheet.png", 48, 48);
			objectSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\object_icy_spriteSheet.png", 48, 48);
			towerSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\tower\\tower_icy_spriteSheet.png", 48, 48);
			infoSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\tower_info_icy_spriteSheet.png", 108, 150);
			upgradeSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\tower_upgrade_icy_spriteSheet.png", 108, 150);
			//loads the shoot Sound
			shoot = new Sound("de\\tu_darmstadt\\gdi1\\resources\\sounds\\icy_shoot.ogg");
			sound = new Music("de\\tu_darmstadt\\gdi1\\resources\\sounds\\icy.ogg");
			sound.loop();
			
			}else if(MainMenuState.utS){ //default grass Sheet 
				mapSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\map\\map_ut_spriteSheet.png", 48, 48);
				objectSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\object_ut_spriteSheet.png", 48, 48);
				towerSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\tower\\tower_ut_spriteSheet.png", 48, 48);
				infoSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\tower_info_ut_spriteSheet.png", 108, 150);
				upgradeSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\tower_upgrade_ut_spriteSheet.png", 108, 150);
				//loads the shoot Sound
				shoot = new Sound("de\\tu_darmstadt\\gdi1\\resources\\sounds\\ut_shoot.ogg");
				sound = new Music("de\\tu_darmstadt\\gdi1\\resources\\sounds\\ut.ogg");
				sound.loop();
			}else{ //default grass Sheet 
				mapSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\map\\map_default_spriteSheet.png", 48, 48);
				objectSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\object_default_spriteSheet.png", 48, 48);
				towerSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\tower\\tower_default_spriteSheet.png", 48, 48);
				infoSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\tower_info_default_spriteSheet.png", 108, 150);
				upgradeSheet = new SpriteSheet("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\objects\\tower_upgrade_default_spriteSheet.png", 108, 150);
				//loads the shoot Sound
				shoot = new Sound("de\\tu_darmstadt\\gdi1\\resources\\sounds\\shoot.wav");
				sound = new Music("de\\tu_darmstadt\\gdi1\\resources\\sounds\\grass.ogg");
				sound.loop();
				}
	}
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		//loads the header Background Image
		header_back = new Image(
				"de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\menu\\header_back.png");
				
		// save the Width and Height of the GameContainer
		x=gc.getWidth();
		y=gc.getHeight();
		
		//initiates the box for the life points
		lpsback = new Rectangle(x-90,13,75,12);
		
		//initiates the Tower Range Circle
		towerRange = new Circle(0, 0, 0);
		
		Font font = new Font(null, 0, 12);
		TrueTypeFont uFont = new TrueTypeFont(font, true);
		
		Font fontTut = new Font(null, 0, 25);
		uFontTut= new TrueTypeFont(fontTut, true);
		string = "Welcome to the Tutorial for Tower Defense. Please left click here to continue.";
		
		//initiates the TextField
		name = new TextField(gc, uFont, 250, 385, 200, 20);
		name.isAcceptingInput();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		g.setAntiAlias(true);
		
		//draw the whole green background
		mapSheet.startUse();
		for (int i = 0; i < x / 40; i++) {
			for (int j = 0; j < y / 40; j++) {
				mapSheet.renderInUse(i * 48, j * 48, 0, 0); 
			}
		}
		mapSheet.endUse();
		
		 //draw the map it self
		if (Map != null) {
			for (Iterator<String> it = Map.keySet().iterator(); it.hasNext();) {
				String name = it.next();
				RenderMap teil = RenderMap.render(Map, mapSheet, name, scale);
				(teil.getImage()).draw(teil.getX(), teil.getY(), scale);
			}
		}
		
		//draw all monsters and Towers in the game
		if (Objects != null){ 
			for (Iterator<String> it = Objects.keySet().iterator(); it.hasNext();){
				String name = it.next();
				RenderObjects object = RenderObjects.render(Objects, objectSheet, towerSheet, name, scale);
				(object.getImage()).draw(object.getX(), object.getY(), scale);
				if(name.indexOf("monster", 0)!=-1){
				g.fillRect(object.getX(), object.getY()-5, (GameEngine.monsterMap.get(name).getLifepoints()*2)*scale, 4);
				}
			}
		}
		
		if(towerChooseVisible){ //draw the towerChoose
			g.drawImage(towerChoose, (int)oldMouse.getX(), (int)oldMouse.getY());
			if(towerInfoVisible){ //draw the towerInfo
				if(towerInfo!=null)
				g.drawImage(towerInfo, (int)oldMouse.getX()+110, (int)oldMouse.getY());
				}
		}else if(towerUgradeVisible){ //draw the towerUpdate
			g.drawImage(towerUpgrade, (int)oldMouse.getX(), (int)oldMouse.getY());
		}else if(treeVisible){  //draw the treeRemove
			g.drawImage(tree, (int)oldMouse.getX(), (int)oldMouse.getY());
		}
		
		if(gameLost){//draw String "Game Over" if Game is Lost
			g.drawString("Game Over", 250, 283);
		}
		
		if(gameWon){//draw the String "You've won" if Game is Won
			g.drawString("You've won", 250, 283);
		}
		
		if(initLifepionts==60 && lifepionts>=0){
			lps = new Rectangle(x-90, 12, 75*((lifepionts*1.66f)/100), 13); //initiates the life points Rectangle
		}else if(initLifepionts==30 && lifepionts>=0){
			lps = new Rectangle(x-90, 12, 75*((lifepionts*3.33f)/100), 13); //initiates the life points Rectangle
		}else if (initLifepionts==15 && lifepionts>=0){
			lps = new Rectangle(x-90, 12, 75*((lifepionts*6.66f)/100), 13); //initiates the life points Rectangle
		}else lps = new Rectangle(x-90, 12, 0, 13); //life points = 0
		
		header_back.draw(0, 0); //draw graphic into upper left corner for money
		header_back.draw(x-header_back.getWidth(), 0); //draw graphic into upper right corner for life-points
		header_back.draw(x-header_back.getWidth(), header_back.getHeight()); //draw graphic into upper right corner for reset Game
		header_back.draw(0, y-header_back.getHeight()); //draw graphic into lower left corner for newMonster
		header_back.draw(0, y-header_back.getHeight()*2); //draw graphic into lower left corner for newMonsterWave
		header_back.draw(0, y-header_back.getHeight()*3); //draw graphic into lower left corner for SpeedUp
		g.drawString("Money", 15, 9); //draw the String Money on top of the upper left graphic
		g.drawString("Lifepoints", x-header_back.getWidth()+15, 9); //draw the String Life points on top 
																	//of the upper right graphic
		g.drawString("back to main Menu", x-header_back.getWidth()+15, 9+header_back.getHeight()); //draw the String Life points on top
		g.drawString("New monster", 15, y-header_back.getHeight()+9); //draw the String for new Monster
		g.drawString("Start next wave", 15, y-header_back.getHeight()*2+9); //draw the String for new MonsterWave
		g.drawString("Speed up", 15, y-header_back.getHeight()*3+9); //draw the String for SpeedUp		
		g.drawString(""+money, 80, 9); //draw the amount of money in the upper right corner
		g.draw(lpsback); //draw a box for the life point shape
		g.fill(lps); //draw the actual life points visualized as white bar
		
		if(MainMenuState.icyS){ //sets the drawing Color to black if
			g.setColor(Color.black); //if MapSheet is icy
		}
		
		g.drawString("Next Wave in: "+waveTimer/1200, x-150, y-40); //Draw the timer for next Wave
		g.drawString("Current Wave: "+waveNr, x-150, y-20); //Draw the number of current Wave
		
		if(towerRangeVisible){ //Draw the Circle of Tower Range
			g.draw(towerRange); 
		}
		g.setColor(Color.white);
		
		if(highscoreVisible){ //Draw Question for Highscore
			g.fill(new Rectangle(230, 280, 315, 180));
			name.render(gc, g);
			name.setFocus(true);
			g.setColor(Color.black);
			g.drawString("Do you want to save your score", 250, 300);
			g.drawString("Yes", 330, 335);
			g.drawString("No", 410, 335);;
			g.drawString("Please enter a Name: ", 250, 360);
			g.drawString(nameToShort, 250, 410);
			g.setColor(Color.white);
		}
		
		if(bonusLevelVisible){ //Draw Question for BonusLevel
			g.fill(new Rectangle(230, 280, 315, 180));
			g.setColor(Color.black);
			g.drawString("Do you want to\nplay a bonus Level", 250, 300);
			g.drawString("or go back to Mainmenu", 250, 360);
			g.setColor(Color.white);
		}
		
		if(tutVisible){
			g.fillRoundRect(0, y-130, x, 150, 20);
			g.drawString("Next Wave in: "+waveTimer/1200, x-150, y-170); //Draw the timer for next Wave
			g.drawString("Current Wave: "+waveNr, x-150, y-150); //Draw the number of current Wave
			g.setColor(Color.black);
			g.setFont(uFontTut);
			g.drawString(string, 20, y-110);
			g.drawString(skip, 20, y-85);
			g.setColor(Color.white);
		}
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		
		GameEngine game = MainMenuState.getGame(); //get the actual Game from GameEngine
		
		Objects = game.getGuiMap(); //with its Tower and Monsters
		money = game.get_Gold(); //the Money
		lifepionts = game.get_LifePoints();//and the actual Life points
		
		Input in = gc.getInput(); //get the Input of the GameContainer
		Point mouse = new Point(in.getMouseX(),in.getMouseY()); //Save the position of the Mouse
		
		//End an enter Fullscreen
		if(in.isKeyPressed(Input.KEY_ESCAPE)&&gc.isFullscreen()){
			gc.setFullscreen(false);
			string = "The tutorial is over now. The Game will restart.";
			restart = true;
		}else if(in.isKeyPressed(Input.KEY_F)&&gc.isFullscreen()!=true){
			gc.setFullscreen(true);
			string = "Now try to press Esc";
			skip = " ";
		}
		
		if(string.equals("Welcome to the Tutorial for Tower Defense. Please left click here to continue.")&&
				((mouse.getX()>=0 && mouse.getX() <= x) && (mouse.getY()>=y-130 && mouse.getY() <= y)) &&
				in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			string = "First we want to set a new Tower. Please rigth click on an empty Field.";
			skip = "";}
		
		if(gameLost!=true && gameWon != true){ //moves all Monsters
		game.moveAllMonster();
		}
		
		if(game.shootTower()){ //let the Tower shoot and Play a Sound at shoot
			shoot.play();
		}
		
		//checks if Player want new Monster
		if((in.getMouseX()>=0 && in.getMouseX()<=header_back.getWidth())&&
				(in.getMouseY()>= y-header_back.getHeight() && in.getMouseY()<=y)&&
					in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			game.StartMonster(1, 1, 1);
		}
		
		//checks if Player want to speed up a little bit
		if((in.getMouseX()>=0 && in.getMouseX()<=header_back.getWidth())&&
				(in.getMouseY()>= y-header_back.getHeight()*3 && in.getMouseY()<=y-header_back.getHeight()*2)&&
					in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			gc.setTargetFrameRate(200);
		}else gc.setTargetFrameRate(60);
		
		//checks if Player want a new wave
		if((in.getMouseX()>=0 && in.getMouseX()<=header_back.getWidth())&&
				(in.getMouseY()>= y-header_back.getHeight()*2 && in.getMouseY()<=y-header_back.getHeight())&&
					in.isMousePressed(Input.MOUSE_LEFT_BUTTON)&& game.Wave<10){
			waveTimer = 12000;
			game.NewWave();
			waveNr = "" + game.Wave;
		}
		
		//checks if Player want go back to main Menu
				if((in.getMouseX()>=x-header_back.getWidth() && in.getMouseX()<=x)&&
						(in.getMouseY()>= header_back.getHeight() && in.getMouseY()<= y)&&
							in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					game.resetAll(); //reset Game
					waveTimer = 12000;
					sbg.enterState(TowerDefense.mainMenu); //and go back to mainMenu
				}
		
		//checks if Wave is over and starts the Timer 
		if(GameEngine.monsterMap.isEmpty() && waveTimer<=0 && gameLost!=true && gameWon != true){
				waveTimer = 12000;
				game.NewWave();
				waveNr = "" + game.Wave;
				if(restart){
					tutVisible = false;
					restart=false;
				}
		}else if (GameEngine.monsterMap.isEmpty() && waveTimer>0 && gameLost!=true && gameWon != true){
						waveTimer = waveTimer - delta;
					}
		
		//checks if Game is lost
		if(game.GameLost()){
			gameLost=true;
			if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){//on MOUSE_LEFT_BUTTON click
				game.resetAll(); //rest everything
				sbg.enterState(TowerDefense.mainMenu); //and go back to mainMenu
				gameLost = false;
			}
		}
		
		//checks if Game is Won
		if(game.GameWon()){
			gameWon = true;
			highscoreVisible = true;
			if((in.getMouseX()>=330 && in.getMouseX()<=350) &&
				(in.getMouseY()>=335&& in.getMouseY()<=355) &&
					in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){ //should score be saved
				
						if(name.getText().length()>1 && name.getText().length() < 11){
							HighScore high = new HighScore();
							high.save(name.getText(), money);
							bonusLevelVisible = true;
						}else nameToShort = "Name must be between\n2 an 10 characters";
						
			}else if((in.getMouseX()>=410 && in.getMouseX()<=430) &&
				(in.getMouseY()>=335&& in.getMouseY()<=355) &&
					in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){ //or not
						bonusLevelVisible = true;
			}
		}
		
		//ask for BonusLeve
		if(bonusLevelVisible){
				if((in.getMouseX()>=250 && in.getMouseX()<=450) &&
						(in.getMouseY()>=300 && in.getMouseY()<=345) &&
						in.isMousePressed(Input.MOUSE_LEFT_BUTTON) && bonusLevelVisible){ //Player want to play bonus Level
							game.resetAll();
							highscoreVisible = false;
							bonusLevelVisible = false;
							MainMenuState.game = new GameEngine(1, 20, 200, "src/de/tu_darmstadt/gdi1/resources/levels/bonus.txt");
							GameState.Map.putAll(GuiObject.getGuiMap());
							MainMenuState.game.StartGame();
				}else if((in.getMouseX()>=250 && in.getMouseX()<=450) &&
						(in.getMouseY()>=360 && in.getMouseY()<=380) &&
						in.isMousePressed(Input.MOUSE_LEFT_BUTTON) && bonusLevelVisible) { //or not
							game.resetAll(); //rest everything
							highscoreVisible = false;
							bonusLevelVisible = false;
							sbg.enterState(TowerDefense.mainMenu); //and go back to mainMenu
				}
		}

		ControllTower checker = new ControllTower(Map, Objects, mouse, game, infoSheet, upgradeSheet, in); //initiates the ControllTower
		
		//what to do after left click
		if(in.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
			oldMouse = mouse; //save Position of mouse at right click
			if(checker.isFieldValid(scale)){ //checks if clicked field is valid
				if(checker.isThereTower(scale)!=true){ //checks if there is not a Tower
					if(string.indexOf("Welcome", 0)!=-1){
						tutVisible = false;
						}
					string = "Now you can choose a Tower and set it on the field by clicking left on the Tower.";
					canTowerSet = true; //so you can build a new one
					towerChooseVisible = true; //you should see the TowerChooser
					towerUgradeVisible = false; //you should not see the TowerUpgrade
					towerChoose = infoSheet.getSubImage(0, 0); //get the SubImage out of the SpriteSheet
								
				}else if(checker.isThereTower(scale)){ //checks if there is a Tower
					towerUgradeVisible = true; //so you could see towerUpgrade
					//towerChoose = infoSheet.getSubImage(0, 1); //get the SubImage out of the SpriteSheet
					}
			}else if(checker.isFieldTree(scale)){ //check if there is a tree
				canTowerSet = false; //so you can't set towers
				treeVisible = true; //and should see the Image to remove tree's
			}
		}
		
		//checks if one of the tree is Visible an your out of Image Bounds
		if ((towerChooseVisible||towerUgradeVisible || treeVisible)&& 
				((oldMouse.getX()-5<=mouse.getX()&&oldMouse.getX()+115>=mouse.getX())&&
						(oldMouse.getY()-5<=mouse.getY() && oldMouse.getY()+155>= mouse.getY()))!=true){
			
			towerChooseVisible = false; 
			towerUgradeVisible = false;
			treeVisible = false;
		}
		
		//if on the field is a Tower
		if(checker.isThereTower(scale)){
			towerRangeVisible = true; //you should see his Range
			double towerX = ControllTower.currentTower.getX(); //the x and y Position of the tower
			double towerY = ControllTower.currentTower.getY(); //for range drawing
			Tower tower = GameEngine.towerMap.get(ControllTower.tower); //get the clicked Tower out of the map
			float radius = (float) tower.getrange()*pictureSize*scale+10; //to get his Range for the radius
			towerRange = new Circle ((int)(towerX+pictureSize/2)*scale, (int)(towerY+pictureSize/2)*scale, radius); //initiates the range Circle for this Tower 
		}else towerRangeVisible = false; //if it was no Tower you should not see the Circle
		
			towerUpgrade = checker.upgradeTower(oldMouse); //get the Image for tower Upgrade
			towerInfo = checker.infoField(oldMouse, 110, pictureSize); //get the Image for tower Info
			tree = checker.removeTree(oldMouse); //get the Image for tree remove
		
		//restart the Game
			if(restart && firstTime){
				GuiObject.GuiObjectMap.clear();
				GameEngine.monsterMap.clear();
				GameEngine.towerMap.clear();
				game.Gold = initMoney;
				game.set_LifePoints(initLifepionts);
				firstTime = false;
			}else if(restart){
				skip = "in "+waveTimer/1200;
			}
	}

}