package pattern.tests.unit;

import java.util.ArrayList;

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
}

