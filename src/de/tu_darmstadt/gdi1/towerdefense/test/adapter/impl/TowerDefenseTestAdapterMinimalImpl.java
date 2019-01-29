/**
 * 
 */
package de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl;

import de.tu_darmstadt.gdi1.towerdefense.classes.GameEngine;
import de.tu_darmstadt.gdi1.towerdefense.classes.GuiObject;
import de.tu_darmstadt.gdi1.towerdefense.classes.ParsMap;
import de.tu_darmstadt.gdi1.towerdefense.exceptions.SyntaxNotCorrectException;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal;

/**
 * @author Michael
 *
 */
public class TowerDefenseTestAdapterMinimalImpl implements
		TowerDefenseTestAdapterMinimal {

	ParsMap parser;
	GameEngine game;
	boolean valid;
	
	public TowerDefenseTestAdapterMinimalImpl() {	
		parser = new ParsMap();
	}
	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#reset()
	 */
	@Override
	public void reset() {
		parser.parsedMap = new char[0][0];
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#loadLevelFromString(java.lang.String)
	 */
	@Override
	public void loadLevelFromString(String level){
		parser.parsedMap = new char[level.length() / level.indexOf("\n")]
				[level.indexOf("\n")];
		parser.stringToArray(level);
		
			try {
				valid = parser.isSyntaxCorrect();
				parser.arrayToGuiObject();
			} catch (SyntaxNotCorrectException e) {
				//e.printStackTrace();
				valid = false;
			}
		
		//System.out.println(valid);
		game = new GameEngine(10, 10);
		game.StartPoints = parser.startPointsCounter;
		game.GameMap = parser.parsedMap.clone();
		
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#levelToString()
	 */
	@Override
	public String levelToString() {
		String s = parser.toString();
		return s;
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#levelIsValid()
	 */
	@Override
	public boolean levelIsValid() {
		return valid;
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#levelElementAt(int, int)
	 */
	@Override
	public char levelElementAt(int x, int y) {
		return parser.getCharAt(y, x);
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#getPlayerHealth()
	 */
	@Override
	public int getPlayerHealth() {
		return game.get_LifePoints();
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#placeTower(int, int)
	 */
	@Override
	public void placeTower(int x, int y) {
		game.setTower(y, x, 'H');

	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#isTowerAt(int, int)
	 */
	@Override
	public boolean isTowerAt(int x, int y) {
		boolean a = false;
		if(GuiObject.GuiObjectMap.containsKey("tower0")){
				int xM = GuiObject.getX("tower0");
				int yM = GuiObject.getY("tower0");
				if(x==xM && y==yM){
					a = true;
			}
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#spawnMonster(int, int)
	 */
	@Override
	public void spawnMonster(int x, int y) {
		if(x==game.Start[1][0]&&y==game.Start[0][0]){
			game.StartMonster(1, 1, 1);
		}
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#isMonsterAt(int, int)
	 */
	@Override
	public boolean isMonsterAt(int x, int y) {
		if(GuiObject.GuiObjectMap.containsKey("monster1")){
		int xM = GuiObject.getX("monster1");
		int yM = GuiObject.getY("monster1");
		//System.out.println(yM + " " + xM);
			if(x==xM && y==yM){
				return true;
			}
		}
			return false;
		
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#monsterCount(int, int)
	 */
	@Override
	public int monsterCount(int x, int y) {
		int monstercount = 0; 
		for (int i = 0; i < game.NumbMonster; i++){
			if(GuiObject.GuiObjectMap.containsKey("monster"+i)){
				int xM = GuiObject.getX("monster1");
				int yM = GuiObject.getY("monster1");
				
				if(x==xM & y==yM){
					monstercount = monstercount + 1;
				}
			}
		}
		return monstercount;
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#startGame()
	 */
	@Override
	public void startGame() {
		game.checkStartTest();
		game.makeRoad();
	}

	/* (non-Javadoc)
	 * @see de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal#tick()
	 */
	@Override
	public void tick() {
//		System.out.println(GuiObject.getX("monster1"));
		
		for(int i=0; i<49; i++){
			game.moveAllMonster();
		}
		//System.out.println("Startpunkt "+GameEngine.monsterMap.get("monster1").Start+ " Step: " + GameEngine.monsterMap.get("monster1").steps);
		//System.out.println(GuiObject.getX("monster1"));

	}

}
