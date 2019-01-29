package de.tu_darmstadt.gdi1.towerdefense.test.pub.suites;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import de.tu_darmstadt.gdi1.towerdefense.test.pub.testcases.*;

public class TowerDefenseTestsuiteMinimal {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Student tests for TowerDefense - Minimal");
		//$JUnit-BEGIN$
		suite.addTest(new JUnit4TestAdapter(LoadLevelFromStringTest.class));
		suite.addTest(new JUnit4TestAdapter(LevelValidationTest.class));
		suite.addTest(new JUnit4TestAdapter(MonsterMovementTest.class));
		suite.addTest(new JUnit4TestAdapter(TowerShotTest.class));
		suite.addTest(new JUnit4TestAdapter(HealthPointsTest.class));
		suite.addTest(new JUnit4TestAdapter(MoneySystemTest.class));

		//$JUnit-END$
		return suite;
	}
}
