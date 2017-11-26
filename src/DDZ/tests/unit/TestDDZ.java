package DDZ.tests.unit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DDZ.*;
import cards.Card;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestDDZ {
	@Before
	public void setup() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	// precondition : cards in hand are sorted
	public void testAllSame1() {
		DDZ thisDDZ = new DDZ();
		ArrayList<Card> thisHand = new ArrayList<>();
		for(int i = 0 ; i< 5; i++) {
			thisHand.add(new Card(i+1));
		}
		boolean result = thisDDZ.allSame(thisHand, 5);
		assertEquals(false, result);
		
	}
}
