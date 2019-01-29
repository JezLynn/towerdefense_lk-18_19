package de.tu_darmstadt.gdi1.towerdefense.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.towerdefense.highscore.HighScore;

/**
 * @author Michael Schlittenbauer
 * @version 0.1
 */
public class HighScoreGui extends BasicGameState {

	private int stateID;
	private Image back;
	
	/**
	 * set the state ID to the given value
	 * 
	 * @param stateID the ID for this menu state
	 */
	HighScoreGui (int stateID){
		this.stateID = stateID;
	}
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		back = new Image("de\\tu_darmstadt\\gdi1\\resources\\images\\defaultskin\\menu\\menu.png");
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		
		back.draw(0, 0);
		g.drawString("HIGHSCORE", 200, 200);
		HighScore a = new HighScore();
		a.read();
		int size = a.listHighscore.size();
		for (int i=0; i<size; i++){
		g.drawString(a.listHighscore.get(size-1-i).getName(), 200, 250+i*20);
		g.drawString(""+ a.listHighscore.get(size - 1 - i).getScore(), 350, 250+i*20);
		}
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		
		Input mc = gc.getInput();
		int mouseX = mc.getMouseX();
		int mouseY = mc.getMouseY();
		
		if(mouseX<=gc.getWidth() && mouseY <= gc.getHeight()){
			if(mc.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				sbg.enterState(TowerDefense.mainMenu);
			}
		}

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		return stateID;
	}

}
