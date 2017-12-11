package test;

import client.AIPlayer;
import client.LocalPlayer;
import gameController.GameController;
import gameController.InsufficientPlayerException;
import gameController.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by yeungchunyin on 11/12/2017.
 */
public class TestGameController {
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
    public void testStartGame(){
        List<Player> players = new ArrayList<>();
        Player p1 = new LocalPlayer();
        Player p2 = new AIPlayer();
        Player p3 = new AIPlayer();
        players.add(p1);
        players.add(p2);
        players.add(p3);

        try {
            GameController controller = new GameController((ArrayList) players);
            controller.startGame();


        } catch (InsufficientPlayerException e) {
            fail("Exception should not be throw");
            e.printStackTrace();
        }


    }
}
