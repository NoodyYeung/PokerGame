package cs3343.group8.server;

/**
 * Created by yeungchunyin on 8/10/2017.
 */
public class ExRoomFullException extends Exception {
    public ExRoomFullException(String s) {
        super(s);
    }
}