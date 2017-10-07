package cs3343.group8.table;

import java.util.ArrayList;

import cards.Card;

public class Player {
	private ArrayList<Card> hand;
	public Player(){
		hand = new ArrayList<Card>();
	}

	public void take(Card c){
		hand.add(c);
	}
	
	public String toString(){
		return hand.toString();
	}
}
