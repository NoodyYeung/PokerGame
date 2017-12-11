package test;

import cards.Card;
import cards.Cards;
import client.LocalPlayer;
import client.Main;
import gameController.Player;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TestPlayer {

    @Test
    public void testLocalPlayerId() {
        Player player = new LocalPlayer();
        int id = player.getId();
        Assert.assertEquals(Player.playerIdForLocalAndAI - 1, id);
    }

    @Test
    public void testLocalPlayerName() {
        Player player = new LocalPlayer();
        player.setName("player1");
        String name = player.getName();
        Assert.assertEquals("player1", name);
    }

    @Test
    public void testLocalPlayerNameToString() {
        Player player = new LocalPlayer();
        player.setName("player1");
        String toString = player.toString();
        Assert.assertEquals("player1", toString);
    }

    @Test
    public void testLocalPlayerLose() throws Exception {
        Player player = new LocalPlayer();
        setOutput();
        player.youLose();
        Assert.assertEquals("You lose\n", getOutput());
    }

    @Test
    public void testLocalPlayerWin() throws Exception {
        Player player = new LocalPlayer();
        setOutput();
        player.youWin();
        Assert.assertEquals("You win\n", getOutput());
    }

    @Test
    public void testLocalPlayerLandLoad() throws Exception {
        Player player = new LocalPlayer();
        setOutput();
        player.youAreLandLord();
        Assert.assertEquals("You are landLoad ! \n ", getOutput());
    }

    @Test
    public void testLocalPlayerFarmer() throws Exception {
        Player player = new LocalPlayer();
        Player teammates = new LocalPlayer();
        teammates.setName("teammate");
        setOutput();
        player.youAreFarmer(teammates);
        Assert.assertEquals(String.format("You are farmer. Your teammate is Player %d -- Name %s \n", teammates.getId(), "teammate"), getOutput());
    }

    @Test
    public void testLocalPlayerYouTurnToPlayCard() throws Exception {


        Player player = new LocalPlayer();
        String input = "" + System.lineSeparator()
                + "DA" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
        Main.systemIn = new Scanner(in);

        setOutput();
        Cards cards = null;
        ArrayList<Card> c = new ArrayList<>();
        c.add(new Card("DA"));
        player.yourTurnToPlayCard(cards, c);

        Assert.assertEquals(
                "No last player cards. You are free to put cards on the table\n" +
                "Card in your hand : ♦DA \n" +
                "Please put the cards: (Example: \"D1 S1\")\n" +
                "Please put the cards: (Example: \"D1 S1\")\n", getOutput());

        System.setIn(System.in);

    }


    @Test
    public void testLocalPlayerYouTurnToPlayCard2() throws Exception {


        Player player = new LocalPlayer();
        String input = "" + System.lineSeparator()
                + "D1" + System.lineSeparator() +
                "DA" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
        Main.systemIn = new Scanner(in);

        setOutput();
        Cards cards = null;
        ArrayList<Card> c = new ArrayList<>();
        c.add(new Card("DA"));
        player.yourTurnToPlayCard(cards, c);

        Assert.assertEquals(
                "No last player cards. You are free to put cards on the table\n" +
                        "Card in your hand : ♦DA \n" +
                        "Please put the cards: (Example: \"D1 S1\")\n" +
                        "Please put the cards: (Example: \"D1 S1\")\n" +
                "Invalid Cards Combination\n"+
                        "Please put the cards: (Example: \"D1 S1\")\n" , getOutput());

        System.setIn(System.in);
    }

    @Test
    public void testLocalPlayerYouTurnToPlayCard3() throws Exception {


        Player player = new LocalPlayer();
        String input = "" + System.lineSeparator()
                + "D1" + System.lineSeparator() +
                "DA" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
        Main.systemIn = new Scanner(in);

        setOutput();
        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card("SJ"));
        Cards cards = new Cards(c1);

        ArrayList<Card> c = new ArrayList<>();
        c.add(new Card("DA"));
        player.yourTurnToPlayCard(cards, c);

        Assert.assertEquals(
                "Last player cards : ♠SJ \n" +
                        "Card in your hand : ♦DA \n" +
                        "Please put the cards: (Example: \"D1 S1\")\n" +
                        "Please put the cards: (Example: \"D1 S1\")\n" +
                        "Invalid Cards Combination\n"+
                        "Please put the cards: (Example: \"D1 S1\")\n" , getOutput());

        System.setIn(System.in);
    }


    @Test
    public void testYourTurnToPlayCardOrSkipCard() throws Exception {


        Player player = new LocalPlayer();
        String input = "" + System.lineSeparator()
                + "D1" + System.lineSeparator() +
                "DA" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
        Main.systemIn = new Scanner(in);

        setOutput();
        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card("SJ"));
        Cards cards = new Cards(c1);

        ArrayList<Card> c = new ArrayList<>();
        c.add(new Card("DA"));
        player.yourTurnToPlayCardOrSkipCard( c, cards);

        Assert.assertEquals(
                "Last player cards : ♠SJ \n" +
                        "Card in your hand : ♦DA \n" +
                        "Please put the cards: (Example: \"D1 S1\") OR input SKIP to skip:\n" +
                        "Please put the cards: (Example: \"D1 S1\") OR input SKIP to skip:\n" +
                        "Invalid Cards Combination\n"+
                        "Please put the cards: (Example: \"D1 S1\") OR input SKIP to skip:\n" , getOutput());

        System.setIn(System.in);
    }


    @Test
    public void testYourTurnToPlayCardOrSkipCard2() throws Exception {


        Player player = new LocalPlayer();
        String input = "" + System.lineSeparator()
                + "D1" + System.lineSeparator() +
                "DA" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
        Main.systemIn = new Scanner(in);

        setOutput();

        Cards cards = null;

        ArrayList<Card> c = new ArrayList<>();
        c.add(new Card("DA"));
        player.yourTurnToPlayCardOrSkipCard( c, cards);

        Assert.assertEquals(
                "No last player cards. You are free to put cards on the table\n" +
                        "Card in your hand : ♦DA \n" +
                        "Please put the cards: (Example: \"D1 S1\") OR input SKIP to skip:\n" +
                        "Please put the cards: (Example: \"D1 S1\") OR input SKIP to skip:\n" +
                        "Invalid Cards Combination\n"+
                        "Please put the cards: (Example: \"D1 S1\") OR input SKIP to skip:\n" , getOutput());

        System.setIn(System.in);
    }


    @Test
    public void testYourTurnToPlayCardOrSkipCard3() throws Exception {


        Player player = new LocalPlayer();
        String input = "" + System.lineSeparator()
                + "SKIP" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
        Main.systemIn = new Scanner(in);

        setOutput();

        Cards cards = null;

        ArrayList<Card> c = new ArrayList<>();
        c.add(new Card("DA"));
        player.yourTurnToPlayCardOrSkipCard( c, cards);

        Assert.assertEquals(
                "No last player cards. You are free to put cards on the table\n" +
                        "Card in your hand : ♦DA \n" +
                        "Please put the cards: (Example: \"D1 S1\") OR input SKIP to skip:\n" +
                        "Please put the cards: (Example: \"D1 S1\") OR input SKIP to skip:\n"
                         , getOutput());

        System.setIn(System.in);
    }


    @Test
    public void testLocalPlayerWaitPlayerToPlayerCard() throws Exception {
        Player player = new LocalPlayer();
        Player player2 = new LocalPlayer();
        player2.setName("player2");
        setOutput();
        player.waitPlayerToPlayerCard(player2, false);
        Assert.assertEquals(String.format("Player %d -- Name %s is choosing cards to play !!\n", player2.getId(), "player2"), getOutput());
    }



    PrintStream oldPrintStream;
    ByteArrayOutputStream bos;

    private void setOutput() throws Exception {
        oldPrintStream = System.out;
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
    }

    private String getOutput() { // throws Exception
        System.setOut(oldPrintStream);
        return bos.toString();
    }
}
