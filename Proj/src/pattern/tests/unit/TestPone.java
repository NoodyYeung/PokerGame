package pattern.tests.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import pattern.*;
import pattern.tests.stubs.*;

public class TestPone {

	Pone thisPone;
	@Before
	public void setUp() throws Exception {

		thisPone = new Pone(4);
	}
	
	@Test
	public void testIsSamePattern1() {
		PbombTestStubA another = new PbombTestStubA(4);
		boolean result = thisPone.isSamePattern(another);
		assertEquals(false, result);
	}
	
	@Test
	public void testIsSamePattern2() {
		PoneTestStubA another = new PoneTestStubA(1);
		boolean result = thisPone.isSamePattern(another);
		assertEquals(true, result);
	}
	
	@Test
	public void testIsLarger1() {
		PoneTestStubA another = new PoneTestStubA(1);
		boolean result = thisPone.isLarger(another);
		assertEquals(true, result);
	}
	
	@Test
	public void testIsLarger2() {
		PbombTestStubA another = new PbombTestStubA(3);
		boolean result = thisPone.isLarger(another);
		assertEquals(false, result);
	}
}
