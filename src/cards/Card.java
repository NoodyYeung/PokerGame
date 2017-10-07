package cards;

public class Card {
	// Q rather than Queen
	private String shortDescription;
	
	public Card(String shortForm) {
		this.shortDescription = shortForm;
	}
	
	public String toString(){
		return this.shortDescription;
	}
}
