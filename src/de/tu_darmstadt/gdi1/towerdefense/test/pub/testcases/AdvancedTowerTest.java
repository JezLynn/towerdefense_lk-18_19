package de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases;

import java.awt.Point;

import adapter.TowerDefenseTestAdapterExtended2;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl.TowerDefenseTestAdapterExtended2Impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests whether the implementation can upgrade and sell towers
 * @author Oliver Guenther
 *
 */
public class AdvancedTowerTest {

	/** Unit under test */
	private static final TowerDefenseTestAdapterExtended2 uut = (TowerDefenseTestAdapterExtended2) new TowerDefenseTestAdapterExtended2Impl();

	@Before
	public void beforeTest() {
		uut.reset();
	}

	private static String testLevel = "#####\n" +
			"#___#\n" +
			"#S>X#\n" +
			"#___#\n" +
			"#####\n";

	private static String testLevelWithTower = 
			"#####\n" +
			"#_t_#\n" +
			"#S>X#\n" +
			"#___#\n" +
			"#####\n";
	
	private double largeValue = Double.MAX_VALUE / 2;


	@Before
	public void setup(){
		uut.reset();
	}

	@Test
	public void testUpgradeTower(){
		uut.loadLevelFromString(testLevel);
		uut.startGame();
		uut.setMoney(largeValue);
		uut.buyAndPlaceDefaultTowerTo(new Point(2,1));
		double moneyAfterBuyingTower = uut.getMoney();
		Assert.assertTrue(moneyAfterBuyingTower < largeValue);
		Assert.assertTrue(uut.isTowerAt(2, 1));
		uut.upgradeTowerAtCoordinate(new Point(2,1));
		Assert.assertTrue("Upgrading a tower should cost money!", moneyAfterBuyingTower > uut.getMoney());
	}

	@Test
	public void testSellTower(){
		uut.loadLevelFromString(testLevel);
		uut.startGame();
		uut.setMoney(largeValue);
		uut.buyAndPlaceDefaultTowerTo(new Point(2,1));
		double moneyAfterBuyingTower = uut.getMoney();
		Assert.assertTrue(moneyAfterBuyingTower < largeValue);
		uut.sellTowerAtCoordinate(new Point(2, 1));
		Assert.assertTrue("Selling a tower should give back money!", moneyAfterBuyingTower < uut.getMoney());
		Assert.assertTrue("Selling a tower should give back less money than it costed to buy the tower", uut.getMoney() < largeValue);
	}

	@Test
	public void testSellExistingTower(){
		uut.loadLevelFromString(testLevelWithTower);
		uut.startGame();
		uut.setMoney(0);
		uut.sellTowerAtCoordinate(new Point(2, 1));
		Assert.assertTrue("Selling a tower should return some money", uut.getMoney() > 0);
	}
}
