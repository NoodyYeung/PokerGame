package test.cards;

import cards.Card;
import cards.ExCardNoExists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestCard {
	 @Test
	    public void test1() throws ExCardNoExists {
	        Card card = new Card("JB");
	        assertEquals(53,card.getValue());
	    }
	     
	    @Test
	    public void test2() throws ExCardNoExists {
	        Card card = new Card("JR");
	        assertEquals(54,card.getValue());
	    }
	     
	    @Test
	    public void test3() throws ExCardNoExists {
	        Card card = new Card("C10");
	        assertEquals(29,card.getValue());
	    }
	     
	    @Test
	    public void test4() throws ExCardNoExists {
	        Card card = new Card("C3");
	        assertEquals(1,card.getValue());
	    }
	     
	    @Test
	    public void test5() throws ExCardNoExists {
	        Card card = new Card("C3");
	        assertEquals("\u2663C3",card.toString());
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
	    

		 @Test
		 public void test9() throws ExCardNoExists {
			 Card card = new Card("JB");
			 assertEquals(53,card.getValue());
		 }

		 @Test
		 public void test10() throws ExCardNoExists {
			 Card card = new Card("JR");
			 assertEquals(54,card.getValue());
		 }

		 @Test
		 public void test11() throws ExCardNoExists {
			 Card card = new Card("C10");
			 assertEquals(29,card.getValue());
		 }

		 @Test
		 public void test12() throws ExCardNoExists {
			 Card card = new Card("C3");
			 assertEquals(1,card.getValue());
		 }

		 @Test
		 public void test13() throws ExCardNoExists {
			 Card card = new Card("C3");
			 assertEquals("\u2663C3",card.toString());
		 }

		 @Test
		 public void test14()  {
			 try{
				 Card card = new Card("A3");
				 fail("No exception for non-existed suit");
			 } catch(ExCardNoExists e) {
				 assertEquals("Card do not exists. Suit \"A\" not exists in card", e.getMessage());
			 }   
		 }

		 @Test
		 public void test15()  {
			 try{
				 Card card = new Card("C1");
				 fail("No exception for non-existed type");
			 } catch(ExCardNoExists e) {
				 assertEquals("Card  do not exists. Type \"1\" not exists in card", e.getMessage());
			 } 
		 }

		 @Test
		 public void test16() throws ExCardNoExists  {
			 Card card = new Card("JB");
			 assertEquals("JB",card.toString());
		 }
 
}
