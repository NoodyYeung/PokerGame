package cs3343.group8.client;

import java.util.Scanner;

/**
 * This cmd is use for connecting server. The connection will keep connect until the
 * "quit" is entered to close the socket
 */
public class CmdConnect extends Command {
    private static final String CMD_QUIT = "quit";
    @Override
    public void execute() {
        String host = "127.0.0.1";
        int port = 5999;

        Client client = Client.getInstance();
        client.setOnConnectedListener(new OnConnectedListener() {
            @Override
            public void onSuccessConnected(String connectedMessage) {

            }

            @Override
            public void onMessageReceived(String receivedMessage) {
                System.out.println(receivedMessage);
                switch (receivedMessage){
                    case CMD_QUIT:
                        client.disconnect();
                        break;
                }
            }

            @Override
            public void onFailConnected() {

            }
        });
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
