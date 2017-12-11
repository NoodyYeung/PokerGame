package test;

import cards.Cards;
import client.LocalPlayer;
import client.Main;
import gameController.Player;
import org.junit.Assert;
import org.junit.Test;
import pattern.Pone;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TestPlayer {

    @Test
    public void testLocalPlayerId() {
        Player player = new LocalPlayer();
        player.setId(1);
        int id = player.getId();
        Assert.assertEquals(1, id);
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
        Assert.assertEquals("player1: Gotta catch them all! ", toString);
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
        teammates.setId(9);
        teammates.setName("teammate");
        setOutput();
        player.youAreFarmer(teammates);
        Assert.assertEquals(String.format("You are farmer. Your teammate is Player %d -- Name %s \n", 9, "teammate"), getOutput());
    }

//    @Test
//    public void testLocalPlayerYouTurn() throws Exception {
//        Player player = new LocalPlayer();
//        Main.systemIn = new Scanner(System.in);
//        setOutput();
//        Cards cards = new Cards(new ArrayList<>(), null);
//        player.yourTurnToPlayCard(cards, new ArrayList<>());
//        Assert.assertEquals("No last player cards. You are free to put cards on the table\n" + "Card in your hand : \n", getOutput());
//    }

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
