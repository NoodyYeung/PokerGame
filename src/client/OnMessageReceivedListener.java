package client;

import message.Message;
import server.ExInsuffientData;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public class OnMessageReceivedListener {

    private void onMsgReceived(String rawMessage){
        try {
            Message clientMessage = new Message(rawMessage);

        } catch (ExInsuffientData exInsuffientData) {
            exInsuffientData.printStackTrace();
        }
    }


}
