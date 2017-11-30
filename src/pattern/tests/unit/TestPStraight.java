package pattern.tests.unit;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import pattern.*;

public class TestPStraight {
	
	ArrayList<Integer> lowerValueStraightList;
	ArrayList<Integer> higherValueStraightList;
	ArrayList<Integer> shortStraightList;
	
	Pstraight thisStraight;
	
	@Before
	// precondition : cards are sorted in ascending order,
	// cards are all consecutive
	public void setup() throws Exception {
		lowerValueStraightList = new ArrayList<>();
		higherValueStraightList = new ArrayList<>();
		shortStraightList = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			lowerValueStraightList.add(i);
			higherValueStraightList.add(i + 5);
			if(i>=3) {
				shortStraightList.add(i+2);
			}
		}
	}
	
	@Test 
	public void getNum() {
		thisStraight = new Pstraight(lowerValueStraightList);
		assertEquals(5, thisStraight.getNum());
	}
	
	@Test 
	public void getValue() {
		thisStraight = new Pstraight(lowerValueStraightList);
		assertEquals(5, thisStraight.getValue());
	}
	@Test
	public void testIsSamePattern1() {
		thisStraight = new Pstraight(lowerValueStraightList);
		assertEquals(true, thisStraight.isSamePattern(thisStraight));
	}
	
	@Test
	public void testIsSamePattern2() {
		thisStraight = new Pstraight(lowerValueStraightList);
		Pstraight shorterStraight = new Pstraight(shortStraightList);
		assertEquals(false, thisStraight.isSamePattern(shorterStraight));
	}
	
	@Test
	public void testIsSamePattern3() {
		thisStraight = new Pstraight(higherValueStraightList);
		Pbomb thisBomb = new Pbomb(10);
		assertEquals(false, thisStraight.isSamePattern(thisBomb));
	}
	
	@Test
	public void testIsLarger1() {
		thisStraight = new Pstraight(higherValueStraightList);
		Pstraight lowerStraight = new Pstraight(lowerValueStraightList);
		assertEquals(true, thisStraight.isLarger(lowerStraight));
	}
	
	@Test
	public void testIsLarger2() {
		thisStraight = new Pstraight(lowerValueStraightList);
		Pstraight higherStraight = new Pstraight(higherValueStraightList);
		assertEquals(false, thisStraight.isLarger(higherStraight));
	}
	
}
