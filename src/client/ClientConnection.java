package client;

import message.Message;
import server.ExInsuffientData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ClientConnection{

    private ClientConnection(){
    };

    private static ClientConnection instance;
    public static ClientConnection getInstance(){
        if(instance == null)
            instance = new ClientConnection();
        return instance;
    }

    public boolean shouldBackToMainThread = false;
    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    protected OnConnectedListener onConnectedListener;

    public void setOnConnectedListener(OnConnectedListener onConnectedListener) {
        this.onConnectedListener = onConnectedListener;
    }



    public void connectTo(String host, int port) {
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        shouldBackToMainThread = false;
        try {
            socket = new Socket(host, port);
            threadExecutor.execute(new ReceiverThread(socket));
            /**
             * Block the main thread, and use the Receiver to communicate with the client
             */
            while(!shouldBackToMainThread){
                Thread.sleep(1000); // Check shouldBackToMainThread per 1 second
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        shouldBackToMainThread = true;
        if(socket.isConnected()){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    if(onConnectedListener != null){
                        String messageFromServer = input.readUTF();
                        onConnectedListener.onSuccessConnected(new Message(messageFromServer));
                    }
                    /**
                     * Currently, there is no way to confirm whether the client is connecting. Heartbeat implementation could handle
                     * this problem.
                     */
                    while (socket.isConnected()) {
                        if(onConnectedListener!= null)
                            onConnectedListener.onMessageReceived(new Message(input.readUTF()));
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                    if(onConnectedListener != null)
                        onConnectedListener.onFailConnected();
                } catch (ExInsuffientData exInsuffientData) {
                    exInsuffientData.printStackTrace();
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
                    shouldBackToMainThread = true;
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