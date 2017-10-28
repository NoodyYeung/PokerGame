package gameController;

import cards.Card;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    /**
     * 0, 1, 2 for indicating the user turn
     */
    private int turn = 0;
    private ArrayList<Player> playersInThisGame;
    private Deck deck;
    private boolean isGameInit = false;

    public GameController(ArrayList<Player> players) throws InsufficientPlayerException {
        if(players.size() != 3){
            throw new InsufficientPlayerException("Insufficient players. At least three player is needed");
        }
        this.playersInThisGame = players;
        this.deck = new Deck();
        for(Player p : this.playersInThisGame){
            p.setGameController(this);
        }

    }

    public void startGame(){
        List<Card>[] decks = deck.distribute();
        for(int i = 0; i < playersInThisGame.size(); i ++) {
            Player player = playersInThisGame.get(i);
            player.setHand(decks[i]);
            if (turn == i) {
                player.yourTurnToPlayCard(null);
            }
        }
    }

    public void playCard(Player player){

    }

    public void callForLandLord(Player player){

    }

    public void grapForLandLord(Player player){

    }

    public void skipToPlayCard(Player player){}



}
