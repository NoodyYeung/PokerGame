package cs3343.group8.test;

import cs3343.group8.client.CmdQuit;
import cs3343.group8.client.CommandController;
import junit.framework.TestCase;
import org.junit.Test;

public class CmdQuitTest extends TestCase {

    private CmdQuit cmdQuit;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        cmdQuit = new CmdQuit();
    }

    @Test
    public void testExecute() {
        CommandController commandController = CommandController.getInstance();
        cmdQuit.execute();
        assertEquals(true, commandController.shouldQuit());
    }

    @Test
    public void testKey() {
        String key = cmdQuit.key();
        assertEquals("q", key);
    }

    @Test
    public void testDescriptions() {
        String d = cmdQuit.description();
        assertEquals("Quit", d);
    }
}
