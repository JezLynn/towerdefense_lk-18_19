package de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl.TowerDefenseTestAdapterMinimalImpl;

/**
 * Tests whether the implementation can spawn and move monsters correctly
 * 
 * @author Oliver Guenther
 * 
 */
public class MonsterMovementTest {

	/** Unit under test */
	private static final TowerDefenseTestAdapterMinimal uut = new TowerDefenseTestAdapterMinimalImpl();

	@Before
	public void beforeTest() {
		uut.reset();
	}

	/** Correct test level */
	private static String testLevel = 
			"#####\n" +
	        "#___#\n" +
	        "#S>X#\n" +
	        "#___#\n" +
	        "#####";

	@Test
	public void testSpawnMonster() {
		uut.loadLevelFromString(testLevel);
		uut.startGame();
		uut.spawnMonster(1, 2);
		uut.spawnMonster(0, 0);
		uut.spawnMonster(2, 2);

		assertTrue("No monster spawned at spawn on command", uut.isMonsterAt(1, 2));
		assertFalse("A monster was spawned on impassable terrain", uut.isMonsterAt(0, 0));
		assertFalse("Monsters should not be spawned on anything but spawns", uut.isMonsterAt(2, 2));
	}

	@Test
	public void testMoveMonster() throws InterruptedException {
		uut.loadLevelFromString(testLevel);
		uut.startGame();
		uut.spawnMonster(1, 2);
		assertTrue("No monster spawned at spawn on command", uut.isMonsterAt(1, 2));
		
		// One tick later
		uut.tick();
		assertTrue("Monster should have moved to (2,2)", uut.isMonsterAt(2, 2));
	}
}
