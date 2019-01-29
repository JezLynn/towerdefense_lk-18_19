package de.tu_darmstadt.gdi1.towerdefense.test.pub.suites;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases.AdvancedTowerTest;

public class TowerDefenseTestsuiteExtended2 {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Student tests for TowerDefense - Extended 2");
		//$JUnit-BEGIN$
		suite.addTest(new JUnit4TestAdapter(AdvancedTowerTest.class));
		//$JUnit-END$
		return suite;
	}
}
