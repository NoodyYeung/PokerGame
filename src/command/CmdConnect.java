package command;

import cs3343.group8.messageEvent.MsgEvent;
import server.ExInsuffientData;

import java.util.Scanner;

import client.Client;
import client.ClientMessage;

/**
 * This cmd is use for connecting server. The connection will keep connect until the
 * "quit" is entered to close the socket
 */

public class CmdConnect extends Command {

    @Override
    public void execute() {
        String host = "127.0.0.1";
        int port = 5999;

        Client client = Client.getInstance(); 
        
        client.setOnConnectedListener(new Client.OnConnectedListener(){
            @Override
            public void onSuccessConnected(String connectedMessage) {

            }

            @Override
            public void onMessageReceived(String receivedMessage) {
                System.out.println(receivedMessage);
                try {
                    ClientMessage msg = new ClientMessage(receivedMessage);
                    switch (msg.getEvent()){
                        case MsgEvent.CLIENT_EVENT_CMD_OPEN_ROOM:
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
        }
         
        		);
        client.connectTo(host, port);
        Scanner scanner = new Scanner(System.in);
        while (client.isConnecting()) {
            String message = scanner.nextLine();
            client.send("Simple : \t" + message);
        }

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
