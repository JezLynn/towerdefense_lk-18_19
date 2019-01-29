package de.tu_darmstadt.gdi1.towerdefense.test.adapter;

import java.awt.Point;

public interface TowerDefenseTestAdapterExtended1 extends TowerDefenseTestAdapterMinimal {
	/**
	 * Buys a tower and places it to the given position.
	 * This tower should be one which is represented as 't' in the default level format.
	 * 
	 * @param coordinates
	 */
	public void buyAndPlaceDefaultTowerTo(Point coordinates);
	
	/**
	 * Sets the player's amount of money to the given value
	 */
	public void setMoney(double value);
	
	/**
	 * @return Current amount of money that the player has. This value can either be an int- or double-value so this method should return double.
	 */
	public double getMoney();
}
