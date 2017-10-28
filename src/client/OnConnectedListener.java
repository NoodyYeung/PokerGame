package client;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public interface OnConnectedListener{
    void onSuccessConnected(String connectedMessage);
    void onMessageReceived(String receivedMessage);
    void onFailConnected();
}
