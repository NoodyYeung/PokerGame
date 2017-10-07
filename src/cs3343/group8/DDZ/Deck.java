package cs3343.group8.DDZ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import cards.*;
import cards.Number;
import cs3343.group8.table.Player;

// with a deck reader
public class Deck {
	private List<Card> deck = new ArrayList<Card>();

	public Deck() {
		// Creates one deck of cards
		try {
			createDeck();
		} catch (FileNotFoundException e) {
			ErrorHandling.handle("Deck of cards not found!", e);
		} catch (IOException e) {
			ErrorHandling.handle(
					"Please double check the settings. Format: 3 lines, 1 line for definition, 1 for cards, 1 for values", e);
		} catch (Exception e) {
			ErrorHandling.handle("Exception occured.", e);
		}
	}

	// input assumes a certain format: if format is not reached, throw a general
	// exception
	private void createDeck() throws FileNotFoundException, IOException, Exception {
		Scanner input = null;
		try {
			// four suits: put
			// none: without suit values

			input = new Scanner(new File("src\\cs3343\\group8\\DDZ\\cardpool.txt"));
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String cardsLine = input.nextLine();
				String valueLine = input.nextLine();

				String[] cards = cardsLine.split(" ");
				String[] value = valueLine.split(" ");

				// Add all 4 suits to each card; 'switch' is as if String.equals was
				switch (line) {
				case "four suits":
					for (int i = 0; i < cards.length; i++)
						for (Suit suit : Suit.values()) {
							Card newCard = identifyCard(cards[i], suit, Integer.parseInt(value[i], 10));
							if (newCard == null)
								throw new Exception("Unidentified card");
							deck.add(newCard);
						}
					break;
				case "none":
					for (int i = 0; i < cards.length; i++) {
						deck.add(new Card(cards[i], Integer.parseInt(value[i], 10)));
					}
					break;
				default:
					System.out.println("Deck wrong input eh");
					break;
				}
			} //end while

			System.out.println(deck.size());
			
		} catch (Exception e) {
			throw e;
		} finally {
			input.close();
		}
	} // end createDeck

	// identifies cards with a suit
	private Card identifyCard(String card, Suit suit, int value) {
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

	public void distributeToMe(Player p) {
		Collections.shuffle(deck);
		for(int i=0; i<12; i++)
			p.take(deck.remove(0));
	}
}
