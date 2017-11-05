package server;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    public static int roomId = 0;
    public Map<Integer, Room> rooms = new LinkedHashMap<>();

    public Room createNewRoom(ServerPlayer host){
        Room room = new Room(roomId, host);
        rooms.put(roomId++ , room);
        return room;
    }

    public Room createNewRoom(ServerPlayer host, String roomName){
        Room room = createNewRoom(host);
        room.setName(roomName);
        return room;
    }

    public void joinRoomWithRoomId(ServerPlayer player, int id) throws ExRoomFullException, ExRoomNotFound {
       findRoomById(id).join(player);
    }

    public Room findRoomById(int id) throws ExRoomNotFound {
        if(rooms.containsKey(id)){
            return rooms.get(id);
        }else throw new ExRoomNotFound("Room Not Found");
    }


    /**
     *
     * @return {String} the rooms information. Each row for one room
     */
    public String getRoomsInformation(){
        String roomInfoStr = "";
        Set<Map.Entry<Integer, Room>> set = this.rooms.entrySet();
        if(set.size() == 0){ // No room is available
            roomInfoStr = "No room is available";
        }else{ // At least one room is available
            for(Map.Entry<Integer, Room> entry : set){
                roomInfoStr += "Room " + entry.getKey() + " : " + entry.getValue().toString() + "\n";
            }
        }
        return roomInfoStr;
    }
}
