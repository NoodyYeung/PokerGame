package player;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

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
}
