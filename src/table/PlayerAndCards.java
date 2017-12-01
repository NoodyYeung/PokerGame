package table;

import cards.Card;
import cards.Cards;
import gameController.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerAndCards {
	Player player;
	ArrayList<Card> cards; // The card left in user hand
	ArrayList<Card> usedCard;
	boolean isLandLord = false;

	public PlayerAndCards(Player player, ArrayList<Card> cards){
		this.player=player;
		this.cards=cards;
		this.usedCard = new ArrayList<>();
		System.out.println("Debug : "+ cards.size());
		if(cards.size() == 20){
			isLandLord = true;
		}
	}
	public boolean updateCards(Cards tempCard){
		List<Card> temp=tempCard.getCards();
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

	public boolean equalsPlayer(Player player){
		return this.player.equals(player);
	}
	public boolean checkCards(ArrayList<Card> temp) {
		// TODO Auto-generated method stub
		for(Card c:temp){
			if(!cards.contains(c)) return false;
		}
		return true;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public boolean isLandLord() {
		return isLandLord;
	}

	public List<Card> getUsedCards() {
		return usedCard;
	}
}
