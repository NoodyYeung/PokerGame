package server;

import gameController.GameController;
import gameController.InsufficientPlayerException;

import java.util.ArrayList;

/**
 * Created by yeungchunyin on 8/10/2017.
 */
public class Room {

    private int roomId;
    private String name;
    private ServerPlayer host;
    private ArrayList<ServerPlayer> players = new ArrayList<>();
    private GameController gameController;

    public Room(int roomId, ServerPlayer host) {
        this.roomId = roomId;
        this.host = host;
        this.players.add(host);

    }

    public void join (ServerPlayer newPlayer) throws ExRoomFullException {
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
    public boolean leave(ServerPlayer player){
        return players.remove(player);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "; Players : " + players.size() + "/3" ;
    }

    public int getRoomId() {
        return roomId;
    }

    public ArrayList<ServerPlayer> getPlayers() {
        return players;
    }

    public void startGame(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gameController = new GameController(players);
                    gameController.startGame();

                } catch (InsufficientPlayerException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public GameController getGameController() {
        return gameController;
    }
}
