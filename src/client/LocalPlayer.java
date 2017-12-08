package client;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import gameController.Player;

import java.util.Comparator;
import java.util.List;

public class LocalPlayer extends Player {
	// TODO: each player needs a unique name or id to play in multiplayer (probably version 2): non-urgent

	public LocalPlayer(){
		id = playerIdForLocalAndAI++;
	}
	public Comparator<Card> cardCpm = new Comparator<Card>() {
		@Override
		public int compare(Card o1, Card o2) {
			return o1.getValue() - o2.getValue();
		}
	};


	public String toString(){
		return name + ": Gotta catch them all! ";
	}


	/**
	 * User will input the card after this function is called
	 * @param lastTurnCards The cards from last turn player
	 * @param tablePlayerCards
	 */
	@Override
	public List<Card> yourTurnToPlayCard(Cards lastTurnCards, List<Card> tablePlayerCards) {
		if(lastTurnCards != null)
			System.out.println("Last player cards : " + Cards.toStringWithSymbol(lastTurnCards.getCards()) );
		else
			System.out.println("No last player cards. You are free to put cards on the table");

		tablePlayerCards.sort(cardCpm);
		System.out.println("Card in your hand : " + Cards.toStringWithSymbol(tablePlayerCards));
		while(true) {
			System.out.println("Please put the cards: (Example: \"D1 S1\")");
			String cardStr = Main.systemIn.nextLine();
			try {
				List<Card> card = Cards.createCardsListFromString(cardStr);
				/** TO-DO :: playCard() here */
				return card;
			} catch (ExCardNoExists exCardNoExists) {
				System.out.println("Invalid Cards Combination");
//				exCardNoExists.printStackTrace();
			}
		}
	}
// Not used now
//	/**
//	 * If noone choose to be a landlord. You can choose to be it
//	 */
//	@Override
//	public void yourTurnToChooseToBeALandLord() {
//
//	}
//
//	/**
//	 * After someone choose to be a land lord. You can steal the position!
//	 */
//	@Override
//	public void yourTurnToGrapLandLord() {
//
//	}
//
//	@Override
//	public void informYouPlayerChooseToBeALandLord(Player theOneCalledToBeLandLord) {
//
//	}
//
//	@Override
//	public void informYouPlayerGrapToBeALandLord(Player theOneGrapedToBeLandLord) {
//
//	}
//
//	@Override
//	public void informPlayerPlayedCards(List<Card> cardsPlayed, Player thePlayerPlayedCard) {
//
//	}
//	@Override
//	public void endGame() {
//
//	}


	@Override
	public void waitPlayerToPlayerCard(Player playerToPlay, boolean isLandLordToPlayCard) {
		System.out.printf("Player %d -- Name %s is choosing cards to play !!\n", playerToPlay.getId(), playerToPlay.getName());
	}

	@Override
	public void youAreFarmer(Player teammates) {
		System.out.printf("You are farmer. Your teammate is Player %d -- Name %s \n", teammates.getId(), teammates.getName());
	}

	@Override
	public void youAreLandLord() {
		System.out.printf("You are landLoad ! \n ");
	}

	@Override
	public List<Card> yourTurnToPlayCardOrSkipCard(List<Card> cardsThatThePlayerHave,  Cards lastTurnCards) {
		if(lastTurnCards != null)
			System.out.println("Last player cards : " + Cards.toStringWithSymbol(lastTurnCards.getCards()) );
		else
			System.out.println("No last player cards. You are free to put cards on the table");
		cardsThatThePlayerHave.sort(cardCpm);
		System.out.println("Card in your hand : " + Cards.toStringWithSymbol(cardsThatThePlayerHave) );
		while(true) {
			System.out.println("Please put the cards: (Example: \"D1 S1\") OR input SKIP to skip:");
			String cardStr = Main.systemIn.nextLine();
			if(cardStr.equals("SKIP"))
				break;
			try {
				List<Card> card = Cards.createCardsListFromString(cardStr);
				/** TO-DO :: playCard() here */
				return card;
			} catch (ExCardNoExists exCardNoExists) {
				System.out.println("Invalid Cards Combination");
//				exCardNoExists.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void youWin() {
		System.out.printf("You win\n");
	}

	@Override
	public void youLose() {
		System.out.printf("You lose\n");

	}

	@Override
	public void pleaseMakeAValidPlay() {
		System.out.println("Please make a valid play");
	}

	@Override
	public void playerCardCountInfo(List<String> playersCountsInfo) {
		for(String s : playersCountsInfo){
			System.out.println(s);
		}
	}
}
