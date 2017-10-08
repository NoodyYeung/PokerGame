package cs3343.group8.client;

import java.util.Scanner;

public class CmdConnect extends Command {

    @Override
    public void execute() {
        String host = "127.0.0.1";
        int port = 5998;

        Client client = new Client();
        client.connectTo(host, port);

        Scanner scanner = new Scanner(System.in);
        while (client.isConnecting()) {
            // TODO: send message to server if IsMyTurn

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
