package de.tu_darmstadt.gdi1.towerdefense.test.pub.suites;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerDefenseTestSuiteAll {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("All Student tests for TowerDefense");
		//$JUnit-BEGIN$
		suite.addTest(TowerDefenseTestsuiteMinimal.suite());
		suite.addTest(TowerDefenseTestsuiteExtended1.suite());
		suite.addTest(TowerDefenseTestsuiteExtended2.suite());
		suite.addTest(TowerDefenseTestsuiteExtended3.suite());
		//$JUnit-END$
		return suite;
	}
}
