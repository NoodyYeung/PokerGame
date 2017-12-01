package message;

import org.json.JSONException;
import org.json.JSONObject;
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
    public static final java.lang.String CLIENT_RAW = "raw";



    /**
     * Server Emit Events
     */
    public static final String SERVER_EXCEPTION = "exception";
    public static final String SERVER_CONNECTED = "server connected";

    public static final String SERVER_RESPONSE_ROOM_CREATED = "roomCreated";
    public static final String SERVER_RESPONSE_ROOM_IS_FULL = "roomIsFull";
    public static final String SERVER_RESPONSE_ROOM_IS_NOT_FOUND= "roomIsNeeded";
    public static final String SERVER_RESPONSE_JOINED_ROOM = "joinedRoom";
    public static final String SERVER_RESPONSE_SOME_USER_JOINED_ROOM = "someoneJoinedRoom";
    public static final String SERVER_START_GAME = "serverStartGame";
    public static final java.lang.String SERVER_YOUR_TURN_TO_PLAY = "yourTurnToPlay";
    public static final java.lang.String SERVER_WAIT_PLAYER_TO_PLAY_CARD = "waitPlayerToPlayCard";
    public static final java.lang.String SERVER_YOU_ARE_FARMER = "youAreFarmer";
    public static final java.lang.String SERVER_YOU_ARE_LANDLORD = "youAreLandLord";
    public static final java.lang.String SERVER_YOUR_TURN_TO_PLAY_CARD_OR_SKIP = "yourTurnToPlayCardOrSkip";
    public static final java.lang.String SERVER_INVALID_PLAY = "invalidPlay";
    public static final java.lang.String SERVER_YOU_WIN = "youWin";
    public static final java.lang.String SERVER_YOU_LOSE = "youLose";
    public static final java.lang.String SERVER_PLEASE_MAKE_A_VALID_PLAY = "pleaseMakeAValidPlay";
    public static final java.lang.String SERVER_INVALID_CMD = "SERVER_INVALID_CMD" ;


    protected String event;
    protected String jsonStr;

    /**
     *
     * @param rawString consisted by "key +\t + jsonMessage"  Example: startGame    {"cards" : [1,2,3,3,4,5,6]}
     * @throws ExInsuffientData
     */
    public Message(String rawString) throws ExInsuffientData {
        String msgs[] = rawString.split("\t\t");
        if(msgs.length < 2){
            throw new ExInsuffientData("ClientMessage construct needs a rawString that can be separate to event and data with a tab. \n" +rawString);
        }
        this.event = msgs[0];
        this.jsonStr = msgs[1];
    }

    public Message(String event, String jsonStr) {
        this.event = event;
        this.jsonStr = jsonStr;
    }

    /**
     *
     * @return the jsonObject by using the jsonStr
     */
    public JSONObject getJSONObject(){
        try {
            return new JSONObject(this.jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEvent() {
        return event;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    @Override
    public String toString() {
        return this.event + "\t\t" + this.jsonStr;
    }
}
