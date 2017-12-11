package test.table;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import client.AIPlayer;
import client.LocalPlayer;
import gameController.Player;
import gameController.TableController;
import org.junit.Test;
import table.ExNotEnoughPlayers;
import table.PlayerAndCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by yeungchunyin on 11/12/2017.
 */
public class TestTableController {

    @Test
    public void testCreateTableForGame(){
        List<Player> players = new ArrayList<>();
        Player p1 = new LocalPlayer();
        Player p2 = new AIPlayer();
        Player p3 = new AIPlayer();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        TableController controller = new TableController();
        try {
            assertEquals(
                    "[♥H2, ♠SJ, ♣C3, ♦D10, ♥H7, ♦D7, ♦D9, ♥H8, ♣C10, ♣C9, ♦D2, ♣C5, ♠SA, ♦DJ, ♠S8, ♠S4, ♦D6] [JB, ♦D4, ♠SK, ♠S7, ♣CK, ♦D5, ♣CA, ♥H5, ♣C2, ♠S9, ♠S10, ♠S2, ♥HJ, ♦DK, ♣CJ, ♥HK, ♦DQ] [♥H4, ♠S6, ♦DA, ♦D8, ♥HA, ♥H9, ♣CQ, ♣C6, ♣C4, ♥HQ, ♠SQ, ♠S5, ♣C8, ♠S3, ♣C7, ♥H6, ♥H10, ♥H3, JR, ♦D3] "
                    , controller.createTableForGame(players, new Random(1)));
        } catch (ExCardNoExists exCardNoExists) {
            fail("Exception should not be throw");
            exCardNoExists.printStackTrace();
        } catch (ExNotEnoughPlayers exNotEnoughPlayers) {
            fail("Exception should not be throw");
            exNotEnoughPlayers.printStackTrace();
        }
    }

    @Test
    public void testCheckGameEnd(){
        List<Player> players = new ArrayList<>();
        Player p1 = new LocalPlayer();
        Player p2 = new AIPlayer();
        Player p3 = new AIPlayer();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        TableController controller = new TableController();
        try {
            assertEquals(
                    "[♥H2, ♠SJ, ♣C3, ♦D10, ♥H7, ♦D7, ♦D9, ♥H8, ♣C10, ♣C9, ♦D2, ♣C5, ♠SA, ♦DJ, ♠S8, ♠S4, ♦D6] [JB, ♦D4, ♠SK, ♠S7, ♣CK, ♦D5, ♣CA, ♥H5, ♣C2, ♠S9, ♠S10, ♠S2, ♥HJ, ♦DK, ♣CJ, ♥HK, ♦DQ] [♥H4, ♠S6, ♦DA, ♦D8, ♥HA, ♥H9, ♣CQ, ♣C6, ♣C4, ♥HQ, ♠SQ, ♠S5, ♣C8, ♠S3, ♣C7, ♥H6, ♥H10, ♥H3, JR, ♦D3] "
                    , controller.createTableForGame(players, new Random(1)));
        } catch (ExCardNoExists exCardNoExists) {
            fail("Exception should not be throw");
            exCardNoExists.printStackTrace();
        } catch (ExNotEnoughPlayers exNotEnoughPlayers) {
            fail("Exception should not be throw");
            exNotEnoughPlayers.printStackTrace();
        }
        assertEquals(false, controller.checkGameEnd());
    }

    @Test
    public void getLastHandCard(){
        List<Player> players = new ArrayList<>();
        Player p1 = new LocalPlayer();
        Player p2 = new AIPlayer();
        Player p3 = new AIPlayer();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        TableController controller = new TableController();
        try {
            controller.createTableForGame(players, new Random(1));
        } catch (ExCardNoExists exCardNoExists) {
            fail("Exception should not be throw");
            exCardNoExists.printStackTrace();
        } catch (ExNotEnoughPlayers exNotEnoughPlayers) {
            fail("Exception should not be throw");
            exNotEnoughPlayers.printStackTrace();
        }
        ArrayList<Card> a1 = new ArrayList<>();

        try {
            a1.add(new Card("D3"));

            a1.add(new Card("D4"));
            a1.add(new Card("D5"));

        } catch (ExCardNoExists exCardNoExists) {
            exCardNoExists.printStackTrace();
        }
        controller.setLastHandCard(a1);

        assertEquals(a1.toString(), controller.getLastHandCard().getCards().toString());
    }

    @Test
    public void getGameWinner(){
        List<Player> players = new ArrayList<>();
        Player p1 = new LocalPlayer();
        Player p2 = new AIPlayer();
        Player p3 = new AIPlayer();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        TableController controller = new TableController();
        try {
            controller.createTableForGame(players, new Random(1));
        } catch (ExCardNoExists exCardNoExists) {
            fail("Exception should not be throw");
            exCardNoExists.printStackTrace();
        } catch (ExNotEnoughPlayers exNotEnoughPlayers) {
            fail("Exception should not be throw");
            exNotEnoughPlayers.printStackTrace();
        }
        ArrayList<Card> a1 = new ArrayList<>();

        try {
            a1.add(new Card("D3"));

            a1.add(new Card("D4"));
            a1.add(new Card("D5"));

        } catch (ExCardNoExists exCardNoExists) {
            exCardNoExists.printStackTrace();
        }

        try {
            assertEquals(true,
                    controller.reduceUserPlayedCard(p1,
                    Cards.createCardsListFromString("H2 SJ C3 D10 H7 D7 D9 H8 C10 C9 D2 C5 SA DJ S8 S4 D6")));

            assertEquals(p1, controller.getGameWinner());
        } catch (ExCardNoExists exCardNoExists) {
            fail("Exception should not be throw");
            exCardNoExists.printStackTrace();
        }
    }

    @Test
    public void testGetPlayerAndCardsObjByPlayer(){
        List<Player> players = new ArrayList<>();
        Player p1 = new LocalPlayer();
        Player p2 = new AIPlayer();
        Player p3 = new AIPlayer();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        TableController controller = new TableController();
        try {
            controller.createTableForGame(players, new Random(1));
        } catch (ExCardNoExists exCardNoExists) {
            fail("Exception should not be throw");
            exCardNoExists.printStackTrace();
        } catch (ExNotEnoughPlayers exNotEnoughPlayers) {
            fail("Exception should not be throw");
            exNotEnoughPlayers.printStackTrace();
        }
        ArrayList<Card> a1 = new ArrayList<>();

        try {
            a1.add(new Card("D3"));

            a1.add(new Card("D4"));
            a1.add(new Card("D5"));

        } catch (ExCardNoExists exCardNoExists) {
            exCardNoExists.printStackTrace();
        }

        PlayerAndCards pac = controller.getPlayerAndCardsObjByPlayer(p1);
        assertEquals("[♥H2, ♠SJ, ♣C3, ♦D10, ♥H7, ♦D7, ♦D9, ♥H8, ♣C10, ♣C9, ♦D2, ♣C5, ♠SA, ♦DJ, ♠S8, ♠S4, ♦D6]",pac.getCards().toString());
    }

    @Test
    public void testGetPlayerAndCardsObjByPlayer2(){
        List<Player> players = new ArrayList<>();
        Player p1 = new LocalPlayer();
        Player p2 = new AIPlayer();
        Player p3 = new AIPlayer();
        Player p4 = new AIPlayer();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        TableController controller = new TableController();
        try {
            controller.createTableForGame(players, new Random(1));
        } catch (ExCardNoExists exCardNoExists) {
            fail("Exception should not be throw");
            exCardNoExists.printStackTrace();
        } catch (ExNotEnoughPlayers exNotEnoughPlayers) {
            fail("Exception should not be throw");
            exNotEnoughPlayers.printStackTrace();
        }
        ArrayList<Card> a1 = new ArrayList<>();

        try {
            a1.add(new Card("D3"));

            a1.add(new Card("D4"));
            a1.add(new Card("D5"));

        } catch (ExCardNoExists exCardNoExists) {
            exCardNoExists.printStackTrace();
        }

        PlayerAndCards pac = controller.getPlayerAndCardsObjByPlayer(p4);
        assertEquals( null, pac);
    }


}
