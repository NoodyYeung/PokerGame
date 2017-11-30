package table;

import cards.Cards;

public class CardOfEachRound {
	int playerID;
	Cards cards;
	public CardOfEachRound(int playerID, Cards cards){
		this.playerID=playerID;
		this.cards=cards;
	}
}
