package command;

import client.ClientConnection;
import client.OnConnectedListener;

/**
 * This cmd is use for connecting server. The connection will keep connect until the
 * "quit" is entered to close the socket
 */

public class CmdConnect extends Command {

    @Override
    public void execute() {
//        String host = "127.0.0.1";
        String host = "ec2-3 4-214-202-228.us-west-2.compute.amazonaws.com";

        int port = 5999;

        ClientConnection client = ClientConnection.getInstance();

        client.setOnConnectedListener(new OnConnectedListener());
        System.out.println("Testing : connecting" );
        client.connectTo(host, port);
}

    @Override
    public String key() {
        return "1";
    }

    @Override
    public String description() {
        return "Connect to game";
    }
}
