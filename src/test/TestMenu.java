package test;

import command.Menu;
import command.MenuGame;
import command.MenuMain;
import command.MenuRoomSelection;
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
    public void testMenuDesc() throws Exception {
        Menu menuMain = MenuMain.getInstance();
        String desc = menuMain.description();
        Assert.assertEquals("Main menu auto loaded", desc);
    }
    @Test
    public void testMenuKey() throws Exception {
        Menu menuMain = MenuMain.getInstance();
        String key = menuMain.key();
        Assert.assertNull(key);
    }

    @Test
    public void testMenuGame() {
        Menu menuGame = new MenuGame();
        menuGame.displayCommandList();
        Assert.assertEquals("p: Play some cards\r\n" +
                "q: Back\r\n", outContent.toString());
    }

    @Test
    public void testMenuGameDesc() throws Exception {
        Menu menuGame = new MenuGame();
        String desc = menuGame.description();
        Assert.assertEquals("Begin local game", desc);
    }

    @Test
    public void testMenuGameKey() throws Exception {
        Menu menuGame = new MenuGame();
        String key = menuGame.key();
        Assert.assertEquals("2", key);
    }

    @Test
    public void testMenuGameExecute() throws Exception {
        Menu menuGame = new MenuGame();
        menuGame.execute("1");
        Assert.assertEquals("Invalid Command [1]!\r\n", outContent.toString());
    }

    @Test
    public void testMenuGameExecute2() throws Exception {
        Menu menuGame = new MenuGame();
        menuGame.execute("q");
        Assert.assertEquals("Exit\r\n", outContent.toString());
    }

    @Test
    public void testMenuRoomSelection() {
        Menu menuRoomSelection = new MenuRoomSelection();
        menuRoomSelection.displayCommandList();
        Assert.assertEquals("1. Create Room\r\n" +
                "2. Join Room\r\n" +
                "3. Disconnect\r\n", outContent.toString());
    }

    @Test
    public void testMenuRoomSelectionDesc() throws Exception {
        Menu menuRoomSelection =new MenuRoomSelection();
        String desc = menuRoomSelection.description();
        Assert.assertEquals("Select room or Create room", desc);
    }

    @Test
    public void testMenuRoomSelectionKey() throws Exception {
        MenuRoomSelection menuRoomSelection = new MenuRoomSelection();
        String key = menuRoomSelection.key();
        Assert.assertNull(key);
    }

    @Test
    public void testMenuRoomSelectionExecute() throws Exception {
        MenuRoomSelection menuRoomSelection = new MenuRoomSelection();
        menuRoomSelection.execute();
        Assert.assertEquals("", outContent.toString());
    }

}
