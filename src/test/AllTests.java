package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ 
	test.command.CmdQuitTest.class,
	test.ddz.TestDDZ.class,
	test.pattern.TestPone.class,
	test.pattern.TestPstraight.class,
	test.pattern.TestPfullHouse.class,
	test.pattern.TestPthree.class,
	test.pattern.TestPtriplePlusOne.class,
	test.pattern.TestPtripleStraight.class,
	test.pattern.TestPTwo.class,
	test.pattern.TestPbomb.class,
	test.pattern.TestPdoubleStraight.class,
	test.pattern.TestPmultiBomb.class,
	test.cards.TestCard.class,
	test.cards.TestCards.class
	})

public class AllTests {

}
