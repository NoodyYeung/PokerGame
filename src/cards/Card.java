package cards;


public class Card {
	// Q rather than Queen
	private String shortDescription;
	//each card should have a value according to the rules
	private int value = 0;
	

	public Card(String shortForm) {
		this.shortDescription = shortForm;
	}
	
	public Card(String shortForm, int value) {
		this(shortForm);
		this.value = value;
	}
	
	public String toString(){
		return this.shortDescription;
	}
/*
	public void setValue(int value) {
		this.value = value;
	}*/
	
}
