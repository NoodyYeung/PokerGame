package test.cards;

import cards.Card;
import cards.ExCardNoExists;
import cards.Suit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestCard {
	 @Test
	 public void testGetValueJR() throws ExCardNoExists {
		 Card card = new Card("JR");
		 assertEquals(54,card.getValue());
	 }

	 @Test
	 public void testGetValueJB() throws ExCardNoExists {
		 Card card = new Card("JB");
		 assertEquals(53,card.getValue());
	 }

	 @Test
	 public void testGetValueClub10() throws ExCardNoExists {
		 Card card = new Card("C10");
		 assertEquals(29,card.getValue());
	 }
	 
	 @Test 
	 public void testGetValueDiamond10() throws ExCardNoExists{
		 Card card = new Card("D10");
		 assertEquals(28,card.getValue());
	 }
	 
	 @Test 
	 public void testGetValueSpade10() throws ExCardNoExists {
		 Card card = new Card("S10");
		 assertEquals(31,card.getValue());
	 }
	 
	 @Test 
	 public void testGetValueHeart10() throws ExCardNoExists {
		 Card card = new Card("H10");
		 assertEquals(30,card.getValue());
	 }

	 @Test
	 public void testToString1() throws ExCardNoExists {
		 Card card = new Card("C3");
		 assertEquals("\u2663C3",card.toString());
	 }

	 @Test
	 public void testCardValidationError1()  {
		 try{
			 Card card = new Card("A3");
			 fail("No exception for non-existent suit");
		 } catch(ExCardNoExists e) {
			 assertEquals("Card does not exist. Suit \"A\" does not exist in card", e.getMessage());
		 }   
	 }

	 @Test
	 public void testCardValidationError2()  {
		 try{
			 Card card = new Card("C1");
			 fail("No exception for non-existent type");
		 } catch(ExCardNoExists e) {
			 assertEquals("Card does not exist. Type \"1\" does not exist in card", e.getMessage());
		 } 
	 }
	 
	 @Test
	 public void testEquals1() throws ExCardNoExists {
		 Card card = new Card("C10");
		 Card another = new Card("C10");
		 assertTrue(card.equals(another));
	 }
	 
	 @Test
	 public void testEquals2() throws ExCardNoExists {
		 Card card = new Card("C10");
		 Card another = new Card("C3");
		 assertFalse(card.equals(another));
	 }
	 @Test
	 public void testEquals3() throws ExCardNoExists {
		 Card card = new Card("C10");
		 String another = "C3";
		 assertFalse(card.equals(another));
	 }
	 @Test
	 public void testToString2() throws ExCardNoExists  {
		 Card card = new Card("JB");
		 assertEquals("JB",card.toString());
	 }
	 
	 @Test
	 public void testGetInputString1() throws ExCardNoExists {
		 Card card = new Card("JB");
		 String suit = "JB";
		 String type = "";
		 String inputString = suit + type;
		 assertEquals(inputString, card.getInputString());
	 }
	 
	 @Test
	 public void testGetInputString2() throws ExCardNoExists {
		 Card card = new Card("C3");
		 String suit = "C";
		 String type = "3";
		 String inputString = suit + type;
		 assertEquals(inputString, card.getInputString());
	 }
 
}
