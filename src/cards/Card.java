package cards;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {

	public static List<String> cardType = new ArrayList(Arrays.asList("3", "4", "5", "6", "7", "8", "9", "10", "J", "Q" ,"K" ,"A" ,"2"));
	public static List<Integer> cardValues = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));

	public static List<String> cardSuitStr = new ArrayList(Arrays.asList("C", "S" , "D", "H", "JB", "JR"));
	public static List<Suit> cardSuit  = new ArrayList<>(Arrays.asList(Suit.CLUB, Suit.SPADE, Suit.DIAMOND, Suit.HEART, Suit.JOKER_BLACK, Suit.JOKER_RED));


	// Q rather than Queen
	private Suit suit;
	private String type;
	private int value = 0;

	public Card(String shortForm) throws ExCardNoExists {
//		System.out.println("[Debug] : ShortForm " + shortForm);
		if(shortForm.equals("JB") ) {
			this.value = 14;
			this.suit = Suit.JOKER_BLACK;
			return;
		}
		if(shortForm.equals("JR") ) {
			this.value = 15;
			this.suit = Suit.JOKER_RED;
			return;
		}

		// Handle the case with type equals 10
		String number = ""+shortForm.charAt(1) + (shortForm.length() == 3 ? shortForm.charAt(2) :  "");
		String suit = ""+shortForm.charAt(0);

		// Set number
		this.setType(number);

		// Set suit
		int suitIndex = cardSuitStr.indexOf(suit);
		if(suitIndex == -1) throw new ExCardNoExists("Card do not exists. Suit \""+ suit +"\" not exists in card");
		this.suit = cardSuit.get(suitIndex);

	}

	public void setType(String type) throws ExCardNoExists {
		int typeIndex = cardType.indexOf(type);
		if(typeIndex == -1) throw new ExCardNoExists("Card  do not exists. Type \"" + type + "\" not exists in card");
		this.value = cardValues.get(typeIndex);
		this.type = type;
	}

	public String toString(){
		if(this.type == null ) return this.suit.toString();
		return this.suit + this.type;
	}
/*
	public void setValue(int value) {
		this.value = value;
	}*/

	public Integer getValue() {
		return value;
	}
	
}
