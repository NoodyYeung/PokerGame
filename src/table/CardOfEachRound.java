package table;

import cards.Cards;

public class CardOfEachRound {
	int round;
	int playerID;
	Cards cards;
	public CardOfEachRound(int round,int playerID, Cards cards){
		this.round=round;
		this.playerID=playerID;
		this.cards=cards;
	}
}