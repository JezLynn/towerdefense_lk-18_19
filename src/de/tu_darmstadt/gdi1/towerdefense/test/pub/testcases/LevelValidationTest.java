package de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl.TowerDefenseTestAdapterMinimalImpl;

/**
 * Tests whether the implementation can validate levels correctly
 * @author Oliver Guenther
 *
 */
public class LevelValidationTest {

	/** Unit under test */
	private static final TowerDefenseTestAdapterMinimal uut = new TowerDefenseTestAdapterMinimalImpl();
	
	@Before
	public void beforeTest() {
		uut.reset();
	}
	
	String[] correctLevels = {
			
				"#####\n" +
				"#___#\n" +
				"#S>X#\n" +
				"#___#\n" +
				"#####",
				
				"#####\n" +
				"#S>X#\n" +
				"#####",
				
				"____\n" +
				"S>>X\n" +
				"____",
	};
	
	/** Correct test level */
	private static String testLevel = 
			"#####\n" +
	        "#___#\n" +
	        "#S>X#\n" +
	        "#___#\n" +
	        "#####";

	/** Level without a spawn */
	private static String faultyLevel = 
			"#####\n" +
	        "#___#\n" +
	        "#>>X#\n" +
	        "#___#\n" +
	        "#####";

	/** Level without a target */
	private static String faultyLevel2 = 
			"#####\n" +
	        "#___#\n" +
	        "#S>>#\n" +
	        "#___#\n" +
	        "#####";

	/** No valid path */
	private static String faultyLevel3 = 
			"#####\n" +
	        "#S>v#\n" +
	        "#___#\n" +
	        "#__X#\n" +
	        "#####";
	
	@Test
	public void testReset() {
		String level = uut.levelToString().trim();
		assertEquals("Level is not empty after a reset", "", level);
	}
	
	@Test
	public void testLevelValid() {
		for (String level : correctLevels) {
			uut.loadLevelFromString(level);
			uut.startGame();
			assertTrue("Test level incorrectly invalidated", uut.levelIsValid());
		}
	}
	@Test
	public void testLevelLoadCorrect() {
		uut.loadLevelFromString(testLevel);
		uut.startGame();
		
		assertEquals("Incorrect character at given coordinates: ", '#', uut.levelElementAt(0, 0));
		assertEquals("Incorrect character at given coordinates: ", 'S', uut.levelElementAt(1, 2));
		assertEquals("Incorrect character at given coordinates: ", 'X', uut.levelElementAt(3, 2));
		assertEquals("Incorrect character at given coordinates: ", '_', uut.levelElementAt(2, 1));
		assertEquals("Incorrect character at given coordinates: ", '>', uut.levelElementAt(2, 2));

		assertTrue("Test level incorrectly invalidated", uut.levelIsValid());
	}
	
	@Test
	public void testInvalidateEmptyLevel() {
		uut.loadLevelFromString(" \n");
		assertFalse("Empty level was not invalidated", uut.levelIsValid());
	}

	@Test
	public void testInvalidateLevelWithNoSpawn() {
		uut.loadLevelFromString(faultyLevel);
		assertFalse("Level with no spawn was not invalidated", uut.levelIsValid());
	}

	@Test
	public void testInvalidateLevelWithNoTarget() {
		uut.loadLevelFromString(faultyLevel2);
		assertFalse("Level with no target was not invalidated", uut.levelIsValid());
	}

	@Test
	public void testInvalidateLevelWithNoPath() {
		uut.loadLevelFromString(faultyLevel3);
		assertFalse("Level with no path from spawn to target was not invalidated", uut.levelIsValid());
	}


}
