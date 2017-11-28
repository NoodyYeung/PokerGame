package gameController;


import cards.*;
import cards.Number;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

// with a deck reader
public class Deck {
	private List<Card> deck = new ArrayList<Card>();
	private List<Card> usedCard = new ArrayList<Card>();

	public Deck() {
		// Creates one deck of cards
		try {
			createDeck();
		} catch (FileNotFoundException e) {
			ErrorHandling.handle("Deck of cards not found!", e);
		} catch (IOException e) {
			ErrorHandling.handle(
					"Please double check the settings. Format: 3 lines, 1 line for definition, 1 for cards, 1 for values", e);
		}  catch (ExCardNoExists exCardNoExists) {
			exCardNoExists.printStackTrace();
		}catch (Exception e) {
			ErrorHandling.handle("Exception occured.", e);
		}
	}

	// input assumes a certain format: if format is not reached, throw a general
	// exception
	private void createDeck() throws Exception {
		for(Suit s : Suit.values()){
			if(s == Suit.JOKER_BLACK){
				deck.add(new Card( "JB"));

			}else if(s==Suit.JOKER_RED){
				deck.add(new Card( "JR"));
			}else{
				for(int i =0; i < 13 ;i ++ ){
					deck.add(new Card(s.toString() +  Card.cardType.get(i)));
				}
			}
		}
	} // end createDeck

	// identifies cards: is this a suit or a number?
	private Card identifyCard(String card, Suit suit, int value) throws ExCardNoExists {
		// Regular expression: card is J or Q or K
		if (Pattern.matches("[JQK]", card)) {
			return new Royal(card, suit, value);
		} else if (Pattern.matches("[A23456789]", card) || Pattern.matches("([1])([0])", card))
			return new Number(card, suit, value);
		else
			return null;
	}

	public void printDeck() {
		for (Card c : deck) {
			System.out.print(c + " ");
		}
	}

	// Distribute all cards at once
	// want to distribute one card at once? Classic playstyle?
	public List<Card>[] distribute() {
		Collections.shuffle(deck);
		List<Card>[] cards = new ArrayList[3];

		for(int j = 0; j < 3; j ++) {
			ArrayList<Card> hand = new ArrayList<>();
			for (int i = 0; i < 17; i++) {
 				hand.add(deck.remove(0));
			}
			cards[j] = hand;

		}
		return cards;
	}
}
