package cards;

public class SuitCard extends Card {
	private Suit suit;
	
	
	public SuitCard(String shortForm, Suit suit, int value) throws ExCardNoExists {
		super(shortForm);
		this.suit = suit;
	}

	public SuitCard(String shortForm) throws ExCardNoExists {
		super(shortForm);
	}

	public String toString(){
		return super.toString()+" "+suit;
		// return super.toString()+" of "+ (suit).toString().toLowerCase() + "s";
	}

}
