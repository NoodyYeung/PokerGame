package player;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

public class Player {
	// TODO: each player needs a unique name or id to play in multiplayer (probably version 2): non-urgent
	private String name = "Ash";
	private ArrayList<Card> hand;
	
	public Player(){
		hand = new ArrayList<Card>();
	}

	// Takes a hand of cards (Classic gaming where you take cards one by one? Version 3?)
	public void take(List<Card> cards){
		for(Card card: cards)
			hand.add(card);
	}
	
	// TODO: 
	
	public String toString(){
		return name + ": Gotta catch them all! " + hand.toString();
	}
}
