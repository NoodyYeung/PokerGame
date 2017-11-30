package pattern.tests.unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import pattern.*;

public class TestPdoubleStraight {
	PdoubleStraight thisDoubleStraight;
	ArrayList<Integer> lowerValueList;
	ArrayList<Integer> higherValueList;
	
	ArrayList<Integer> shorterListOfValues;
	
	@Before
	public void setUp() throws Exception {
		higherValueList = new ArrayList<Integer>();
		lowerValueList = new ArrayList<Integer>();
		shorterListOfValues = new ArrayList<Integer>();
		for(int i = 1; i <= 5; i++) {
			lowerValueList.add(i);
			lowerValueList.add(i);
			higherValueList.add(i + 5);
			higherValueList.add(i + 5);
			if(i>=3) {
				shorterListOfValues.add(i+6);
			}
		}
	}

	@Test
	public void testGetValue1() {
		thisDoubleStraight = new PdoubleStraight(higherValueList);
		int result = thisDoubleStraight.getValue();
		assertEquals(10, result);
	}
	
	
	@Test
	public void testGetNum() {
		thisDoubleStraight = new PdoubleStraight(lowerValueList);
		int result = thisDoubleStraight.getNum();
		assertEquals(10, result);
	}
	
	@Test
	public void testIsSamePattern1() {
		thisDoubleStraight = new PdoubleStraight(lowerValueList);
		PdoubleStraight another = new PdoubleStraight(higherValueList);
		boolean result = thisDoubleStraight.isSamePattern(another);
		assertEquals(true, result);
	}
	
	@Test
	public void testIsSamePattern2() {
		thisDoubleStraight = new PdoubleStraight(lowerValueList);
		PdoubleStraight another = new PdoubleStraight(shorterListOfValues);
		assertEquals(false, thisDoubleStraight.isSamePattern(another));
	}
	
	@Test 
	public void testIsSamePattern3() {
		thisDoubleStraight = new PdoubleStraight(lowerValueList);
		Pone another = new Pone(1);
		assertEquals(false, thisDoubleStraight.isSamePattern(another));
	}
	
	@Test
	public void testIsLarger1() {
		Pbomb thisBomb = new Pbomb(1);
		thisDoubleStraight = new PdoubleStraight(higherValueList);
		boolean result = thisDoubleStraight.isLarger(thisBomb);
		assertEquals(false, result);
	}
	
	@Test
	public void testIsLarger2() {
		Procket thisRocket = new Procket();
		thisDoubleStraight = new PdoubleStraight(higherValueList);
		boolean result = thisDoubleStraight.isLarger(thisRocket);
		assertEquals(false, result);
	}
	
	@Test 
	public void testIsLarger3() {
		thisDoubleStraight = new PdoubleStraight(higherValueList);
		PdoubleStraight another = new PdoubleStraight(lowerValueList);
		boolean result = thisDoubleStraight.isLarger(another);
		assertEquals(true, result);
	}
	
	@Test 
	public void testIsLarger4() {
		thisDoubleStraight = new PdoubleStraight(lowerValueList);
		PdoubleStraight another = new PdoubleStraight(higherValueList);
		boolean result = thisDoubleStraight.isLarger(another);
		assertEquals(false, result);
	}
	
}
