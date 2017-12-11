package test;

import command.Menu;
import command.MenuGame;
import command.MenuMain;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestMenu {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }


    @Test
    public void testMenu() throws Exception {
        Menu menuMain = MenuMain.getInstance();
        menuMain.displayCommandList();
        Assert.assertEquals("1: Connect to game\r\n"
                + "2: Begin local game+\r\n"
                + "h: Help\r\n"
                + "q: Back\r\n", outContent.toString());
    }


    @Test
    public void testMenuGame() {
        Menu menuGame = new MenuGame();
        menuGame.displayCommandList();
        Assert.assertEquals("p: Play some cards\r\n" +
                "q: Back\r\n", outContent.toString());
    }
}
