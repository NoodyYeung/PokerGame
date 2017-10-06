package cs3343.group8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int LISTEN_PORT = 5998;

    public ArrayList<Player> playerList = new ArrayList<>();

    public void listenRequest() {
        ServerSocket serverSocket = null;
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        try {
            serverSocket = new ServerSocket(LISTEN_PORT);
            System.out.println("Server listening requests...");
            while (true) {
                Socket socket = serverSocket.accept();
                Player player = new Player(socket);
                playerList.add(player);
                threadExecutor.execute(new RequestThread(player));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (threadExecutor != null)
                threadExecutor.shutdown();
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    class RequestThread implements Runnable {

        private Player player;
        private Socket clientSocket;

        public RequestThread(Player player) {
            this.player = player;
            this.clientSocket = player.getSocket();
        }

        @Override
        public void run() {
            System.out.printf("%s connected!\n", clientSocket.getRemoteSocketAddress());
            System.out.println("player count "+ playerList.size());
            System.out.println("players: " +playerList.toString());
            DataInputStream input = null;
            DataOutputStream output = null;

            try {
                input = new DataInputStream(this.clientSocket.getInputStream());
                output = new DataOutputStream(this.clientSocket.getOutputStream());
                output.writeUTF(String.format("Hi, %s!\n", clientSocket.getRemoteSocketAddress()));
                while (true) {

                    // TODO: received message form player's socket
                    String s = input.readUTF();
                    System.out.println(s);
                    output.writeUTF("got " + s);
                    output.flush();

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (input != null)
                        input.close();
                    if (output != null)
                        output.close();
                    if (this.clientSocket != null && !this.clientSocket.isClosed())
                        this.clientSocket.close();
                    if (playerList.contains(player))
                        playerList.remove(player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendToAll(String message){
        for (Player player: playerList){
            player.write(message);
        }
    }
}
