package test.pattern;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import pattern.*;


public class TestPthree {
	
	Pthree highPthree;
	Pthree lowPthree;
	Pbomb thisPbomb;
	
	@Before
	public void setup() throws Exception{
		highPthree = new Pthree(11);
		lowPthree = new Pthree(9);
	}
	
	@Test
	public void testGetValue() {
		assertEquals(11, highPthree.getValue());
	}
	
	@Test public void testGetNum() {
		assertEquals(3, highPthree.getNum());
	}
	@Test
	public void testIsSamePattern1() {
		thisPbomb = new Pbomb(1);
		assertEquals(false, highPthree.isSamePattern(thisPbomb));
	}
	
	@Test
	public void testIsSamePattern2() {
		assertEquals(true, highPthree.isSamePattern(lowPthree));
	}
	
	@Test
	public void testIsLarger1() {
		assertEquals(true, highPthree.isLarger(lowPthree));
	}
	
	@Test
	public void testIsLarger2() {
		assertEquals(false, lowPthree.isLarger(highPthree));
	}
	
	
	@Test
	public void testIsLarger3() {
		thisPbomb = new Pbomb(6); // true because this case will hypothetically never arise// code smell
		assertEquals(true, highPthree.isLarger(thisPbomb));
	}
	
	@Test
	public void testEquals1() {
		boolean result = highPthree.equals(new Pthree(11));
		assertEquals(true, result);
	}
	
	@Test
	public void testEquals2() {
		boolean result = highPthree.equals(new Pthree(4));
		assertEquals(false, result);
	}
	
	@Test 
	public void testEquals3() {
		boolean result = highPthree.equals(new Pbomb(3));
		assertEquals(false, result);
	}
}

