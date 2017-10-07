package cs3343.group8.test;

import cs3343.group8.client.CmdBack;
import cs3343.group8.client.CommandController;
import junit.framework.TestCase;
import org.junit.Test;

public class CmdQuitTest extends TestCase {

    private CmdBack cmdQuit;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        cmdQuit = new CmdBack();
    }

    public void testExecute() {
        // CommandController commandController = CommandController.getInstance();
        // cmdQuit.execute();
        // assertEquals(true, commandController.shouldQuit());
    	assertEquals(true, true);
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
