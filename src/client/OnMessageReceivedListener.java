package client;

import server.ExInsuffientData;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public class OnMessageReceivedListener {

    private void onMsgReceived(String rawMessage){
        try {
            ClientMessage clientMessage = new ClientMessage(rawMessage);

        } catch (ExInsuffientData exInsuffientData) {
            exInsuffientData.printStackTrace();
        }
    }


}
