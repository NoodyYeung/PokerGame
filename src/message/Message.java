package message;

import server.ExInsuffientData;

/**
 * Created by yeungchunyin on 6/10/2017.
 * The message send from client to server. So the message will execute on server
 */
public class Message {


    /**
     * THE event with CLIENT prefix means the event is send from client and received from server
     * If a CLIENT event is received by a client, it means the event response from the server
     * IF a CLIENT event is received by the server, it means the event reach the server and server will execute the event
     *
     */

    /**
     *
     * Client Emit Events
     */
    public static final String CLIENT_EVENT_CMD_OPEN_ROOM = "create room";
    public static final String CLIENT_EVENT_JOIN_ROOM = "join room";


    /**
     * Server Emit Events
     */
    public static final String SERVER_EXCEPTION = "exception";
    public static final String SERVER_CONNECTED = "server connected";




    protected String event;
    protected String jsonStr;

    /**
     *
     * @param rawString consisted by "key +\t + jsonMessage"  Example: startGame    {"cards" : [1,2,3,3,4,5,6]}
     * @throws ExInsuffientData
     */
    public Message(String rawString) throws ExInsuffientData {
        String msgs[] = rawString.split("\t");
        if(msgs.length < 1){
            throw new ExInsuffientData("ClientMessage construct needs a rawString that can be separate to event and data with a tab.");
        }
        this.event = msgs[0];
        this.jsonStr = msgs[1];
    }

    public Message(String event, String jsonStr) {
        this.event = event;
        this.jsonStr = jsonStr;
    }

    public String getEvent() {
        return event;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    @Override
    public String toString() {
        return this.event + " " + this.jsonStr;
    }
}
