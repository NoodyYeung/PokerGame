package cs3343.group8.table;

import cs3343.group8.DDZ.*;

// Each table has 4 players and a deck: for testing, it only has 1 player: (54-6)/4 = 12

public class Table {
	private Deck deck;
	// supposed to have 4 players, but just 1 now
	private Player me;
	public Table(Deck d) {
		System.out.println("Table.java initiating table");
		
		// Hack-like implementation: please expand on this (Priority 1)
		// Design question: should we let Table control player actions, or let the specific command control it?
		me = new Player();
		this.deck = d;
		me.take(deck.distribute());
		System.out.println(me);
	}
}
