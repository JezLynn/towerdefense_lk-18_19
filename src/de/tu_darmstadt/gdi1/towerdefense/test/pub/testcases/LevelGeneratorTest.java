package de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.gdi1.towerdefense.test.adapter.TowerDefenseTestAdapterExtended3;
import de.tu_darmstadt.gdi1.towerdefense.test.adapter.impl.TowerDefenseTestAdapterExtended3Impl;

/**
 * Tests whether the implementation can generate a level
 * @author Oliver Guenther
 *
 */
public class LevelGeneratorTest {

	/** Unit under test */
	private static final TowerDefenseTestAdapterExtended3 uut = new TowerDefenseTestAdapterExtended3Impl();

	@Before
	public void beforeTest() {
		uut.reset();
	}

	
	@Test
	public void testLevelGenerator(){
		Set<String> storage = new LinkedHashSet<String>(10);
		for (int i = 0; i < 10; i++) {
			String generatedLevel = uut.generateLevel(20, 20);
			uut.loadLevelFromString(generatedLevel);
			uut.startGame();
			Assert.assertTrue(uut.levelIsValid());
			if (storage.contains(generatedLevel)) {
				System.err.println("Duplicate level returned from Generator in iteration " + i);
			}
			storage.add(generatedLevel);
		}

	}
}
