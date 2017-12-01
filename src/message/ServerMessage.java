package message;

import cards.Card;
import cards.Cards;
import gameController.Player;
import org.json.JSONException;
import org.json.JSONObject;
import server.ExInsuffientData;
import server.Room;
import server.RoomsController;
import server.ServerPlayer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

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

    public void sendToClient(SocketChannel socket) throws IOException {
        byte[] message = new String(toString()).getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(message);
        socket.write(buffer);
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

        public ServerMessage prepareResponseRoomSomeUserJoinedToThisRoom(ServerPlayer player){
            JSONObject json = new JSONObject();
            try{
                json.put("message","Player" + player.getId() + " joined to room ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_RESPONSE_SOME_USER_JOINED_ROOM, json.toString());
        }

        public ServerMessage prepareStartGameMessage(){
            JSONObject json = new JSONObject();
            try{
                json.put("message", "Game start !!! ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_START_GAME, json.toString());
        }

         public ServerMessage prepareYourTurnToPlayCard(Cards lastTurnCards, List<Card> yourCard) {
            JSONObject json = new JSONObject();
            try{
                List<Card> lastHands = lastTurnCards != null ? lastTurnCards.getCards() : null;
                json.put("lastTurn", lastHands != null ? Cards.toString(lastHands) : "-1");
                json.put("yourCard", Cards.toString(yourCard));
            }catch (JSONException e){
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_YOUR_TURN_TO_PLAY, json.toString());
         }

         public ServerMessage prepareWaitPlayerToPlayerCardMessage(Player playerToPlay, boolean isLandLordToPlayCard) {
            JSONObject json = new JSONObject();
            try{
                json.put("message", String.format("Player %d -- Name %s is choosing cards to play !!\n", playerToPlay.getId(), playerToPlay.getName()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_WAIT_PLAYER_TO_PLAY_CARD, json.toString());
         }

         public ServerMessage prepareYouAreFarmerMessage(ServerPlayer serverPlayer, Player teammates) {
            JSONObject json = new JSONObject();
            try{
                json.put("message", String.format("You are farmer. Your teammate is Player %d -- Name %s \n", teammates.getId(), teammates.getName()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_YOU_ARE_FARMER, json.toString());
         }

         public ServerMessage prepareYouLandLordMessage() {
             JSONObject json = new JSONObject();
             try{
                 json.put("message", String.format("You are landLoad !\n"));
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             return new ServerMessage(Message.SERVER_YOU_ARE_LANDLORD, json.toString());
        }

         public ServerMessage prepareYourTurnToPlayCardOrSkipCard(List<Card> cardsThatThePlayerHave,  Cards lastTurnCards ) {
             JSONObject json = new JSONObject();
             try{
                 List<Card> lastHand = lastTurnCards != null ? lastTurnCards.getCards() : null;
                 json.put("lastHand", lastHand != null ? Cards.toString(lastHand) : "-1");
                 json.put("hand", Cards.toString(cardsThatThePlayerHave));
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             return new ServerMessage(Message.SERVER_YOUR_TURN_TO_PLAY_CARD_OR_SKIP, json.toString());
         }

         public ServerMessage prepareInvalidPlaysMessage() {
             JSONObject json = new JSONObject();
             try{
                  json.put("message", "Invalid Cards Combination");
             }catch (JSONException e) {
                 e.printStackTrace();
             }
             return new ServerMessage(Message.SERVER_INVALID_PLAY, json.toString());
         }

         public ServerMessage prepareYouWinMessage() {
             JSONObject json = new JSONObject();
             try{
                 json.put("message", "You win\n");
             }catch (JSONException e) {
                 e.printStackTrace();
             }
             return new ServerMessage(Message.SERVER_YOU_WIN, json.toString());
         }

         public ServerMessage prepareYouLoseMessage() {
             JSONObject json = new JSONObject();
             try{
                 json.put("message", "You lose\n");
             }catch (JSONException e) {
                 e.printStackTrace();
             }
             return new ServerMessage(Message.SERVER_YOU_LOSE, json.toString());

         }

         public ServerMessage preparePleaseMakeAValidPlayMessage() {
             JSONObject json = new JSONObject();
             try{
                 json.put("message", "Please make a valid play\n");
             }catch (JSONException e) {
                 e.printStackTrace();
             }
             return new ServerMessage(Message.SERVER_PLEASE_MAKE_A_VALID_PLAY, json.toString());
        }

        public ServerMessage prepareInvalidCmd() {
            JSONObject json = new JSONObject();
            try{
                json.put("message", "Invalid Command\n");
            }catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_INVALID_CMD, json.toString());
        }

        public ServerMessage prepareCountInfoMessage(String s) {
            JSONObject json = new JSONObject();
            try{
                json.put("message", "s\n");
            }catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_CARD_COUNT_MESSAGE, json.toString());
        }
    }

}
