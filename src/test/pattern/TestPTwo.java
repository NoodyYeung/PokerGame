package test.pattern;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import pattern.*;


public class TestPTwo {

	Ptwo thisPtwo;
	Ptwo higherPtwo;
	
	@Before
	public void setUp() throws Exception {
		thisPtwo = new Ptwo(4);
		higherPtwo = new Ptwo(6);
	}
	
	@Test
	public void testGetValue1() {
		assertEquals(6, higherPtwo.getValue());
	}
	
	@Test 
	public void testGetNum1() {
		assertEquals(2, thisPtwo.getNum());
	}
	@Test
	public void testIsSamePattern1() {
		assertEquals(true, thisPtwo.isSamePattern(higherPtwo));
	}
	
	@Test
	public void testIsSamePattern2() {
		Pbomb thisBomb = new Pbomb(5);
		assertEquals(false, thisPtwo.isSamePattern(thisBomb));
	}
	
	@Test 
	public void testIsLarger1() {
		assertEquals(false, thisPtwo.isLarger(higherPtwo));
	}
	
	@Test
	public void testIsLarger2() {
		assertEquals(true, higherPtwo.isLarger(thisPtwo));
	}
	
	@Test
	// this case will never arise, so the code should logically return false
	public void testIsLarger3() {
		Ptwo thisPtwo = new Ptwo(2);
		assertEquals(false, thisPtwo.isLarger(thisPtwo));
	}
	
	@Test
	public void testEquals1() {
		boolean result = thisPtwo.equals(new Ptwo(4));
		assertEquals(true, result);
	}
	
	@Test
	public void testEquals2() {
		boolean result = thisPtwo.equals(new Pthree(5));
		assertEquals(false, result);
	}
	
	@Test 
	public void testEquals3() {
		boolean result = thisPtwo.equals(new Pbomb(11));
		assertEquals(false, result);
	}
}
