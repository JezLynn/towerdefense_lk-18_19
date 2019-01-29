package de.tu_darmstadt.gdi1.towerdefense.ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Michael Schlittenbauer
 * @version 0.3
 *
 */
public class TowerDefense extends StateBasedGame {

	static final int mainMenu = 0;
	public static final int game = 1;
	static final int high = 2;


	private TowerDefense(){
		super("Tower Defense");
		this.addState(new MainMenuState(mainMenu));
		this.addState(new GameState(game));
		this.addState(new HighScoreGui(high));
		this.enterState(mainMenu);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(mainMenu).init(gc, this);
		this.getState(game).init(gc, this);
		this.getState(high).init(gc, this);
		this.getState(high).init(gc, this);
		}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer game = new AppGameContainer(new TowerDefense());
		game.setTargetFrameRate(60);
		game.setDisplayMode(1024, 768, true);
		game.setShowFPS(false);
		game.start();
	}

}
