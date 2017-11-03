package table;

import java.util.ArrayList;

import cards.Card;
import cards.Cards;
import player.Player;

public class PlayerAndCards {
	Player player;
	ArrayList<Card> cards;
	public PlayerAndCards(Player player, ArrayList<Card> cards){
		this.player=player;
		this.cards=cards;
	}
	public boolean updateCards(Cards tempCard){
		ArrayList<Card> temp=tempCard.getCards();
		for(Card c: temp){
			cards.remove(c);
		}
		return true;
	}
	public Player getPlayer(){
		return player;
	}
	public boolean isEmpty(){
		if (cards.size()==0) return true;
		return false;
	}
	public String getCardsString(){
		String result="";
		for(Card c:cards){
			result+=c.toString()+" ";
		}
		return result;
	}
	public boolean checkCards(ArrayList<Card> temp) {
		// TODO Auto-generated method stub
		for(Card c:temp){
			if(!cards.contains(c)) return false;
		}
		return true;
	}
}
