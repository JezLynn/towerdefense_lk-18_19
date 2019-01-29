package de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl.TowerDefenseTestAdapterMinimalImpl;

public class TowerShotTest {

	/** Unit under test */
	private static final TowerDefenseTestAdapterMinimal uut = new TowerDefenseTestAdapterMinimalImpl();

	/** Correct test level */
	private static String testLevel = 	"#####\n" +
	                                  	"#___#\n" +
	                                    "#S>X#\n" +
	                                    "#___#\n" +
	                                    "#####\n";

	@Before
	public void beforeTest() {
		uut.reset();
	}

	@Test
	public void towerShotTest() {
		uut.loadLevelFromString(testLevel);
		uut.startGame();
		uut.placeTower(2, 1);
		uut.spawnMonster(1, 2);
		uut.tick();
		uut.tick();
		Assert.assertFalse("Monster should be killed", uut.isMonsterAt(1, 2));
		Assert.assertFalse("Monster should be killed", uut.isMonsterAt(2, 2));
		Assert.assertFalse("Monster should be killed", uut.isMonsterAt(3, 2));
	}
}
