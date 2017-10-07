package cs3343.group8.server;

/**
 * Created by yeungchunyin on 6/10/2017.
 */
public class ClientMessage {
    private String event;
    private String jsonStr;

    public ClientMessage(String rawString) throws ExInsuffientData {
        String msgs[] = rawString.split("\t");
        if(msgs.length < 2){
            throw new ExInsuffientData("ClientMessage construct needs a rawString that can be separate to event and data with a tab.");
        }
        this.event = msgs[0];
        this.jsonStr = msgs[1];
    }

    public String getEvent() {
        return event;
    }

    public String getJsonStr() {
        return jsonStr;
    }
}
