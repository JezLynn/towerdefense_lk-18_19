package de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterMinimal;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl.TowerDefenseTestAdapterMinimalImpl;

/**
 * Tests whether the implementation can load levels correctly
 * @author Oliver Guenther
 *
 */
public class LoadLevelFromStringTest {

	/** Unit under test */
	private static final TowerDefenseTestAdapterMinimal uut = new TowerDefenseTestAdapterMinimalImpl();
	
	@Before
	public void beforeTest() {
		uut.reset();
	}


	String[] correctLevels = {
			"#####\n" + "#___#\n" + "#S>X#\n" + "#___#\n" + "#####",
			
			"#####\n" + "#S>X#\n" + "#####",
			
			"____\n" + "S>>X\n" + "____",
	};
	
	@Test
	public void testLevelToString() {
		for (String testLevel : correctLevels) {
			uut.loadLevelFromString(testLevel);
			assertEquals("Generated string chars of the level is not same as loaded level", testLevel, uut.levelToString().trim());
		}
		
	}


}
