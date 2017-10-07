package cs3343.group8.table;

import cs3343.group8.DDZ.*;

// Each table has 4 players and a deck: for testing, it only has 1 player: (54-6)/4 = 12

public class Table {
	private Deck deck;
	// supposed to have 4 players, but just 1 now
	private Player me;
	public Table(Deck d) {
		me = new Player();
		this.deck = d;
		deck.distributeToMe(me);
		
		System.out.println("hi");
		System.out.println(me);
	}
}
