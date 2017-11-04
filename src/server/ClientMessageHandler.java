package server;

import message.Message;

/**
 * Created by yeungchunyin on 7/10/2017.
 * Used for server to receive cards
 */
public class ClientMessageHandler {
    private ServerPlayer player;

    public ClientMessageHandler(ServerPlayer player) {
        this.player = player;
    }

    public void handleMessage(String rawString) throws ExInsuffientData {
        String msgs[] = rawString.split("\t");
        if(msgs.length < 1){
            throw new ExInsuffientData("Server received message: needs a rawString that can be separate to event and data with a tab.");
        }
        String event = msgs[0];

        String jsonStr = null;
        if(msgs.length > 1){
            jsonStr = msgs[1];
        }
        /**
         * All the event that can trigger by client
         */
        switch (event){
            case Message.CLIENT_EVENT_CMD_OPEN_ROOM:
                break;

            default:
                break;
        }
    }

}
