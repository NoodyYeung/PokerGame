package test;

import command.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestCmd {

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
    public void testCmdHelp() {
        CmdHelp cmdHelp = new CmdHelp();
        cmdHelp.execute();
        Assert.assertEquals("Please read README.txt\r\n", outContent.toString());
    }


    @Test
    public void testCmdHelpKey() {
        CmdHelp cmdHelp = new CmdHelp();
        String key = cmdHelp.key();
        Assert.assertEquals("h", key);
    }


    @Test
    public void testCmdHelpDesc() {
        CmdHelp cmdHelp = new CmdHelp();
        String desc = cmdHelp.description();
        Assert.assertEquals("Help", desc);
    }

    @Test
    public void testCmdBack() {
        CmdBack cmdBack = new CmdBack();
        cmdBack.execute();
        Assert.assertEquals("Exit\r\n", outContent.toString());
    }


    @Test
    public void testCmdBackKey() {
        CmdBack cmdBack = new CmdBack();
        String key = cmdBack.key();
        Assert.assertEquals("q", key);
    }


    @Test
    public void testCmdBackDesc() {
        CmdBack cmdBack = new CmdBack();
        String desc = cmdBack.description();
        Assert.assertEquals("Back", desc);
    }


    @Test
    public void testCmd2PlayKey() {
        Cmd2Play cmd2Play = new Cmd2Play();
        String key = cmd2Play.key();
        Assert.assertEquals("p", key);
    }


    @Test
    public void testCmd2PlayDesc() {
        Cmd2Play cmd2Play = new Cmd2Play();
        String desc = cmd2Play.description();
        Assert.assertEquals("Play some card", desc);
    }


    @Test
    public void testCmdConnect() {
        CmdConnect cmdConnect = new CmdConnect();
        cmdConnect.execute();
        Assert.assertEquals("Exit\r\n" +
                "Testing : connecting\r\n", outContent.toString());
    }


    @Test
    public void testCmdConnectKey() {
        CmdConnect cmdConnect = new CmdConnect();
        String key = cmdConnect.key();
        Assert.assertEquals("1", key);
    }

    @Test
    public void testCmdConnectDesc() {
        CmdConnect cmdConnect = new CmdConnect();
        String desc = cmdConnect.description();
        Assert.assertEquals("Connect to game", desc);
    }


}
