package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public abstract  class Client {


    public Client(){};
    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    protected OnConnectedListener onConnectedListener;
    protected OnMessageReceivedListener onMessageReceivedListener;
    public void setOnConnectedListener(OnConnectedListener onConnectedListener) {
        this.onConnectedListener = onConnectedListener;
    }

    public void setOnMessageReceivedListener(OnMessageReceivedListener onMessageReceivedListener) {
        this.onMessageReceivedListener = onMessageReceivedListener;
    }

    /**
     * The msg should consisted by two parts: key , and jsonmsg
     * Example:
     * putCard  {cards:[2,3,5,6]}
     * @param msg
     */
    public abstract void send(String msg);

}