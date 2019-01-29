package de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterExtended1;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl.TowerDefenseTestAdapterExtended1Impl;

/**
 * Tests whether the implementation can buy and build towers correctly
 * @author Oliver Guenther
 *
 */
public class BasicTowerTest {

	/** Unit under test */
	private static final TowerDefenseTestAdapterExtended1 uut = new TowerDefenseTestAdapterExtended1Impl();
	
	@Before
	public void beforeTest() {
		uut.reset();
	}
	
	String[] correctLevels = {
			"#####\n" + "#___#\n" + "#S>X#\n" + "#___#\n" + "#####",
			"#####\n" + "#S>X#\n" + "#####",
			"____\n" + "S>>X\n" + "____",
	};
	
	/** Correct test level */
	private static String testLevel = "#####\n" +
	        "#___#\n" +
	        "#S>X#\n" +
	        "#___#\n" +
	        "#####\n";

	@Test
	public void testBuyTower(){
		uut.loadLevelFromString(testLevel);
		uut.startGame();
		
		double largeNumber = Double.MAX_VALUE / 2;
		
		uut.setMoney(largeNumber);
		uut.buyAndPlaceDefaultTowerTo(new Point(2, 1));
		Assert.assertTrue("After buying a tower you should have less money than before!", largeNumber > uut.getMoney());
		Assert.assertTrue("After buying and building a tower, it should be placed in the game.", uut.isTowerAt(2, 1));
	}
}
