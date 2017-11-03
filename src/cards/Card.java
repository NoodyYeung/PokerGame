package cards;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {

	public static List<String> cardType = new ArrayList(Arrays.asList("3", "4", "5", "6", "7", "8", "9", "10", "J", "Q" ,"K" ,"A" ,"2", "B", "R"));
	public static List<Integer> cardValues = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

	public static List<String> cardSuitStr = new ArrayList(Arrays.asList("C", "S" , "D", "H", "J"));
	public static List<Suit> cardSuit  = new ArrayList<>(Arrays.asList(Suit.CLUB, Suit.SPADE, Suit.DIAMOND, Suit.HEART, Suit.JOKER));


	// Q rather than Queen
	private Suit suit;
	private String number;
	private int value = 0;

	public Card(String shortForm) throws ExCardNoExists {
		if(shortForm.equals("JB") || shortForm.equals("JR")) {
			int index = cardType.indexOf(shortForm); // Checking for JR AND JB
			this.value = cardValues.get(index);
			this.suit = Suit.JOKER;
		}

		String number = ""+shortForm.charAt(1) + (shortForm.length() == 3 ? shortForm.charAt(2) :  "");
		String suit = ""+shortForm.charAt(0);

		// Set number
		this.setNumber(number);

		// Set suit
		int suitIndex = cardSuitStr.indexOf(suit);
		if(suitIndex == -1) throw new ExCardNoExists("Card do not exists. Suit not exists in card");
		this.suit = cardSuit.get(suitIndex);

	}

	public Card(Suit suit, String number) throws ExCardNoExists {
		this.suit = suit;
		this.setNumber(number);
	}

	public void setNumber(String number) throws ExCardNoExists {
		int typeIndex = cardType.indexOf(number);
		if(typeIndex == -1) throw new ExCardNoExists("Card do not exists. Number not exists in card");
		this.value = cardValues.get(typeIndex);
		this.number = number;
	}

	public String toString(){
		return this.suit + this.number;
	}
/*
	public void setValue(int value) {
		this.value = value;
	}*/

	public Integer getValue() {
		return value;
	}
	
}
