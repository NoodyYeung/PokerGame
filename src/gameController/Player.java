package gameController;

import cards.Card;

import java.util.List;

/**
 * Created by yeungchunyin on 29/10/2017.
 */
public abstract class Player {

    protected String name;
    protected List<Card> hand;
    protected GameController gameController;


    public abstract void setHand(List<Card> hand) ;

    public abstract void yourTurnToChooseToBeALandLord();
    public abstract void informYouPlayerChooseToBeALandLord(Player theOneCalledToBeLandLord);

    public abstract void yourTurnToGrapLandLord();
    public abstract void informYouPlayerGrapToBeALandLord(Player theOneGrapedToBeLandLord);

    public abstract void yourTurnToPlayCard(List<Card> lastTurnCards);
    public abstract void informPlayerPlayedCards(List<Card> cardsPlayed, Player thePlayerPlayedCard);

    public abstract void endGame();


    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

		public void setHand(Card card) {
			// TODO Auto-generated method stub
			
		}
}


/*
 * 
 * package player;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import gameController.GameController;

public class Player {
	// TODO: each player needs a unique name or id to play in multiplayer (probably version 2): non-urgent
	private String name = "Ash";
	private ArrayList<Card> hand;
	private int id;
	boolean isDiZhu;
	public Player(){
		hand = new ArrayList<Card>();
	}

	// Takes a hand of cards (Classic gaming where you take cards one by one? Version 3?)
	public void take(List<Card> cards){
		for(Card card: cards)
			hand.add(card);
	}
	public boolean isDiZhu(){
		return isDiZhu;
	}
	// TODO: 
	public void setID(int id){
		this.id=id;
	}
	public int getID(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String toString(){
		return name + ": Gotta catch them all! " + hand.toString();
	}

	public void updateCards(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		for(Card c:cards){
			hand.remove(c);
		}
	}

	public void setGameController(GameController gameController) {
		// TODO Auto-generated method stub
		
	}

	public void setHand(Card card) {
		// TODO Auto-generated method stub
		
	}

	public void yourTurnToPlayCard(Object object) {
		// TODO Auto-generated method stub
		
	}

}
*/
 