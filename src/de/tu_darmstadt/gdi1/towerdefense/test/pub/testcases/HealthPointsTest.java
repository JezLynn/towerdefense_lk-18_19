package de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl.TowerDefenseTestAdapterMinimalImpl;

/**
 * Tests whether the implementation handles player health points correctly
 * @author Oliver Guenther
 *
 */
public class HealthPointsTest {

	/** Unit under test */
	private static final TowerDefenseTestAdapterMinimal uut = new TowerDefenseTestAdapterMinimalImpl();
	
	@Before
	public void beforeTest() {
		uut.reset();
		uut.loadLevelFromString(testLevel);
		uut.startGame();
	}
	
	/** Correct test level */
	private static String testLevel = 
			"#####\n" +
	        "#___#\n" +
	        "#S>X#\n" +
	        "#___#\n" +
	        "#####\n";

	@Test
	public void testPlayerHPNewGame() {
		assertTrue("Player HP was zero at game start", uut.getPlayerHealth() > 0);
	}
	
	@Test
	public void testPlayerHP() {
		assertTrue("Player HP was zero at game start", uut.getPlayerHealth() > 0);
		// Spawn monster
		int curHP = uut.getPlayerHealth();
		uut.spawnMonster(1, 2);
		assertTrue("Monster was not spawned at 1|2. ", uut.isMonsterAt(1, 2));
		
		// Tick, monster should be at 2,1 now
		uut.tick();
		assertTrue("Monster should now be at 2|2. ", uut.isMonsterAt(2, 2));

		// Tick, monster should be at target
		uut.tick();
		assertFalse("Monster reached the target and should have been removed", uut.isMonsterAt(3, 2));
		assertEquals("Player HP should have been reduced by one", curHP - 1, uut.getPlayerHealth());

	}
	

}
