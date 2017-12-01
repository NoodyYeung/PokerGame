package pattern.tests.unit;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import pattern.*;

public class TestPtriplePlusOne {
	
	ArrayList<Integer> triple1list;
	ArrayList<Integer> triple2list;
	
	PfullHouse triple1;
	PfullHouse triple2;
	
	@Before
	public void setup() throws Exception{
		triple1list = new ArrayList<Integer>();
		triple2list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++ ) {
			triple1list.add(12);
			triple2list.add(2);
		}

		triple1 = new PfullHouse(triple1list, 1);
		triple2 = new PfullHouse(triple2list, 1);

	}
	
	@Test
	public void testIsSamePattern1() {
		assertEquals(true, triple1.isSamePattern(triple2));
	}
	
	@Test
	public void testIsSamePattern2() {
		assertEquals(false, triple1.isSamePattern(new PtriplePlusOne(triple1list, 2)));
	}
	@Test 
	public void testIsSamePattern3() {
		assertEquals(false, triple1.isSamePattern(new Pone(1)));
	}
	
	@Test
	public void testIsLarger1() {
		assertEquals(true, triple1.isLarger(new Pone(2)));
	}
	
	@Test
	public void testIsLarger2() {
		assertEquals(false, triple2.isLarger(new Pone(2)));
	}
	
}