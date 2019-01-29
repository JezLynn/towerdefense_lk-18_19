/**
 * 
 */
package de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl;

import java.awt.Point;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterExtended1;

/**
 * @author Michael
 *
 */
public class TowerDefenseTestAdapterExtended1Impl extends TowerDefenseTestAdapterMinimalImpl implements TowerDefenseTestAdapterExtended1 {


	@Override
	public void buyAndPlaceDefaultTowerTo(Point coordinates) {
		game.setTower((int)coordinates.getY(), (int)coordinates.getX(), 'H');
	}

	@Override
	public void setMoney(double value) {
		game.Gold = (int)value;
		
	}

	@Override
	public double getMoney() {
		return game.Gold;
		 
	}



}
