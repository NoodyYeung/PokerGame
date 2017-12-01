package server;

import message.Message;
import message.ServerMessage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by yeungchunyin on 7/10/2017.
 * Used for server to receive cards
 */
public class ClientMessageHandler {
    private ServerPlayer player;

    public ClientMessageHandler(ServerPlayer player) {
        this.player = player;
    }

    public void handleMessage(Message msg) throws ExInsuffientData, IOException {
        String event = msg.getEvent();
        JSONObject value = msg.getJSONObject();
        RoomsController roomsController = RoomsController.getInstance();
        ServerMessage.Builder builder = new ServerMessage.Builder();

        System.out.println("[Debug] : receivedMessage " + value.toString());
        /**
         * All the event that can trigger by client
         */
        switch (event){
            case Message.CLIENT_EVENT_CMD_OPEN_ROOM:
                try {
                    Room room =  roomsController.createNewRoom(player, value.getString("name"));
                    builder.prepareResponseMessageOnRoomCreated(player, room).sendToClient(player.getOutputStream());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Message.CLIENT_EVENT_JOIN_ROOM:
                int roomId = -1;
                try {
                    roomId = value.getInt("roomId");
                    roomsController.joinRoomWithRoomId(player, roomId);
                    Room room = roomsController.findRoomById(roomId);
                    List<ServerPlayer> players = room.getPlayers();
                    // broadcast the "player joined" message
                    for(ServerPlayer p : players){
                        if(p == player){
                            builder.prepareResponseRoomJoinedRoom(player, roomId).sendToClient(p);
                        }else {
                            builder.prepareResponseRoomSomeUserJoinedToThisRoom(player).sendToClient(p);
                        }
                    }

                    // If the room is full, start the game
                    if(players.size() == 3){
                        for(ServerPlayer p : players){
                            builder.prepareStartGameMessage().sendToClient(p);
                        }
                        room.startGame();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                } catch (ExRoomNotFound exRoomNotFound) {
                    builder.prepareResponseRoomIsNotFoundMsg(player, roomId).sendToClient(player);
                    exRoomNotFound.printStackTrace();

                } catch (ExRoomFullException e) {
                    builder.prepareResponseRoomIsFullMessage(player, roomId).sendToClient(player);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            default:
                break;
        }
    }




}
