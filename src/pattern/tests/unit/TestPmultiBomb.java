package pattern.tests.unit;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import pattern.*;


public class TestPmultiBomb {
	
	PmultiBomb thisMultiBombHigh;
	PmultiBomb thisMultiBombLow;
	
	ArrayList<Integer> multiBombHighList;
	ArrayList<Integer> multiBombLowList;
	ArrayList<Integer> doubleBombList;
	
	@Before
	public void setup() throws Exception{
		multiBombHighList = new ArrayList<>();
		multiBombLowList = new ArrayList<>();
		doubleBombList = new ArrayList<>();
		
		for(int i = 1; i < 4; i ++) {
			for(int j = 0; j < 3; j++) {
				multiBombHighList.add(i + 1);
				multiBombLowList.add(i);
				if(j < 2) {
					doubleBombList.add(i);
				}
			}
		}
		
		thisMultiBombHigh = new PmultiBomb(multiBombHighList);
		thisMultiBombLow = new PmultiBomb(multiBombLowList);
	}
	
	@Test
	public void testIsSamePattern1() {
		assertEquals(true, thisMultiBombHigh.isSamePattern(thisMultiBombLow));
	}
	
	@Test
	public void testIsSamePattern2() {
		assertEquals(false, thisMultiBombHigh.isSamePattern(new Pone(1)));
	}
	
	@Test
	public void testIsSamePattern3() {
		assertEquals(false, thisMultiBombHigh.isSamePattern(new PmultiBomb(doubleBombList)));
	}
	
	@Test
	public void testIsLarger1() {
		assertEquals(true, thisMultiBombHigh.isLarger(thisMultiBombLow));
	}
	
	@Test
	public void testIsLarger2() {
		assertEquals(false, thisMultiBombLow.isLarger(thisMultiBombHigh));
	}
}
