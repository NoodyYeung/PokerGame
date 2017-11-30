package server;

import cards.Card;
import cards.Cards;
import gameController.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Server Player is the player on  the server
 * This class should extends Player.java. For development, i will add it later.
 */
public class ServerPlayer  extends Player{

    private static int id = 1;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;

    public ServerPlayer(Socket socket) throws IOException {
        this.setId(id);
        this.name = "Player" + id++;
        this.socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void write(String s) {
        try {
            outputStream.writeUTF(s);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }




    @Override
    public List<Card> yourTurnToPlayCard(Cards lastTurnCards, List<Card> yourCard) {
        return null;
    }

    @Override
    public void waitPlayerToPlayerCard(Player playerToPlay, boolean isLandLordToPlayCard) {

    }

    @Override
    public void youAreFarmer(Player teammates) {

    }

    @Override
    public void youAreLandLord() {

    }

    @Override
    public List<Card> yourTurnToPlayCardOrSkipCard(List<Card> cardsThatThePlayerHave, Cards lastTurnCards) {
        return null;
    }

    @Override
    public void youWin() {

    }

    @Override
    public void youLose() {

    }

    @Override
    public void pleaseMakeAValidPlay() {

    }


    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }
}
