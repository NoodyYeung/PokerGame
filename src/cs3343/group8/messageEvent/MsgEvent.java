package cs3343.group8.messageEvent;

/**
 * Created by yeungchunyin on 8/10/2017.
 */
public class MsgEvent {

    /**
     * THE event with CLIENT prefix means the event is send from client and received from server
     * If a CLIENT event is received by a client, it means the event response from the server
     * IF a CLIENT event is received by the server, it means the event reach the server and server will execute the event
     *
     */

    /**
     *
     * Client Events
     */
    public static final String CLIENT_EVENT_CMD_OPEN_ROOM = "create room";
    public static final String CLIENT_EVENT_JOIN_ROOM = "join room";


    /**
     * Server Events
     */
    public static final String SERVER_EXEECPTION = "exception";
}
