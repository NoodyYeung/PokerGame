package test.pattern;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import pattern.*;

public class TestPbomb {
	Pbomb thisBomb;
	ArrayList<Integer> smallerListOfValues;
	ArrayList<Integer> biggerListOfValues;
	
	@Before
	public void setUp() throws Exception {
		thisBomb = new Pbomb(4);
		smallerListOfValues = new ArrayList<>();
		biggerListOfValues = new ArrayList<>();
		for(int i = 0; i<= 5; i++) {
			biggerListOfValues.add(i + 5);
			smallerListOfValues.add(i);
			biggerListOfValues.add(i + 5);
			smallerListOfValues.add(i);
		}
	}
	
	@Test
	//get Num
	public void testGetNum1() {
		int result = thisBomb.getNum();
		assertEquals(4, result);
	}
	@Test
	// PBomb x PDoubleStraight
	public void testIsSamePattern1() {
		PdoubleStraight another = new PdoubleStraight(smallerListOfValues);
		boolean result = thisBomb.isSamePattern(another);
		assertEquals(false,result);
	}
	
	@Test
	// PBomb x PDoubleStraight
	public void testIsSamePattern2() {
		Pbomb another = new Pbomb(10);
		boolean result = thisBomb.isSamePattern(another);
		assertEquals(true,result);
	}
	
	@Test
	public void isLarger1() {
		PdoubleStraight another = new PdoubleStraight(biggerListOfValues);
		boolean result = thisBomb.isLarger(another);
		assertEquals(true, result);
	}	
	
	@Test
	public void isLarger2() {
		Pbomb another = new Pbomb(3);
		boolean result = thisBomb.isLarger(another);
		assertEquals(true, result);
	}	
	
	@Test
	public void isLarger3() {
		Pbomb another = new Pbomb(5);
		boolean result = thisBomb.isLarger(another);
		assertEquals(false, result);
	}
	
	@Test
	public void isLarger4() {
		Procket another = new Procket();
		boolean result = thisBomb.isLarger(another);
		assertEquals(false, result);
	}	
	
	@Test
	public void testEquals1() {
		boolean result = thisBomb.equals(new Pbomb(4));
		assertEquals(true, result);
	}
	
	@Test
	public void testEquals2() {
		boolean result = thisBomb.equals(new Pbomb(3));
		assertEquals(false, result);
	}
	
	@Test 
	public void testEquals3() {
		boolean result = thisBomb.equals(new Pone(1));
		assertEquals(false, result);
	}
}
