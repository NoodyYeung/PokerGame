package server;

import cards.Card;
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
    private String name;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;

    public ServerPlayer(Socket socket) throws IOException {
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
    public void setHand(List<Card> hand) {

    }

    @Override
    public void yourTurnToChooseToBeALandLord() {

    }

    @Override
    public void informYouPlayerChooseToBeALandLord(Player theOneCalledToBeLandLord) {

    }

    @Override
    public void yourTurnToGrapLandLord() {

    }

    @Override
    public void informYouPlayerGrapToBeALandLord(Player theOneGrapedToBeLandLord) {

    }

    @Override
    public void yourTurnToPlayCard(List<Card> lastTurnCards) {

    }

    @Override
    public void informPlayerPlayedCards(List<Card> cardsPlayed, Player thePlayerPlayedCard) {

    }

    @Override
    public void endGame() {

    }
}
