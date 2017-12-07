package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.command.*;
import test.ddz.*;
import test.pattern.*;
import test.cards.*;

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
	test.cards.stubs.cardTest.class,
	test.cards.cardsTest.class,
	test.cards.cardTest.class
	})

public class AllTests {

}
