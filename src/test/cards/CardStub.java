package test.cards;

import cards.Suit;

public class CardStub {
	
	private Suit suit;
	private int value;
	
	public void setValue(int val) {
		this.value = val;
	}
	
	/**
	 * 
	 * @return int value
	 */
	public int getValue() {
		if(this.suit.equals(Suit.JOKER_RED) || suit.equals(Suit.JOKER_BLACK))
			return value;
		else{
			int minus = 0;
			switch (suit){
				case CLUB:
					minus = 3;
					break;
				case DIAMOND:
					minus = 4;
					break;
				case HEART:
					minus = 2;
					break;
				case SPADE:
					minus = 1;
					break;
			}

			return (value * 4) - minus;
		}
	}

}
