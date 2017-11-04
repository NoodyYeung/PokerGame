package message;

import client.Client;
import server.ExInsuffientData;

/**
 * Created by yeungchunyin on 4/11/2017.
 */
public class ClientMessage extends Message {
    public ClientMessage(String rawString) throws ExInsuffientData {
        super(rawString);
    }

    public ClientMessage(String event, String jsonStr) {
        super(event, jsonStr);
    }

    public void sendMessageToServer(Client client){
        client.send(this.event + "\t" + this.jsonStr);
    }


    public static class Builder {

    }

}
