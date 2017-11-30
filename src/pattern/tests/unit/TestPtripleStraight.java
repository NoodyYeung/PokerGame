package pattern.tests.unit;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import pattern.*;


public class TestPtripleStraight {

	ArrayList<Integer> lowerValueStraightList;
	ArrayList<Integer> higherValueStraightList;
	ArrayList<Integer> shortTripleStraightList;
	
	PtripleStraight thisTripleStraight;
	
	@Before
	// precondition : cards are sorted in ascending order,
	// cards are all consecutive
	public void setup() throws Exception {
		lowerValueStraightList = new ArrayList<>();
		higherValueStraightList = new ArrayList<>();
		shortTripleStraightList = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			lowerValueStraightList.add(i);
			higherValueStraightList.add(i + 5);
			if(i>=3) {
				shortTripleStraightList.add(i+2); 
			}
		}
	}
	
	@Test 
	public void getNum() {
		thisTripleStraight = new PtripleStraight(lowerValueStraightList);
		assertEquals(5, thisTripleStraight.getNum());
	}
	
	@Test 
	public void getValue() {
		thisTripleStraight = new PtripleStraight(lowerValueStraightList);
		assertEquals(5, thisTripleStraight.getValue());
	}
	@Test
	public void testIsSamePattern1() {
		thisTripleStraight = new PtripleStraight(lowerValueStraightList);
		assertEquals(true, thisTripleStraight.isSamePattern(thisTripleStraight));
	}
	
	@Test
	public void testIsSamePattern2() {
		thisTripleStraight = new PtripleStraight(lowerValueStraightList);
		PtripleStraight shorterTripleStraight = new PtripleStraight(shortTripleStraightList);
		assertEquals(false, thisTripleStraight.isSamePattern(shorterTripleStraight));
	}
	
	@Test
	public void testIsSamePattern3() {
		thisTripleStraight = new PtripleStraight(higherValueStraightList);
		Pbomb thisBomb = new Pbomb(10);
		assertEquals(false, thisTripleStraight.isSamePattern(thisBomb));
	}
	
	@Test
	public void testIsLarger1() {
		thisTripleStraight = new PtripleStraight(higherValueStraightList);
		PtripleStraight lowerStraight = new PtripleStraight(lowerValueStraightList);
		assertEquals(true, thisTripleStraight.isLarger(lowerStraight));
	}
	
	@Test
	public void testIsLarger2() {
		thisTripleStraight = new PtripleStraight(lowerValueStraightList);
		PtripleStraight higherStraight = new PtripleStraight(higherValueStraightList);
		assertEquals(false, thisTripleStraight.isLarger(higherStraight));
	}
}