package message;

import org.json.JSONException;
import org.json.JSONObject;
import server.ExInsuffientData;
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

     public static class Builder {

        public ServerMessage prepareClientConnectedMessage(ServerPlayer player) throws ExInsuffientData {
            JSONObject json = new JSONObject();
            if(player == null){
                throw new ExInsuffientData("Player is needed");
            }

            try {
                json.put("message", "Hi, player" + player.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ServerMessage(Message.SERVER_CONNECTED, json.toString());
        }
    }

}
