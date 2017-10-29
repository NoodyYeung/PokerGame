package client;

import server.ExInsuffientData;

/**
 * Created by yeungchunyin on 6/10/2017.
 * The message send from client to server. So the message will execute on server
 */
public class ClientMessage {

    private String event;
    private String jsonStr;

    /**
     *
     * @param rawString consisted by "key +\t + jsonMessage"  Example: startGame    {"cards" : [1,2,3,3,4,5,6]}
     * @throws ExInsuffientData
     */
    public ClientMessage(String rawString) throws ExInsuffientData {
        String msgs[] = rawString.split("\t");
        if(msgs.length < 1){
            throw new ExInsuffientData("ClientMessage construct needs a rawString that can be separate to event and data with a tab.");
        }
        this.event = msgs[0];
        this.jsonStr = msgs[1];
    }

    public ClientMessage(String event, String jsonStr) {
        this.event = event;
        this.jsonStr = jsonStr;
    }
    public void sendMessageToServer(Client client){
        client.send(this.event + "\t" + this.jsonStr);
    }

    public String getEvent() {
        return event;
    }

    public String getJsonStr() {
        return jsonStr;
    }
}
