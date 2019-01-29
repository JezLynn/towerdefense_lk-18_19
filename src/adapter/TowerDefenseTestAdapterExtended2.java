package adapter;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterExtended1;

import java.awt.Point;

public interface TowerDefenseTestAdapterExtended2 extends TowerDefenseTestAdapterExtended1 {
	/**
	 * Sells a tower at a given position
	 * 
	 * @param position coordinates of the tower
	 */
	public void sellTowerAtCoordinate(Point position);
	
	/**
	 * Upgrades a tower at the given coordinates
	 * @param position coordinates of the tower
	 */
	public void upgradeTowerAtCoordinate(Point position);
}
