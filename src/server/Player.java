package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Player {

    private static int id = 1;
    private String name;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;

    public Player(Socket socket) throws IOException {
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
