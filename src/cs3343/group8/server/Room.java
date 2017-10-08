package cs3343.group8.server;

import java.util.ArrayList;

/**
 * Created by yeungchunyin on 8/10/2017.
 */
public class Room {

    private int roomId;
    private String name;
    private Player host;
    private ArrayList<Player> players = new ArrayList<>();

    public Room(int roomId, Player host) {
        this.roomId = roomId;
        this.host = host;
        this.players.add(host);
    }

    public void join (Player newPlayer) throws ExRoomFullException {
        if(this.players.size() >= 3){
            throw new ExRoomFullException("Room " + roomId + " is full");
        }else{
            players.add(newPlayer);
        }
    }

    /**
     *
     * @param player
     * @return True for remove success; False otherwise
     */
    public boolean leave(Player player){
        return players.remove(player);
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Name for display room
     */
    public String getRoomDisplayName(){
        if(name != null){
            return "Room " + roomId + ":\t" + name;
        }else return "Room " + roomId;
    }

}
