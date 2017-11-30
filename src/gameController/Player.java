package gameController;

import cards.Card;
import cards.Cards;

import java.util.List;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public abstract class Player {

    protected String name;
    protected GameController gameController;
    protected int id = -1;
    protected boolean isDiZhu;



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

//    public abstract void yourTurnToChooseToBeALandLord();
//    public abstract void informYouPlayerChooseToBeALandLord(Player theOneCalledToBeLandLord);
//
//    public abstract void yourTurnToGrapLandLord();
//    public abstract void informYouPlayerGrapToBeALandLord(Player theOneGrapedToBeLandLord);

//    public abstract void informPlayerPlayedCards(List<Card> cardsPlayed, Player thePlayerPlayedCard);
//
//    public abstract void endGame();

//    public void updateCards(ArrayList<Card> cards) {
//        // TODO Auto-generated method stub
//        for(Card c:cards){
//            hand.remove(c);
//        }
//    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public abstract List<Card> yourTurnToPlayCard(Cards lastTurnCards, List<Card> yourCard);


    public abstract void waitPlayerToPlayerCard(Player playerToPlay, boolean isLandLordToPlayCard);


    public abstract void youAreFarmer(Player teammates);

    public abstract void youAreLandLord();

    public abstract List<Card> yourTurnToPlayCardOrSkipCard(List<Card> cardsThatThePlayerHave, Cards lastTurnCards);

    public abstract void youWin();

    public abstract void youLose();

    public abstract void pleaseMakeAValidPlay();
}
