package test.ddz;
import DDZ.DDZ;
import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pattern.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestDDZ {
	@Before
	public void setup() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	// precondition : cards in hand are sorted
	public void allSame1() throws ExCardNoExists{
		ArrayList<Card> cards=new ArrayList<>();
		DDZ ddz=new DDZ();
		cards.add(new Card("C3"));
		cards.add(new Card("S3"));
		cards.add(new Card("D3"));
		cards.add(new Card("H3"));
		boolean result=ddz.allSame(cards, cards.size());
		assertEquals(true,result);
	}
	@Test
	// precondition : cards in hand are sorted
	public void allSame2() throws ExCardNoExists{
		ArrayList<Card> cards=new ArrayList<>();
		DDZ ddz=new DDZ();
		cards.add(new Card("C3"));
		cards.add(new Card("S4"));
		cards.add(new Card("D3"));
		cards.add(new Card("H3"));
		boolean result=ddz.allSame(cards, cards.size());
		assertEquals(false,result);
	}
	@Test
	public void isStraight1() throws ExCardNoExists{
		ArrayList<Integer> cards=new ArrayList<>();
		DDZ ddz=new DDZ();
		cards.add(new Integer(3));
		cards.add(new Integer(4));
		cards.add(new Integer(5));
		cards.add(new Integer(6));
		cards.add(new Integer(7));
		boolean result=ddz.isStraight(cards);
		assertEquals(true,result);
	}
	@Test
	public void isStraight2() throws ExCardNoExists{
		ArrayList<Integer> cards=new ArrayList<>();
		DDZ ddz=new DDZ();
		cards.add(new Integer(1));
		cards.add(new Integer(4));
		cards.add(new Integer(5));
		cards.add(new Integer(6));
		boolean result=ddz.isStraight(cards);
		assertEquals(false,result);
	}
	
	@Test
	public void testHashAllCards() throws ExCardNoExists{
		ArrayList<Card> cards=new ArrayList<>();
		DDZ ddz=new DDZ();
		cards.add(new Card("C3"));
		cards.add(new Card("D4"));
		cards.add(new Card("H4"));
		cards.add(new Card("H6"));
		cards.add(new Card("SK"));
		cards.add(new Card("CJ"));
		HashMap<Integer,Integer> testResult=ddz.hashAllCards(cards);
		HashMap<Integer,Integer> result=new HashMap<>();
		result.put(new Integer(1), new Integer(1));
		result.put(new Integer(2), new Integer(2));
		result.put(new Integer(4), new Integer(1));
		result.put(new Integer(11), new Integer(1));
		result.put(new Integer(9), new Integer(1));
		assertEquals(result,testResult);
		
	}
	@Test
	public void hashAllCards2() throws ExCardNoExists{
		ArrayList<Card> cards=null;
		DDZ ddz=new DDZ();
		HashMap<Integer,Integer> testResult=ddz.hashAllCards(cards);
		assertEquals(null,testResult);
		
	}
	@Test
	public void allDifferent1() throws ExCardNoExists{
		HashMap<Integer,Integer> cards=new HashMap<>();
		DDZ ddz=new DDZ();
		cards.put(new Integer(1), new Integer(1));
		cards.put(new Integer(2), new Integer(2));
		cards.put(new Integer(4), new Integer(1));
		cards.put(new Integer(11), new Integer(1));
		cards.put(new Integer(9), new Integer(1));
		boolean result=ddz.allDifferent(cards);
		assertEquals(false,result);
	}
	@Test
	public void allDifferent2() throws ExCardNoExists{
		HashMap<Integer,Integer> cards=new HashMap<>();
		DDZ ddz=new DDZ();
		cards.put(new Integer(1), new Integer(1));
		cards.put(new Integer(4), new Integer(1));
		cards.put(new Integer(11), new Integer(1));
		cards.put(new Integer(9), new Integer(1));
		boolean result=ddz.allDifferent(cards);
		assertEquals(true,result);
	}
	@Test
	public void numOfN1(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		DDZ ddz=new DDZ();
		cards.put(new Integer(1), new Integer(1));
		cards.put(new Integer(4), new Integer(1));		
		cards.put(new Integer(9), new Integer(1));
		cards.put(new Integer(11), new Integer(1));
		ArrayList<Integer> result=new ArrayList<>();
		result.add(new Integer(1));
		result.add(new Integer(4));
		result.add(new Integer(9));
		result.add(new Integer(11));
		ArrayList<Integer> testResult=ddz.numOfN(cards,1);
		assertEquals(result,testResult);		
	}
	@Test
	public void numOfN2(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		DDZ ddz=new DDZ();
		cards.put(new Integer(1), new Integer(1));
		cards.put(new Integer(4), new Integer(2));
		cards.put(new Integer(9), new Integer(1));
		cards.put(new Integer(11), new Integer(1));
		ArrayList<Integer> result=new ArrayList<>();
		result.add(new Integer(1));
		result.add(new Integer(9));
		result.add(new Integer(11));
		ArrayList<Integer> testResult=ddz.numOfN(cards,1);
		assertEquals(result,testResult);		
	}
	@Test
	public void isTripleWithOne1(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(1),new Integer(3));
		cards.put(new Integer(2),new Integer(3));
		cards.put(new Integer(3),new Integer(3));
		DDZ ddz=new DDZ();
		int numOfAll=12;
		int testResult=ddz.isTripleWithOne(cards, numOfAll);
		assertEquals(3,testResult);	
	}
	@Test
	public void isTripleWithOne2(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(1),new Integer(3));
		cards.put(new Integer(3),new Integer(3));
		DDZ ddz=new DDZ();
		int numOfAll=9;
		int testResult=ddz.isTripleWithOne(cards, numOfAll);
		assertEquals(-1,testResult);	
	}
	@Test
	public void isTripleWithOne3(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(1),new Integer(3));
		cards.put(new Integer(2),new Integer(3));
		cards.put(new Integer(3),new Integer(3));
		DDZ ddz=new DDZ();
		int numOfAll=8;
		int testResult=ddz.isTripleWithOne(cards, numOfAll);
		assertEquals(-1,testResult);	
	}
	@Test
	public void isFullHouse1(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(1),new Integer(3));
		cards.put(new Integer(2),new Integer(3));
		cards.put(new Integer(3),new Integer(3));
		DDZ ddz=new DDZ();
		int numOfAll=15;
		int testResult=ddz.isFullHouse(cards, numOfAll);
		assertEquals(3,testResult);	
	}
	@Test
	public void isFullHouse2(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(1),new Integer(3));
		cards.put(new Integer(3),new Integer(3));
		DDZ ddz=new DDZ();
		int numOfAll=15;
		int testResult=ddz.isFullHouse(cards, numOfAll);
		assertEquals(-1,testResult);	
	}
	@Test
	public void isFullHouse3(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(1),new Integer(3));
		cards.put(new Integer(2),new Integer(3));
		cards.put(new Integer(3),new Integer(3));
		DDZ ddz=new DDZ();
		int numOfAll=14;
		int testResult=ddz.isFullHouse(cards, numOfAll);
		assertEquals(-1,testResult);	
	}
	@Test
	public void isDoubleStraight1(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(2));
		cards.put(new Integer(3), new Integer(2));
		cards.put(new Integer(4), new Integer(2));
		DDZ ddz=new DDZ();
		int testResult=ddz.isDoubleStraight(cards);
		assertEquals(3,testResult);
	}
	@Test
	public void isDoubleStraight2(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(2));
		cards.put(new Integer(3), new Integer(3));
		cards.put(new Integer(4), new Integer(2));
		DDZ ddz=new DDZ();
		int testResult=ddz.isDoubleStraight(cards);
		assertEquals(-1,testResult);
	}
	@Test
	public void isDoubleStraight3(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(2));
		cards.put(new Integer(3), new Integer(2));
		cards.put(new Integer(5), new Integer(2));
		DDZ ddz=new DDZ();
		int testResult=ddz.isDoubleStraight(cards);
		assertEquals(-1,testResult);
	}
	@Test
	public void isTripleStraight1(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(3));
		cards.put(new Integer(3), new Integer(3));
		cards.put(new Integer(5), new Integer(3));
		DDZ ddz=new DDZ();
		int testResult=ddz.isTripleStraight(cards);
		assertEquals(-1,testResult);
	}
	@Test
	public void isTripleStraight2(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(3));
		cards.put(new Integer(3), new Integer(3));
		cards.put(new Integer(4), new Integer(3));
		DDZ ddz=new DDZ();
		int testResult=ddz.isTripleStraight(cards);
		assertEquals(3,testResult);
	}
	@Test
	public void isTripleStraight3(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(2));
		cards.put(new Integer(3), new Integer(3));
		cards.put(new Integer(5), new Integer(3));
		DDZ ddz=new DDZ();
		int testResult=ddz.isTripleStraight(cards);
		assertEquals(-1,testResult);
	}
	@Test
	public void isBomb1() throws ExCardNoExists{
		List<Card> cards=null;
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isBomb(cards);
		assertEquals(false,testResult);
	}
	@Test
	public void isBomb2() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		DDZ ddz=new DDZ();
		cards.add(new Card("C4"));
		boolean testResult=ddz.isBomb(cards);
		assertEquals(false,testResult);
	}
	@Test
	public void isBomb3() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("D4"));
		cards.add(new Card("C5"));
		cards.add(new Card("H4"));
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isBomb(cards);
		assertEquals(false,testResult);
	}
	@Test
	public void isBomb4() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("S4"));
		cards.add(new Card("D4"));
		cards.add(new Card("H4"));
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isBomb(cards);
		assertEquals(true,testResult);
	}
	@Test
	public void isRocket1() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isRocket(cards);
		assertEquals(false,testResult);
	}
	@Test
	public void isRocket2() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("JB"));
		cards.add(new Card("JR"));
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isRocket(cards);
		assertEquals(true,testResult);
	}
	@Test
	public void isRocket3() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("JR"));
		cards.add(new Card("JB"));
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isRocket(cards);
		assertEquals(true,testResult);
	}
	@Test
	public void isRocket4() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("JB"));
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isRocket(cards);
		assertEquals(false,testResult);
	}
	@Test
	public void isRocket5() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("JB"));
		cards.add(new Card("C4"));
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isRocket(cards);
		assertEquals(false,testResult);
	}
	@Test
	public void isRocket6() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("JR"));
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isRocket(cards);
		assertEquals(false,testResult);
	}
	@Test
	public void isRocket7() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("JR"));
		cards.add(new Card("C4"));
		DDZ ddz=new DDZ();
		boolean testResult=ddz.isRocket(cards);
		assertEquals(false,testResult);
	}
	@Test
	public void isMultipleBomb1(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(4));
		cards.put(new Integer(3), new Integer(4));
		cards.put(new Integer(4), new Integer(4));
		DDZ ddz=new DDZ();
		int testResult=ddz.isMultipleBomb(cards);
		assertEquals(3,testResult);
	}
	@Test
	public void isMultipleBomb2(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(4));
		cards.put(new Integer(3), new Integer(3));
		cards.put(new Integer(4), new Integer(4));
		DDZ ddz=new DDZ();
		int testResult=ddz.isMultipleBomb(cards);
		assertEquals(-1,testResult);
	}
	
	@Test
	public void identifyPatternIsNotMultipleBomb()  throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("D4"));
		cards.add(new Card("S4"));
		cards.add(new Card("H4"));
		cards.add(new Card("C5"));
		cards.add(new Card("D5"));
		cards.add(new Card("S5"));
		cards.add(new Card("H7"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		assertNull(testResult);
	}
	
	@Test
	public void isMultipleBomb3(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(4));
		cards.put(new Integer(3), new Integer(4));
		cards.put(new Integer(5), new Integer(4));
		DDZ ddz=new DDZ();
		int testResult=ddz.isMultipleBomb(cards);
		assertEquals(-1,testResult);
	}
	@Test
	public void isMultipleBomb4(){
		HashMap<Integer,Integer> cards=new HashMap<>();
		cards.put(new Integer(2), new Integer(4));
		DDZ ddz=new DDZ();
		int testResult=ddz.isMultipleBomb(cards);
		assertEquals(-1,testResult);
	}
	@Test
	public void identifyPattern1() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		Pattern result=new Pone(1);
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern2() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D4"));
		cards.add(new Card("C8"));
		cards.add(new Card("C9"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		Pattern result=null;
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern3() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D4"));
		cards.add(new Card("C8"));
		cards.add(new Card("C9"));
		cards.add(new Card("C10"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		Pattern result=null;
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern4() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("S3"));
		cards.add(new Card("H3"));
		DDZ ddz=new DDZ();
		Pattern testResult = ddz.identifyPattern(cards);
//		System.out.println("bomb"+testResult.getValue());
		Pattern result=new Pbomb(1);
//		System.out.println("aaabomb"+result.getValue());
		assertEquals(new Pbomb(1),testResult);
	}
	@Test
	public void identifyPattern5() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("S3"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		Pattern result=new Pthree(1);
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern6() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		Pattern result=new Ptwo(1);
		assertEquals(result.getValue(),testResult.getValue());
	}
	@Test
	public void identifyPattern7() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("JB"));
		cards.add(new Card("JR"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		Pattern result=new Procket();
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern8() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("JB"));
		cards.add(new Card("JR"));
		cards.add(new Card("C4"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		Pattern result=null;
		assertEquals(null,testResult);
	}
	@Test
	public void identifyPattern9() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("D4"));
		cards.add(new Card("H4"));
		cards.add(new Card("H5"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		ArrayList<Integer> temp=new ArrayList<>();
		temp.add(new Integer(2));
		Pattern result=new PtriplePlusOne(temp,1);
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern10() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("D4"));
		cards.add(new Card("H4"));
		cards.add(new Card("H5"));
		cards.add(new Card("S5"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		ArrayList<Integer> temp=new ArrayList<>();
		temp.add(new Integer(2));
		Pattern result=new PfullHouse(temp,1);
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern11() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("D4"));
		cards.add(new Card("C5"));
		cards.add(new Card("D5"));
		cards.add(new Card("C6"));
		cards.add(new Card("D6"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		ArrayList<Integer> listOfValues =new ArrayList<>();
		listOfValues.add(new Integer(2));
		listOfValues.add(new Integer(3));
		listOfValues.add(new Integer(4));
		Pattern result=new PdoubleStraight(listOfValues);
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern12() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("D4"));
		cards.add(new Card("S4"));
		cards.add(new Card("C5"));
		cards.add(new Card("D5"));
		cards.add(new Card("S5"));
		cards.add(new Card("C6"));
		cards.add(new Card("D6"));
		cards.add(new Card("S6"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		ArrayList<Integer> listOfValues =new ArrayList<>();
		listOfValues.add(new Integer(2));
		listOfValues.add(new Integer(3));
		listOfValues.add(new Integer(4));
		Pattern result=new PtripleStraight(listOfValues);
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern13() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C4"));
		cards.add(new Card("D4"));
		cards.add(new Card("S4"));
		cards.add(new Card("H4"));
		cards.add(new Card("C5"));
		cards.add(new Card("D5"));
		cards.add(new Card("S5"));
		cards.add(new Card("H5"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		ArrayList<Integer> listOfValues =new ArrayList<>();
		listOfValues.add(new Integer(2));
		listOfValues.add(new Integer(3));
		Pattern result=new PmultiBomb(listOfValues);
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern14() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D4"));
		cards.add(new Card("C5"));
		cards.add(new Card("C6"));
		cards.add(new Card("C7"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		ArrayList<Integer> listOfStraight=new ArrayList<>();
		listOfStraight.add(new Integer(1));
		listOfStraight.add(new Integer(2));
		listOfStraight.add(new Integer(3));
		listOfStraight.add(new Integer(4));
		listOfStraight.add(new Integer(5));
		Pattern result=new Pstraight(listOfStraight);
		assertEquals(result,testResult);
	}
	@Test
	public void identifyPattern15() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D2"));
		cards.add(new Card("C5"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		assertEquals(null,testResult);
	}
	@Test
	public void identifyPattern16() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D4"));
		cards.add(new Card("C5"));
		cards.add(new Card("C6"));
		cards.add(new Card("C8"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.identifyPattern(cards);
		ArrayList<Integer> listOfStraight=new ArrayList<>();
		listOfStraight.add(new Integer(1));
		listOfStraight.add(new Integer(2));
		listOfStraight.add(new Integer(3));
		listOfStraight.add(new Integer(4));
		listOfStraight.add(new Integer(5));
		Pattern result=new Pstraight(listOfStraight);
		assertNull(testResult);
	}
	@Test
	public void validateDDZ1() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("C4"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(null,testResult);		
	}
	@Test
	public void validateDDZ2() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		Cards lastHand=null;
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("C4"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(null,testResult);		
	}
	@Test
	public void validateDDZ3() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("C4"));
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(null,testResult);		
	}
	@Test
	public void validateDDZ4() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("C4"));	
		lastHandCards.add(new Card("C3"));
		lastHandCards.add(new Card("D3"));
		lastHandCards.add(new Card("C4"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(null,testResult);		
	}
	@Test
	public void validateDDZ5() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("S3"));
		cards.add(new Card("C4"));
		lastHandCards.add(new Card("C5"));
		lastHandCards.add(new Card("D5"));
		lastHandCards.add(new Card("S5"));
		lastHandCards.add(new Card("C4"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(null,testResult);		
	}
	@Test
	public void validateDDZ6() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		cards.add(new Card("C5"));
		cards.add(new Card("D5"));
		cards.add(new Card("S5"));
		cards.add(new Card("C4"));
		lastHandCards.add(new Card("C3"));
		lastHandCards.add(new Card("D3"));
		lastHandCards.add(new Card("S3"));
		lastHandCards.add(new Card("C7"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		ArrayList<Integer> temp=new ArrayList<>();
		temp.add(new Integer(3));
		Pattern result=new PtriplePlusOne(temp,1);
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(result,testResult);		
	}
	@Test
	public void validateDDZ7() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		lastHandCards.add(new Card("C5"));
		lastHandCards.add(new Card("D5"));
		lastHandCards.add(new Card("S5"));
		lastHandCards.add(new Card("C4"));
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("S3"));
		cards.add(new Card("H3"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		Pattern result=new Pbomb(1);
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(result,testResult);		
	}
	@Test
	public void validateDDZ8() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		cards.add(new Card("C5"));
		cards.add(new Card("D5"));
		cards.add(new Card("S5"));
		cards.add(new Card("C4"));
		lastHandCards.add(new Card("C3"));
		lastHandCards.add(new Card("D3"));
		lastHandCards.add(new Card("S3"));
		lastHandCards.add(new Card("H8"));
		lastHandCards.add(new Card("D8"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(null,testResult);		
	}
	@Test
	public void validateDDZ9() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		lastHandCards.add(new Card("C5"));
		lastHandCards.add(new Card("D5"));
		lastHandCards.add(new Card("S5"));
		lastHandCards.add(new Card("C4"));
		cards.add(new Card("JR"));
		cards.add(new Card("JB"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		Pattern result=new Procket();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(result,testResult);		
	}
	@Test
	public void validateDDZ10() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		lastHandCards.add(new Card("JR"));
		lastHandCards.add(new Card("JB"));
		cards.add(new Card("C3"));
		cards.add(new Card("S3"));
		cards.add(new Card("H3"));
		cards.add(new Card("D3"));
		cards.add(new Card("C4"));
		cards.add(new Card("S4"));
		cards.add(new Card("H4"));
		cards.add(new Card("D4"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(null,testResult);		
	}
	@Test
	public void validateDDZ11() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		lastHandCards.add(new Card("C8"));
		lastHandCards.add(new Card("S8"));
		cards.add(new Card("C3"));
		cards.add(new Card("S3"));
		cards.add(new Card("H3"));
		cards.add(new Card("D3"));
		cards.add(new Card("C4"));
		cards.add(new Card("S4"));
		cards.add(new Card("H4"));
		cards.add(new Card("D4"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		ArrayList<Integer> temp=new ArrayList<>();
		temp.add(new Integer(1));
		temp.add(new Integer(2));
		Pattern result=new PmultiBomb(temp);
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(result,testResult);		
	}
	
	@Test
	public void testAllSame1() throws ExCardNoExists {
		ArrayList<Card> lastHandCards = new ArrayList<>();
		lastHandCards.add(new Card("C8"));
		lastHandCards.add(new Card("S8"));
		DDZ ddz = new DDZ();
		assertEquals(true, ddz.allSame(lastHandCards, 2) );
	}
	@Test
	public void validateDDZ12() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=null;
		Pattern lastPattern=new Pone(1);
		cards.add(new Card("C3"));

		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);

		assertNull(testResult);
	}
	@Test
	public void validateDDZ13() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		lastHandCards.add(new Card("JR"));
		lastHandCards.add(new Card("JB"));
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("S3"));
		cards.add(new Card("H3"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(null,testResult);		
	}
	@Test
	public void validateDDZ14() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		ArrayList<Card> lastHandCards=new ArrayList<>();
		Pattern lastPattern=new Pone(1);
		lastHandCards.add(new Card("C4"));
		lastHandCards.add(new Card("D4"));
		lastHandCards.add(new Card("S4"));
		lastHandCards.add(new Card("H4"));
		lastHandCards.add(new Card("C5"));
		lastHandCards.add(new Card("D5"));
		lastHandCards.add(new Card("S5"));
		lastHandCards.add(new Card("H5"));
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("S3"));
		cards.add(new Card("H3"));
		Cards lastHand=new Cards(lastHandCards,lastPattern);
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		assertEquals(null,testResult);		
	}
	@Test
	public void validateDDZ15() throws ExCardNoExists{
		List<Card> cards=new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D3"));
		cards.add(new Card("S3"));
		cards.add(new Card("H3"));
		Cards lastHand=null;
		DDZ ddz=new DDZ();
		Pattern testResult=ddz.validateDDZ(cards,lastHand);
		Pattern result=new Pbomb(1);
		assertEquals(result,testResult);		
	}
	
	@Test
	public void allDifferentLessThan5NotRocketCheckIsNull() throws ExCardNoExists {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card("C3"));
		cards.add(new Card("D2"));
		Cards lastHand = null;
		DDZ ddz = new DDZ();
		assertNull(ddz.validateDDZ(cards, lastHand));
	}
	
}