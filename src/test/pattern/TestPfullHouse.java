package test.pattern;

import java.util.*;


import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import pattern.*;


public class TestPfullHouse {
	ArrayList<Integer> house1list;
	ArrayList<Integer> house2list;
	ArrayList<Integer> house3list;
	
	PfullHouse house1;
	PfullHouse house2;
	PfullHouse house3;
	
	@Before
	public void setup() throws Exception{
		house1list = new ArrayList<Integer>();
		house2list = new ArrayList<Integer>();
		house3list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++ ) {
			house1list.add(12);
			house2list.add(2);
			house3list.add(2);
		}
		

		house1 = new PfullHouse(house1list, 1);
		house2 = new PfullHouse(house2list, 1);
		house3 = new PfullHouse(house3list, 2);
		// upper house = Q-Q-Q-6-6 beats 
		// lower house = 10-10-10-K-K	
	}
	
	@Test
	public void testIsSamePattern1() {
		assertEquals(true, house1.isSamePattern(house2));
	}
	
	@Test
	public void testIsSamePattern2() {
		assertEquals(false, house1.isSamePattern(new PfullHouse(house1list, 2)));
	}
	@Test 
	public void testIsSamePattern3() {
		assertEquals(false, house1.isSamePattern(new Pone(1)));
	}
	
	@Test
	public void testIsLarger1() {
		assertEquals(true, house1.isLarger(new Pone(2)));
	}
	
	@Test
	public void testIsLarger2() {
		assertEquals(false, house2.isLarger(new Pone(2)));
	}
	
}
