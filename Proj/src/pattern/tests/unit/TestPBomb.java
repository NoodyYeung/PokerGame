package pattern.tests.unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pattern.*;
import pattern.tests.stubs.*;

public class TestPBomb {
	Pbomb thisBomb;
	@Before
	public void setUp() throws Exception {
		thisBomb = new Pbomb(4);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	// PBomb x PDoubleStraight
	public void testIsSamePattern1() {
		ArrayList<Integer> thisListOfValues = new ArrayList<>();
		for(int i = 0; i< 5; i++) {
			thisListOfValues.add(i + 1);
			thisListOfValues.add(i + 1);
		}
		PdoubleStraightTestStubA another = new PdoubleStraightTestStubA(thisListOfValues);
		boolean result = thisBomb.isSamePattern(another);
		assertEquals(false,result);
	}
	
	@Test
	// PBomb x PDoubleStraight
	public void testIsSamePattern2() {
		PbombTestStubA another = new PbombTestStubA(10);
		boolean result = thisBomb.isSamePattern(another);
		assertEquals(false,result);
	}
	
	@Test
	public void isLarger1() {
		ArrayList<Integer> thisListOfValues = new ArrayList<>();
		for(int i = 0; i< 5; i++) {
			thisListOfValues.add(i + 1);
			thisListOfValues.add(i + 1);
		}
		PdoubleStraightTestStubA another = new PdoubleStraightTestStubA(thisListOfValues);
		boolean result = thisBomb.isLarger(another);
		assertEquals(true, result);
	}	
	
	@Test
	public void isLarger2() {
		PbombTestStubA another = new PbombTestStubA(3);
		boolean result = thisBomb.isLarger(another);
		assertEquals(true, result);
	}	
	
	@Test
	public void isLarger3() {
		PbombTestStubA another = new PbombTestStubA(5);
		boolean result = thisBomb.isLarger(another);
		assertEquals(true, result);
	}
	
}
