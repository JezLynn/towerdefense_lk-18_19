/**
 * 
 */
package de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl;

import de.tu_darmstadt.gdi1.towerdefense.MapBuilder.Builder;
import de.tu_darmstadt.gdi1.towerdefense.classes.ParsMap;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterExtended3;



public class TowerDefenseTestAdapterExtended3Impl extends TowerDefenseTestAdapterExtended2Impl  
	implements TowerDefenseTestAdapterExtended3 {

	ParsMap parser;
	Builder builder;
	@Override
	public String generateLevel(int width, int height) {
		
		Builder b = new Builder();
		return b.toString();
	}
}