package cs3343.group8.server;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yeungchunyin on 8/10/2017.
 * This intends to control all the Room
 */
public class RoomsController {
    private RoomsController(){}
    private static RoomsController instance;
    public static RoomsController getInstance(){
        instance = new RoomsController();
        return instance;
    }
    public int roomId = 0;
    public Map<Integer, Room> rooms = new LinkedHashMap<>();

    public Room createNewRoom(Player host){
        Room room = new Room(roomId, host);
        rooms.put(roomId++ , room);
        return room;
    }

    public Room createNewRoom(Player host, String roomName){
        Room room = createNewRoom(host);
        room.setName(roomName);
        return room;
    }

    public void joinRoomWithRoomId(Player player, int id) throws ExRoomFullException, ExRoomNotFound {
        if(rooms.containsKey(id)){
            rooms.get(id).join(player);
        }else throw new ExRoomNotFound("Room Not Found");
    }
}
