package message;

import org.json.JSONException;
import org.json.JSONObject;
import server.ExInsuffientData;
import server.Room;
import server.RoomsController;
import server.ServerPlayer;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by yeungchunyin on 4/11/2017.
 */
public class ServerMessage extends Message {
    public ServerMessage(String rawString) throws ExInsuffientData {
        super(rawString);
    }

    public ServerMessage(String event, String jsonStr) {
        super(event, jsonStr);
    }


    public void sendToClient(DataOutputStream output) throws IOException {
        output.writeUTF(this.toString());
    }
    public void sendToClient(ServerPlayer player) throws IOException {
        player.getOutputStream().writeUTF(this.toString());
    }


     public static class Builder {

        public ServerMessage prepareClientConnectedMessage(ServerPlayer player) throws ExInsuffientData {
            JSONObject json = new JSONObject();
            if(player == null){
                throw new ExInsuffientData("Player is needed");
            }
            RoomsController roomsController = RoomsController.getInstance();
            try {
                json.put("message", "Hi, player" + player.getId());
                json.put("roomInfo", roomsController.getRoomsInformation());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_CONNECTED, json.toString());
        }

        public ServerMessage prepareResponseMessageOnRoomCreated(ServerPlayer player, Room room) throws ExInsuffientData {
            JSONObject json = new JSONObject();
            if(player == null){
                throw new ExInsuffientData("Player is needed");
            }
            try {
                json.put("message", "Room created; Room ID : " + room.getRoomId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_RESPONSE_ROOM_CREATED, json.toString());
        }

        public ServerMessage prepareResponseRoomIsFullMessage(ServerPlayer player, int id) throws ExInsuffientData {
            JSONObject json = new JSONObject();
            if(player == null){
                throw new ExInsuffientData("Player is needed");
            }
            try{
                json.put("message", "Room " +id + " is full");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_RESPONSE_ROOM_IS_FULL, json.toString());
        }

        public ServerMessage prepareResponseRoomIsNotFoundMsg(ServerPlayer player, int id) throws ExInsuffientData {
            JSONObject json = new JSONObject();
            if(player == null){
                throw new ExInsuffientData("Player is needed");
            }
            try{
                json.put("message", "Room " + id + " is not existed");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ServerMessage(Message.SERVER_RESPONSE_ROOM_IS_NOT_FOUND, json.toString());
        }

        public ServerMessage prepareResponseRoomJoinedRoom(ServerPlayer player, int id) throws ExInsuffientData {
            JSONObject json = new JSONObject();
            if(player == null){
                throw new ExInsuffientData("Player is needed");
            }
            try{
                json.put("message", "Joined to Room " + id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_RESPONSE_JOINED_ROOM, json.toString());
        }

        public ServerMessage prepareResponseRoomSomeUserJoinedToThisRoom(ServerPlayer player)throws ExInsuffientData{
            JSONObject json = new JSONObject();
            try{
                json.put("message","Player" + player.getId() + "joined to room ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_RESPONSE_SOME_USER_JOINED_ROOM, json.toString());
        }



    }

}
