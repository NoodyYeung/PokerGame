package message;

import client.ClientConnection;
import org.json.JSONException;
import org.json.JSONObject;
import server.ExInsuffientData;

/**
 * Created by yeungchunyin on 4/11/2017.
 * The message from client send to server
 */
public class ClientMessage extends Message {
    public ClientMessage(String rawString) throws ExInsuffientData {
        super(rawString);
    }

    public ClientMessage(String event, String jsonStr) {
        super(event, jsonStr);
    }

    public void sendMessageToServer(ClientConnection client){
        client.send(this.toString());
    }


    public static class Builder {
        /**
         * Create room on server
         * @param roomName
         * @return
         */
        public ClientMessage prepareCreateNewRoomMsg(String roomName){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", roomName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ClientMessage(Message.CLIENT_EVENT_CMD_OPEN_ROOM, jsonObject.toString() );
        }

        /**
         * Join the romm on server
         * @param roomNumber
         * @return
         */
        public ClientMessage prepareJoinRoomMessage(int roomNumber){
            JSONObject json = new JSONObject();
            try {
                json.put("roomId", roomNumber);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ClientMessage(Message.CLIENT_EVENT_JOIN_ROOM, json.toString());

        }

        public ClientMessage prepareRawTextMessage(String message){
            JSONObject json = new JSONObject();
            try {
                json.put("raw", message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ClientMessage(Message.CLIENT_RAW, json.toString());
        }
    }

}
