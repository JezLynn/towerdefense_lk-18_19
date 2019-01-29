package de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterExtended1;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl.TowerDefenseTestAdapterExtended1Impl;

/**
 * Tests whether the implementation handles money changes
 * @author Oliver Guenther
 *
 */
public class MoneySystemTest {

	/** Unit under test */
	private static final TowerDefenseTestAdapterExtended1 uut = new TowerDefenseTestAdapterExtended1Impl();
	
	@Before
	public void beforeTest() {
		uut.reset();
		uut.loadLevelFromString(testLevel);
		uut.startGame();
	}
	
	String[] correctLevels = {
			"#####\n" + "#___#\n" + "#S>X#\n" + "#___#\n" + "#####",
			"#####\n" + "#S>X#\n" + "#####",
			"____\n" + "S>>X\n" + "____",
	};
	
	/** Correct test level */
	private static String testLevel = 
			"#####\n" +
	        "#___#\n" +
	        "#S>X#\n" +
	        "#___#\n" +
	        "#####\n";

	@Test
	public void testStaringMoney(){
		assertTrue("The Player should have some starting money available", uut.getMoney() > 0);
	}
	
	@Test
	public void testSetMoney() {
		double newMoney = 42.0;
		uut.setMoney(newMoney);
		assertEquals("Money was not set correctly", newMoney, uut.getMoney(), 0.1);
	}
	
}
