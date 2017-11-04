package command;

import client.ClientConnection;
import client.OnConnectedListener;
import message.Message;
import server.ExInsuffientData;

/**
 * This cmd is use for connecting server. The connection will keep connect until the
 * "quit" is entered to close the socket
 */

public class CmdConnect extends Command {

    @Override
    public void execute() {
        String host = "127.0.0.1";
        int port = 5999;

        ClientConnection client = new ClientConnection();

        client.setOnConnectedListener(new OnConnectedListener(){
            @Override
            public void onSuccessConnected(String connectedMessage) {


            }

            @Override
            public void onMessageReceived(String receivedMessage) {
                System.out.println(receivedMessage);
                try {
                    Message msg = new Message(receivedMessage);
                    switch (msg.getEvent()){
                        case Message.CLIENT_EVENT_CMD_OPEN_ROOM:
                            //TODO::Response from server
                            System.out.println("Responses from server");
                            break;
                    }
                } catch (ExInsuffientData exInsuffientData) {
                    exInsuffientData.printStackTrace();
                }
            }

            @Override
            public void onFailConnected() {

            }
        });
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
