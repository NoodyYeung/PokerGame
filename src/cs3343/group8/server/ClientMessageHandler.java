package cs3343.group8.server;

/**
 * Created by yeungchunyin on 7/10/2017.
 */
public class ClientMessageHandler {
    public ClientMessageHandler(){};

    public void handleMessage(ClientMessage msg){
        String event = msg.getEvent();
        String jsonStr = msg.getJsonStr();
        switch (event){
            case "":
                break;

        }
    }
}
