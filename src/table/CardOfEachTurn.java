package table;

import cards.Cards;

public class CardOfEachTurn {
	int playerID;
	Cards cards;
	public CardOfEachTurn(int playerID, Cards cards){
		this.playerID=playerID;
		this.cards=cards;
	}
}

