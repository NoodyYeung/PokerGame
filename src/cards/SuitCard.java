package cards;

public class SuitCard extends Card {
	private Suit suit;
	
	
	public SuitCard(String shortForm, Suit suit, int value) {
		super(shortForm, value);
		this.suit = suit;
	}
	
	public String toString(){
		return super.toString()+" "+suit;
		// return super.toString()+" of "+ (suit).toString().toLowerCase() + "s";
	}

}
