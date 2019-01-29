/**
 * 
 */
package de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl;

import adapter.TowerDefenseTestAdapterExtended2;
import de.tu_darmstadt.gdi1.towerdefense.classes.GuiObject;

import java.awt.*;

/**
 * @author Michael
 *
 */
public class TowerDefenseTestAdapterExtended2Impl extends TowerDefenseTestAdapterExtended1Impl
	implements TowerDefenseTestAdapterExtended2 {

	@Override
	public void sellTowerAtCoordinate(Point position) {
		String tower = null;
		int xM = 0;
		int yM = 0;
		
		if(GuiObject.GuiObjectMap.containsKey("tower0")){
			tower = "tower0";
			 xM = GuiObject.getX("tower0");
			 yM = GuiObject.getY("tower0");}
		
		if(position.getX()==xM & position.getY()==yM){
			game.SellTower(tower);
		}
	}

	
	@Override
	public void upgradeTowerAtCoordinate(Point position) {
		String tower = null;
		int xM = 0;
		int yM = 0;
		for (int i = 0; i<game.counterT; i++){
		if(GuiObject.GuiObjectMap.containsKey("tower"+i)){
			tower = "tower"+i;
			 xM = GuiObject.getX("tower"+i);
			 yM = GuiObject.getY("tower"+i);}
		}
		if(position.getX()==xM & position.getY()==yM){
			game.UpgradeTower(tower);
			game.Gold = game.Gold -1;
		}
	}
}

	