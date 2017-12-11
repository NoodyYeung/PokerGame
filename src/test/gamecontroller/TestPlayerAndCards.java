package test.gamecontroller;

import cards.Card;
import cards.ExCardNoExists;
import client.LocalPlayer;
import gameController.Player;
import org.junit.Assert;
import org.junit.Test;
import table.PlayerAndCards;

import java.util.ArrayList;

import static org.junit.Assert.fail;

/**
 * Created by yeungchunyin on 11/12/2017.
 */
public class TestPlayerAndCards {

    @Test
    public void testGetPlayer(){
        Player p = new LocalPlayer();
        ArrayList<Card> cards = new ArrayList<>();
        PlayerAndCards c = new PlayerAndCards(p , cards);

        Assert.assertEquals(p, c.getPlayer());
    }


    @Test
    public void testGetPlayer2(){
        Player p = null;
        ArrayList<Card> cards = new ArrayList<>();
        PlayerAndCards c = new PlayerAndCards(p , cards);

        Assert.assertEquals(null, c.getPlayer());
    }

    @Test
    public void testIsEmpty(){
        Player p = null;
        ArrayList<Card> cards = new ArrayList<>();
        PlayerAndCards c = new PlayerAndCards(p , cards);

        Assert.assertEquals(true, c.isEmpty());

    }

    @Test
    public void testIsEmpty2(){
        Player p = null;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            cards.add(new Card("D3"));
        } catch (ExCardNoExists exCardNoExists) {
            fail("No exception should be throw");
            exCardNoExists.printStackTrace();
        }
        PlayerAndCards c = new PlayerAndCards(p , cards);

        Assert.assertEquals(false, c.isEmpty());
    }

    @Test
    public void testIsLandLord(){
        Player p = null;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            for(int i = 0; i < 13; i ++){
                cards.add(new Card("D" +Card.cardType.get(i)));
            }
            for(int i = 0; i < 7 ; i ++ ){
                cards.add(new Card("S" + Card.cardType.get(i)));
            }
        } catch (ExCardNoExists exCardNoExists) {
            fail("No exception should be throw");
            exCardNoExists.printStackTrace();
        }
        PlayerAndCards c = new PlayerAndCards(p , cards);

        Assert.assertEquals(true, c.isLandLord());
    }

    @Test
    public void testIsLandLord1(){
        Player p = null;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            for(int i = 0; i < 13; i ++){
                cards.add(new Card("D" +Card.cardType.get(i)));
            }
            for(int i = 0; i < 6 ; i ++ ){
                cards.add(new Card("S" + Card.cardType.get(i)));
            }
        } catch (ExCardNoExists exCardNoExists) {
            fail("No exception should be throw");
            exCardNoExists.printStackTrace();
        }
        PlayerAndCards c = new PlayerAndCards(p , cards);

        Assert.assertEquals(false, c.isLandLord());
    }

    @Test
    public void testIsLandLord2(){
        Player p = null;
        ArrayList<Card> cards = new ArrayList<>();

        PlayerAndCards c = new PlayerAndCards(p , cards);

        Assert.assertEquals(false, c.isLandLord());
    }


    @Test
    public void testEqualsPlayer(){

        Player p = new LocalPlayer();
        ArrayList<Card> cards = new ArrayList<>();
        PlayerAndCards c = new PlayerAndCards(p , cards);

        Assert.assertEquals(true, c.equalsPlayer(p));
    }


    @Test
    public void testGetCards(){
        Player p = new LocalPlayer();
        ArrayList<Card> cards = new ArrayList<>();
        PlayerAndCards c = new PlayerAndCards(p , cards);
        Assert.assertEquals(cards,  c.getCards());
    }

}
