package pattern.tests.unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pattern.*;
import pattern.tests.stubs.*;

public class TestPDoubleStraight {
	PdoubleStraight thisDoubleStraight;
	@Before
	public void setUp() throws Exception {
		ArrayList<Integer> listOfValues = new ArrayList<Integer>();
		for(int i = 1; i <= 5; i++) {
			listOfValues.set(i, i);
		}
		thisDoubleStraight = new PdoubleStraight(listOfValues);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetValue1() {
		int result = thisDoubleStraight.getValue();
		assertEquals(5, result);
	}
	
	@Test 
	// precondition , list input is sorted
	public void testIsSamePattern1() {
		PbombTestStubA thisBombStub = new PbombTestStubA(4);
		boolean result = thisDoubleStraight.isSamePattern(thisBombStub);
		assertEquals(false, result);
	}
	
	@Test 
	// precondition , list input is sorted
	public void testIsSamePattern2() {
		ArrayList<Integer> anotherListOfValues = new ArrayList<>();
		PdoubleStraightTestStubA thisDoubleStraightStub = new PdoubleStraightTestStubA(anotherListOfValues);
		boolean result = thisDoubleStraight.isSamePattern(thisDoubleStraightStub);
		assertEquals(true, result);
	}
	@Test 
	// precondition , list input is sorted
	public void testIsSamePattern3() {
		PoneTestStubA thisOneStub = new PoneTestStubA(1);
		boolean result = thisDoubleStraight.isSamePattern(thisOneStub);
		assertEquals(true, result);
	}
	
	@Test
	//precondition , list input is sorted
	public void testIsLarger1() {
		PoneTestStubA thisOneStub = new PoneTestStubA(1);
		boolean result = thisDoubleStraight.isLarger(thisOneStub);
		assertEquals(true, result);
	}
	
	@Test
	// precondition, list input is sorted
	public void testIsLarger2() {
		PoneTestStubA thisOneStub = new PoneTestStubA(6);
		boolean result = thisDoubleStraight.isLarger(thisOneStub);
		assertEquals(false, result);
	}
	
	@Test
	//precondition, list input is sorted
	public void testIsLarger3() {
		PbombTestStubA thisBombStub = new PbombTestStubA(6);
		boolean result = thisDoubleStraight.isLarger(thisBombStub);
		assertEquals(false, result);
	}
	
	@Test
	//  precondition , list input is sorted
	public void testIsLarger4() {
		ArrayList<Integer> anotherListOfValues = new ArrayList<>();
		for (int i = 3; i<=8; i++) {
			anotherListOfValues.add(i);
		}
		PdoubleStraightTestStubA thisDoubleStraightStub = new PdoubleStraightTestStubA(anotherListOfValues);
		boolean result = thisDoubleStraight.isLarger(thisDoubleStraightStub);
		assertEquals(false, result);
	}
}
