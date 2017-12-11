package table;

import cards.Card;
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
//		System.out.println("Debug : "+ cards.size());
		if(cards.size() == 20){
			isLandLord = true;
		}
	}

	public Player getPlayer(){
		return player;
	}
	public boolean isEmpty(){
		if (cards.size()==0) return true;
		return false;
	}

	public boolean equalsPlayer(Player player){
		return this.player.equals(player);
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
