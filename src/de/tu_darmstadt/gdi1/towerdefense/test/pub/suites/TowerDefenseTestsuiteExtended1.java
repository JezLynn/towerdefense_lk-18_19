package de.tu_darmstadt.gdi1.towerdefense.test.pub.suites;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases.BasicTowerTest;

public class TowerDefenseTestsuiteExtended1 {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Student tests for TowerDefense - Extended 1");
		//$JUnit-BEGIN$
		suite.addTest(new JUnit4TestAdapter(BasicTowerTest.class));
		//$JUnit-END$
		return suite;
	}
}
