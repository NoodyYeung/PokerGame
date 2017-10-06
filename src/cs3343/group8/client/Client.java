package cs3343.group8.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    public void connectTo(String host, int port) {
        ExecutorService threadExecutor = Executors.newCachedThreadPool();

        try {
            socket = new Socket(host, port);
            threadExecutor.execute(new ReceiverThread(socket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnecting() {
        return socket.isConnected();
    }

    class ReceiverThread implements Runnable {

        private Socket socket;

        ReceiverThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                try {
                    input = new DataInputStream(socket.getInputStream());
                    output = new DataOutputStream(socket.getOutputStream());
                    System.out.println(input.readUTF());

                    while (socket.isConnected()) {
                        try {
                            // keep read stream
                            System.out.println(input.readUTF());

                            // TODO: do something when receive message form server

                        } catch (EOFException e) {
                            System.out.println(e.getMessage());
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (input != null)
                        input.close();
                    if (output != null)
                        output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void send(String message) {
        try {
            output.writeUTF(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}