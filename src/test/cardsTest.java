package test;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import org.junit.Test;
import pattern.Pattern;
import pattern.Pbomb;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class cardsTest {

	@Test
    public void test1() throws ExCardNoExists {
        String rawString = "C3 S5 D4 H3";
        ArrayList<Card> cards = Cards.createCardsListFromString(rawString);
         
        Card card1 = new Card("C3");
        Card card2 = new Card("H3");
        Card card3 = new Card("D4");
        Card card4 = new Card("S5");
        ArrayList<Card> expectCards =new ArrayList<Card>();
        expectCards.add(card1);
        expectCards.add(card2);
        expectCards.add(card3);
        expectCards.add(card4);
        Boolean result = (cards.get(0).equals(card1) && cards.get(1).equals(card2) && cards.get(2).equals(card3) && cards.get(3).equals(card4));
         
        assertEquals(true,result);
    }
 
    @Test
    public void test2() throws ExCardNoExists {
        Card card1 = new Card("C3");
        Card card2 = new Card("H3");
        Card card3 = new Card("D4");
        Card card4 = new Card("S5");
        ArrayList<Card> expectCards =new ArrayList<Card>();
        expectCards.add(card1);
        expectCards.add(card2);
        expectCards.add(card3);
        expectCards.add(card4);
        String expect ="C3 H3 D4 S5 ";
        assertEquals(expect,Cards.toString(expectCards));
 
    }
 

 
    @Test
    public void test3() throws ExCardNoExists {
        Card card1 = new Card("C3");
        Card card2 = new Card("H3");
        Card card3 = new Card("D3");
        Card card4 = new Card("S3");
        ArrayList<Card> cardlist =new ArrayList<Card>();
        cardlist.add(card1);
        cardlist.add(card2);
        cardlist.add(card3);
        cardlist.add(card4);
        Pattern p = new Pbomb(3);
        Cards cards= new Cards(cardlist,p);
 
        assertEquals(true,cards.getPattern().equals(p));
    }
 
    @Test
    public void test5() throws ExCardNoExists {
        Card card1 = new Card("C3");
        Card card2 = new Card("H3");
        Card card3 = new Card("D3");
        Card card4 = new Card("S3");
        ArrayList<Card> cardlist =new ArrayList<Card>();
        cardlist.add(card1);
        cardlist.add(card2);
        cardlist.add(card3);
        cardlist.add(card4);
        Pattern p = new Pbomb(3);
        Cards cards= new Cards(cardlist,p);
 
        assertEquals(cardlist,cards.getCards());
    }

}
