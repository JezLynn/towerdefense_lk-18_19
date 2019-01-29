package de.tu_darmstadt.gdi1.towerdefense.test.pub.suites;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases.LevelGeneratorTest;

public class TowerDefenseTestsuiteExtended3 {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Student tests for TowerDefense - Extended 3");
		//$JUnit-BEGIN$
		suite.addTest(new JUnit4TestAdapter(LevelGeneratorTest.class));
		//$JUnit-END$
		return suite;
	}
}
