package gameController;

import cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public abstract class Player {

    protected String name;
    protected List<Card> hand;
    protected GameController gameController;
    protected int id = -1;
    protected boolean isDiZhu;

    public Player(){
        hand = new ArrayList<Card>();
    }

    // Takes a hand of cards (Classic gaming where you take cards one by one? Version 3?)
    public void take(List<Card> cards){
        for(Card card: cards)
            hand.add(card);
    }
    
    public void take(Card card){
          hand.add(card);
    }
    public boolean isDiZhu(){
        return isDiZhu;
    }

    /**
     * if Player id == -1. Then the id can be set
     * If Player id != -1 The Id cannot be set
     * The purpose is to avoiding server assigning duplicated id to players and avoiding reassigning id to player
     * @param id
     * @return True if id is changed. Otherwise, false
     */
    public boolean setId(int id){
        if(this.id == -1) {
            this.id = id;
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        if(name == null){
            return "Player" + this.id;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void setHand(List<Card> hand) ;

    public abstract void yourTurnToChooseToBeALandLord();
    public abstract void informYouPlayerChooseToBeALandLord(Player theOneCalledToBeLandLord);

    public abstract void yourTurnToGrapLandLord();
    public abstract void informYouPlayerGrapToBeALandLord(Player theOneGrapedToBeLandLord);

    public abstract void yourTurnToPlayCard(List<Card> lastTurnCards);
    public abstract void informPlayerPlayedCards(List<Card> cardsPlayed, Player thePlayerPlayedCard);

    public abstract void endGame();

    public void updateCards(ArrayList<Card> cards) {
        // TODO Auto-generated method stub
        for(Card c:cards){
            hand.remove(c);
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
