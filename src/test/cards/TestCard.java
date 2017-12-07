package test.cards;

import cards.Card;
import cards.ExCardNoExists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestCard {
	 @Test
	 public void testGetValueJR() throws ExCardNoExists {
		 Card card = new Card("JB");
		 assertEquals(53,card.getValue());
	 }

	 @Test
	 public void testGetValueJB() throws ExCardNoExists {
		 Card card = new Card("JR");
		 assertEquals(54,card.getValue());
	 }

	 @Test
	 public void testGetValueClub10() throws ExCardNoExists {
		 Card card = new Card("C10");
		 assertEquals(29,card.getValue());
	 }
	 
	 @Test 
	 public void testGetValueDiamond10() throws ExCardNoExists{
		 
	 }
	 
	 @Test 
	 public void testGetValueSpade10() throws ExCardNoExists {
		 
	 }
	 
	 @Test 
	 public void testGetValueHeart10() throws ExCardNoExists {
		 
	 }

	 @Test
	 public void testToString1() throws ExCardNoExists {
		 Card card = new Card("C3");
		 assertEquals("\u2663C3",card.toString());
	 }

	 @Test
	 public void test6()  {
		 try{
			 Card card = new Card("A3");
			 fail("No exception for non-existed suit");
		 } catch(ExCardNoExists e) {
			 assertEquals("Card does not exist. Suit \"A\" not exists in card", e.getMessage());
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
	 public void testToString2() throws ExCardNoExists  {
		 Card card = new Card("JB");
		 assertEquals("JB",card.toString());
	 }
 
}
