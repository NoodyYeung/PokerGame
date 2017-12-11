package server;

import message.Message;
import message.ServerMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int LISTEN_PORT = 5999;

    public ArrayList<ServerPlayer> playerList = new ArrayList<>();

    public void listenRequest() {
        ServerSocket serverSocket = null;
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        try {
            serverSocket = new ServerSocket(LISTEN_PORT);
            System.out.println("Server listening requests...");
            while (true) {
                System.out.println("Server listening requests...");
                Socket socket = serverSocket.accept();
                ServerPlayer player = new ServerPlayer(socket);
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

    /**
     * Each client will use one thread to handle
     */
    class RequestThread implements Runnable {

        private ServerPlayer player;
        private Socket clientSocket;

        public RequestThread(ServerPlayer player) {
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
            ServerMessage.Builder builder = new ServerMessage.Builder();
            try {
                input = new DataInputStream(this.clientSocket.getInputStream());
                output = new DataOutputStream(this.clientSocket.getOutputStream());
                player.setOutputStream(output);
                player.setInputStream(input);

                /** Send message to client to notify that "you are connected to server" */
                builder.prepareClientConnectedMessage(player).sendToClient(output);
                ClientMessageHandler handler = new ClientMessageHandler(player);

                while (true) {

                    // TODO: received message form player's socket
                    if(input.available() <= 0)
                        continue;
                    while(player.isGameStarted()){
//                        System.out.println("[DEBUG] Reset");
                        Thread.sleep(1000);
                    }
                    String s = input.readUTF();
//                    System.out.println("[Debug] getMessage 2: " + s);
//                    System.out.println("[Debug] getMessage : " + s);
                    try {
                        handler.handleMessage(new Message(s));
                    } catch (ExInsuffientData exInsuffientData) {
                        exInsuffientData.printStackTrace();
                        builder.prepareInvalidCmd().sendToClient(output);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExInsuffientData exInsuffientData) {
                exInsuffientData.printStackTrace();
            } catch (InterruptedException e) {
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
        for (ServerPlayer player: playerList){
            player.write(message);
        }
    }
}
