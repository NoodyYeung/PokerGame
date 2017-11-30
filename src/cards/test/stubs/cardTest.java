package cards.cardsTestCase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cards.*;
import pattern.*;

public class cardTest {

	 @Test
	    public void test1() throws ExCardNoExists {
	        Card card = new Card("JB");
	        assertEquals(Integer.valueOf(14),card.getValue());
	    }
	     
	    @Test
	    public void test2() throws ExCardNoExists {
	        Card card = new Card("JR");
	        assertEquals(Integer.valueOf(15),card.getValue());
	    }
	     
	    @Test
	    public void test3() throws ExCardNoExists {
	        Card card = new Card("C10");
	        assertEquals(Integer.valueOf(8),card.getValue());
	    }
	     
	    @Test
	    public void test4() throws ExCardNoExists {
	        Card card = new Card("C3");
	        assertEquals(Integer.valueOf(1),card.getValue());
	    }
	     
	    @Test
	    public void test5() throws ExCardNoExists {
	        Card card = new Card("C3");
	        assertEquals("C3",card.toString());
	    }
	     
	    @Test
	    public void test6()  {
	        try{
	            Card card = new Card("A3");
	            fail("No exception for non-existed suit");
	        } catch(ExCardNoExists e) {
	            assertEquals("Card do not exists. Suit \"A\" not exists in card", e.getMessage());
	        }   
	    }
	     
	    @Test
	    public void test7()  {
	        try{
	            Card card = new Card("C1");
	            fail("No exception for non-existed type");
	        } catch(ExCardNoExists e) {
	            assertEquals("Card  do not exists. Type \"1\" not exists in card", e.getMessage());
	        } 
	    }
	     
	    @Test
	    public void test8() throws ExCardNoExists  {
	        Card card = new Card("JB");
	        assertEquals("JB",card.toString());
	    }
    
}

