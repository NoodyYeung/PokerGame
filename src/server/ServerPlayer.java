package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Server Player is the player on  the server
 * This class should extends Player.java. For development, i will add it later.
 */
public class ServerPlayer  {

    private static int id = 1;
    private String name;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;

    public ServerPlayer(Socket socket) throws IOException {
        this.name = "Player" + id++;
        this.socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void write(String s) {
        try {
            outputStream.writeUTF(s);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }



}
