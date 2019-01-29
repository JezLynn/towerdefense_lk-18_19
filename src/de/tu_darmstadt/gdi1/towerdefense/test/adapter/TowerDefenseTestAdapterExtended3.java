package de.tu_darmstadt.gdi1.towerdefense.test.adapter;

import adapter.TowerDefenseTestAdapterExtended2;

public interface TowerDefenseTestAdapterExtended3 extends TowerDefenseTestAdapterExtended2 {
	/**
	 * @return Creates a valid level with given size using the implemented level generator
	 */
	public String generateLevel(int width, int height);
}
