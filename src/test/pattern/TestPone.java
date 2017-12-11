package test.pattern;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import pattern.*;

public class TestPone {

	Pone thisPone;
	Pone higherPone;
	
	@Before
	public void setUp() throws Exception {
		thisPone = new Pone(4);
		higherPone = new Pone(6);
	}
	
	@Test
	public void testGetValue1() {
		assertEquals(6, higherPone.getValue());
	}
	
	@Test 
	public void testGetNum1() {
		assertEquals(1, thisPone.getNum());
	}
	@Test
	public void testIsSamePattern1() {
		assertEquals(true, thisPone.isSamePattern(higherPone));
	}
	
	@Test
	public void testIsSamePattern2() {
		Pbomb thisBomb = new Pbomb(5);
		assertEquals(false, thisPone.isSamePattern(thisBomb));
	}
	
	@Test 
	public void testIsLarger1() {
		assertEquals(false, thisPone.isLarger(higherPone));
	}
	
	@Test
	public void testIsLarger2() {
		assertEquals(true, higherPone.isLarger(thisPone));
	}
	
	@Test
	// this case will never arise, so the code should logically return false
	public void testIsLarger3() {
		Ptwo thisPtwo = new Ptwo(2);
		assertEquals(false, thisPone.isLarger(thisPtwo));
	}
	
	@Test
	public void testEquals1() {
		boolean result = thisPone.equals(new Pone(4));
		assertEquals(true, result);
	}
	
	@Test
	public void testEquals2() {
		boolean result = thisPone.equals(new Pone(5));
		assertEquals(false, result);
	}
	
	@Test 
	public void testEquals3() {
		boolean result = thisPone.equals(new Pbomb(3));
		assertEquals(false, result);
	}
}
