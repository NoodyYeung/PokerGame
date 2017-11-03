package table;

import java.util.ArrayList;

import cards.Card;
import cards.Cards;

public class CardOfEachTurn {
	int round;
	int playerID;
	Cards cards;
	public CardOfEachTurn(int round,int playerID, Cards cards){
		this.round=round;
		this.playerID=playerID;
		this.cards=cards;
	}
}

