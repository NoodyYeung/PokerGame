package test;

import cards.Card;
import cards.ExCardNoExists;
import client.AIPlayer;
import client.LocalPlayer;
import gameController.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import table.ExNotEnoughPlayers;
import table.PlayerAndCards;
import table.Table;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
/**
 * Created by yeungchunyin on 11/12/2017.
 */
public class TestTable {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
//        System.setOut(null);
//        System.setErr(null);
    }

    @Test
    public void testDistributeCard1(){
        List<Player> players = new ArrayList<>();
        players.add(new LocalPlayer());
        players.add(new AIPlayer());
        players.add(new AIPlayer());

        Table table = new Table(players);
        try {
            String result = table.distributeCards(new Random(1));

            assertEquals(3, table.getPlayerCardsEntities().size());
            assertEquals(
            "[♥H2, ♠SJ, ♣C3, ♦D10, ♥H7, ♦D7, ♦D9, ♥H8, ♣C10, ♣C9, ♦D2, ♣C5, ♠SA, ♦DJ, ♠S8, ♠S4, ♦D6] [JB, ♦D4, ♠SK, ♠S7, ♣CK, ♦D5, ♣CA, ♥H5, ♣C2, ♠S9, ♠S10, ♠S2, ♥HJ, ♦DK, ♣CJ, ♥HK, ♦DQ] [♥H4, ♠S6, ♦DA, ♦D8, ♥HA, ♥H9, ♣CQ, ♣C6, ♣C4, ♥HQ, ♠SQ, ♠S5, ♣C8, ♠S3, ♣C7, ♥H6, ♥H10, ♥H3, JR, ♦D3] "
                    , result);

        } catch (ExCardNoExists exCardNoExists) {
            fail("Should not throw exception here");
            exCardNoExists.printStackTrace();
        } catch (ExNotEnoughPlayers exNotEnoughPlayers) {
            fail("There should be three players already");
            exNotEnoughPlayers.printStackTrace();
        }

    }

    @Test
    public void testDistributeCard2(){
        List<Player> players = new ArrayList<>();
        players.add(new LocalPlayer());
        players.add(new AIPlayer());

        Table table = new Table(players);
        try {
            String result = table.distributeCards(new Random(1));
            fail("Exception of not enouth players should be throw");
        } catch (ExCardNoExists exCardNoExists) {
            fail("Should not throw exception here");
            exCardNoExists.printStackTrace();
        } catch (ExNotEnoughPlayers exNotEnoughPlayers) {
            assertEquals("Not enough players at this table! There are only "+2+" players here.", exNotEnoughPlayers.getMessage());
            exNotEnoughPlayers.printStackTrace();
        }

    }


    @Test
    public void testDistributeCard3(){
        List<Player> players = null;
        Table table = new Table(players);
        try {
            String result = table.distributeCards(new Random(1));
            fail("Exception of not enouth players should be throw");
        } catch (ExCardNoExists exCardNoExists) {
            fail("Should not throw exception here");
            exCardNoExists.printStackTrace();
        } catch (ExNotEnoughPlayers exNotEnoughPlayers) {
            assertEquals("Players object is null", exNotEnoughPlayers.getMessage());
            exNotEnoughPlayers.printStackTrace();
        }
    }

    @Test
    public void testGameEnd(){
        List<Player> players = new ArrayList<>();
        players.add(new LocalPlayer());
        players.add(new AIPlayer());
        players.add(new AIPlayer());

        Table table = new Table(players);
        List<PlayerAndCards> entities = table.getPlayerCardsEntities();

        ArrayList<Card> a1 = new ArrayList<>();
        ArrayList<Card> a2 = new ArrayList<>();
        ArrayList<Card> a3 = new ArrayList<>();

        try {
            a1.add(new Card("D3"));
            a1.add(new Card("D4"));
            a1.add(new Card("D5"));
            a2.add(new Card("D6"));
        } catch (ExCardNoExists exCardNoExists) {
            fail("No exception should be throw");
            exCardNoExists.printStackTrace();
        }

        entities.add(new PlayerAndCards(players.get(0),  a1));
        entities.add(new PlayerAndCards(players.get(1),  a2));
        entities.add(new PlayerAndCards(players.get(2),  a3));

        assertEquals(true, table.checkGameEnd());

    }

    @Test
    public void testGameEnd2(){
        List<Player> players = new ArrayList<>();
        players.add(new LocalPlayer());
        players.add(new AIPlayer());
        players.add(new AIPlayer());

        Table table = new Table(players);
        List<PlayerAndCards> entities = table.getPlayerCardsEntities();

        ArrayList<Card> a1 = new ArrayList<>();
        ArrayList<Card> a2 = new ArrayList<>();
        ArrayList<Card> a3 = new ArrayList<>();

        try {
            a1.add(new Card("D3"));
            a1.add(new Card("D4"));
            a1.add(new Card("D5"));
            a2.add(new Card("D6"));
            a3.add(new Card("D7"));
        } catch (ExCardNoExists exCardNoExists) {
            fail("No exception should be throw");
            exCardNoExists.printStackTrace();
        }

        entities.add(new PlayerAndCards(players.get(0),  a1));
        entities.add(new PlayerAndCards(players.get(1),  a2));
        entities.add(new PlayerAndCards(players.get(2),  a3));

        assertEquals(false, table.checkGameEnd());

    }


    @Test
    public void testGameEnd3(){
        List<Player> players = new ArrayList<>();
        players.add(new LocalPlayer());
        players.add(new AIPlayer());
        players.add(new AIPlayer());

        Table table = new Table(players);
        List<PlayerAndCards> entities = table.getPlayerCardsEntities();

        ArrayList<Card> a1 = new ArrayList<>();
        ArrayList<Card> a2 = new ArrayList<>();
        ArrayList<Card> a3 = new ArrayList<>();

        try {
            a1.add(new Card("D3"));
        } catch (ExCardNoExists exCardNoExists) {
            fail("No exception should be throw");
            exCardNoExists.printStackTrace();
        }

        entities.add(new PlayerAndCards(players.get(0),  a1));
        entities.add(new PlayerAndCards(players.get(1),  a2));
        entities.add(new PlayerAndCards(players.get(2),  a3));

        assertEquals(true, table.checkGameEnd());
    }

    @Test
    public void testGetPlayerCardsEntities(){
        List<Player> players = new ArrayList<>();
        players.add(new LocalPlayer());
        players.add(new AIPlayer());
        players.add(new AIPlayer());

        Table table = new Table(players);
        List<PlayerAndCards> entities = table.getPlayerCardsEntities();

        ArrayList<Card> a1 = new ArrayList<>();
        ArrayList<Card> a2 = new ArrayList<>();
        ArrayList<Card> a3 = new ArrayList<>();

        try {
            a1.add(new Card("D3"));
        } catch (ExCardNoExists exCardNoExists) {
            fail("No exception should be throw");
            exCardNoExists.printStackTrace();
        }

        entities.add(new PlayerAndCards(players.get(0),  a1));
        entities.add(new PlayerAndCards(players.get(1),  a2));
        entities.add(new PlayerAndCards(players.get(2),  a3));

        assertEquals(entities, table.getPlayerCardsEntities());

    }

    @Test
    public void testSetLastHand(){
        List<Player> players = new ArrayList<>();
        players.add(new LocalPlayer());
        players.add(new AIPlayer());
        ArrayList<Card> cards= new ArrayList<>();
        try {

            for(int i = 0; i < 13; i ++){
                cards.add(new Card("D" +Card.cardType.get(i)));
            }
            for(int i = 0; i < 7 ; i ++ ){
                cards.add(new Card("S" + Card.cardType.get(i)));

            }
        } catch (ExCardNoExists exCardNoExists) {
            fail("Exception should not be thrown");
            exCardNoExists.printStackTrace();
        }
        Table table = new Table(players);
        table.setLastHand(cards);
        assertEquals(cards.toString(), table.getLastHandCard().getCards().toString() );
    }

    @Test
    public void testEmptyLastHand(){

    }

}
