package client;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import gameController.Player;

import java.util.ArrayList;
import java.util.List;

public class LocalPlayer extends Player {
	// TODO: each player needs a unique name or id to play in multiplayer (probably version 2): non-urgent

	public LocalPlayer(){
		hand = new ArrayList<Card>();
	}

	// Takes a hand of cards (Classic gaming where you take cards one by one? Version 3?)
	public void take(List<Card> cards){
		for(Card card: cards)
			hand.add(card);
	}

	public String toString(){
		return name + ": Gotta catch them all! " + hand.toString();
	}

	@Override
	public void setHand(List<Card> hand) {
		this.hand = hand;
		System.out.print("You got the following card : \n");
		String cardStr = "";
		for(int i =0; i < hand.size(); i ++){
			Card c = hand.get(i);
			cardStr += c + " ";
		}
		System.out.println(cardStr );
	}

	/**
	 * User will input the card after this function is called
	 * @param lastTurnCards The cards from last turn player
	 */
	@Override
	public void yourTurnToPlayCard(List<Card> lastTurnCards) {
		if(lastTurnCards != null)
			System.out.println("Last player cards : " + Cards.toString(lastTurnCards) );
		else
			System.out.println("No last player cards. You are free to put cards on the table");
		System.out.println("Card in your hand : " + Cards.toString(hand) );
		while(true) {
			System.out.println("Please put the cards: (Example: \"D1 S1\")");
			String cardStr = Main.systemIn.nextLine();
			try {
				List<Card> card = Cards.createCardsListFromString(cardStr);
				/** TO-DO :: playCard() here */
//				this.playCard(card);
				break;
			} catch (ExCardNoExists exCardNoExists) {
				System.out.println("Invalid Cards Combination");
				exCardNoExists.printStackTrace();
			}
		}
	}

	/**
	 * If noone choose to be a landlord. You can choose to be it
	 */
	@Override
	public void yourTurnToChooseToBeALandLord() {

	}

	/**
	 * After someone choose to be a land lord. You can steal the position!
	 */
	@Override
	public void yourTurnToGrapLandLord() {

	}

	@Override
	public void informYouPlayerChooseToBeALandLord(Player theOneCalledToBeLandLord) {

	}

	@Override
	public void informYouPlayerGrapToBeALandLord(Player theOneGrapedToBeLandLord) {

	}

	@Override
	public void informPlayerPlayedCards(List<Card> cardsPlayed, Player thePlayerPlayedCard) {

	}

	@Override
	public void endGame() {

	}
}
