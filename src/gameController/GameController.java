package gameController;

import DDZ.DDZ;
import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import pattern.Pattern;
import table.ExNotEnoughPlayers;
import table.PlayerAndCards;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * GameController should control the interaction with players
 *
 *
 */
public class GameController<T extends Player> {
	private boolean isEnd = false;
	/**
	 * turn is always increasing
	 */
	private int turn = 0;

	/**
	 * Count the number of skip. If the nubmer of skip == 2, it reset to 0 and a new round is started
	 */
	private int numberOfSkip = 0;

	private ArrayList<T> playersInThisGame;
	private TableController tableController;
	private DDZ ddz;
	/** used to identify which user to play. playersInThisGame[initTurn + turn % 3] = the player play card in this turn **/
	private int initTurn = 0;

	//pass in the player object list, and create a table to play and distribute cards
	//the Cards for each player is saved in the table.java
	//before gamecontroller is created, the server should assign id to the player
	public GameController(ArrayList<T> players) throws InsufficientPlayerException {
		if (players.size() != 3) {
			throw new InsufficientPlayerException("Insufficient players. At least three player is needed");
		}
		turn = 0;
		this.playersInThisGame = players;
		this.tableController = new TableController();
		this.ddz = new DDZ();

		int playerId = 0;
		for (Player p : this.playersInThisGame) {
			if(p.getId() != -1)
				p.setId(playerId ++);
			p.setGameController(this);
		}
	}

	/**
	 * TODO 1.Prompt player got cards
	 * TODO 2.ASk Player to call for land lord
	 */
	public void startGame(){
		try {
			tableController.createTableForGame(playersInThisGame);
			newRound();
			Player landLord = getLandLord();
			List<Player> farmers = getFarmer();
			this.initTurn = playersInThisGame.indexOf(landLord);
			// Tell the player what you are
			landLord.youAreLandLord();
			farmers.get(0).youAreFarmer(farmers.get(1));
			farmers.get(1).youAreFarmer(farmers.get(0));

			// Tell what player you need to do
			Pattern pattern = null;
			boolean isLandLordToPlayCard = true;
			for (Player f : farmers)
				f.waitPlayerToPlayerCard(landLord, isLandLordToPlayCard);

			List<Card> playedCard = null;
			do {
				try {
//					System.out.println("[DEBUG] tried loop here 1:");
					List<Card> hands = tableController.getCardsByPlayer(landLord);
					playedCard = landLord.yourTurnToPlayCard(null, hands);
					if(playedCard!= null && !checkPlayedCardInsideHands(playedCard, hands)){
						throw new ExPlayedCardNotInYourHands();
					}
					pattern = ddz.validateDDZ(playedCard, null);

				}catch(ExPlayedCardNotInYourHands e){
					landLord.pleaseMakeAValidPlay();
				}
			}while(pattern == null);
			tableController.setLastHandCard(playedCard);
			tableController.reduceUserPlayedCard(landLord, playedCard);
			nextTurn();

		} catch (ExCardNoExists exCardNoExists) {
			exCardNoExists.printStackTrace();
		} catch (ExNotEnoughPlayers e) {
			System.out.println("From game controller" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Called when the turn is finish
	 */
	public void nextTurn(){
		try{
//			System.out.println("[Debug] : next turn : " + (turn + 1) );
			if(tableController.checkGameEnd()){
				gameEnd();
				return;
			}
			turn ++;
			Player currentPlayCardPlayer =getCurrentTurnPlayer();
			List<Player> otherPlayers = getNotCurrentTurnPlayer();

			/** Tell the waiting user something **/
			for(Player i : otherPlayers)
				i.waitPlayerToPlayerCard(currentPlayCardPlayer, this.isCurrentTurnPlayerLandLoad());

			/** Ask for card play **/
			List<Card> playedCard = null;
			while(true) {
				try {
					List<Card> hands = tableController.getCardsByPlayer(currentPlayCardPlayer);
					List<String> cardCountInfo = new ArrayList<>();
					for( Player p : playersInThisGame){
						PlayerAndCards tc = tableController.getPlayerAndCardsObjByPlayer(p);
						cardCountInfo.add(String.format("[%s] %s: %d card left", tc.isLandLord() ? "Landlord" : "Farmer",
								p.getName(), tc.getCards().size()));
					}
					currentPlayCardPlayer.playerCardCountInfo(cardCountInfo);
					playedCard = currentPlayCardPlayer.yourTurnToPlayCardOrSkipCard(hands, tableController.getLastHandCard());
					if(playedCard!=null && !checkPlayedCardInsideHands(playedCard, hands)){
						throw new ExPlayedCardNotInYourHands();
					}
					if (playedCard == null) { // the user choose to skip
						break;
						// List<Card> lastHands = tableController.getLastHandCard() != null ? tableController.getLastHandCard().getCards() : null;
						// System.out.println("[DEBUG]: GOT Pattern FAIL " + Cards.toString(lastHands));
					}
					Pattern pattern = ddz.validateDDZ(playedCard, tableController.getLastHandCard());
					
					if(pattern == null) {
						info("Incorrect pattern! Please play a valid hand of cards.");
						break;
					}
					if (pattern != null) {
//						System.out.println("[DEBUG] : GOT Pattern " + pattern.getClass().getName());
						// exit when user input valid pattern
						break;
					}
				}catch (ExPlayedCardNotInYourHands e){
					currentPlayCardPlayer.pleaseMakeAValidPlay();
				}
			}
			if(playedCard != null){ // player plays some card
				tableController.setLastHandCard(playedCard);
				tableController.reduceUserPlayedCard(currentPlayCardPlayer, playedCard);
				numberOfSkip = 0;
			}else{
				numberOfSkip ++;
				if(numberOfSkip == 2){
					newRound();
				}
			}
			/** End of ask for card play **/
			nextTurn();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param played
	 * @param hands
	 * @return True if plays in hands
	 */
	public boolean checkPlayedCardInsideHands(List<Card> played, List<Card> hands){
		for(Card p : played){
			boolean found = false ;
			for(Card h : hands) {
				if (p.equals(h)) {
					found = true;
					break;
				}
			}
			if(!found){
				return false;
			}
		}
		return true;
	}

	/**
	 * Called when game end
	 */
	public void gameEnd(){
		try{
			Player winner = tableController.getGameWinner();
			List<Player> otherThanWinner = getPlayersOtherThanInputPlayer(winner);
			boolean isWinnerLandLord = 	tableController.getPlayerAndCardsObjByPlayer(winner).isLandLord();
			winner.youWin();
			if(isWinnerLandLord) {
				for (Player p : otherThanWinner) {
					p.youLose();
				}
			}else{
				for(Player p: otherThanWinner){
					boolean isFarmer = !tableController.getPlayerAndCardsObjByPlayer(p).isLandLord();
					if(isFarmer){
						p.youWin();
					}else{
						p.youLose();
					}
				}
			}
			isEnd = true;

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public List<Player> getPlayersOtherThanInputPlayer(Player p ) throws Exception {
		List<Player> players = new ArrayList<>();
		for(Player p1 : playersInThisGame){
			if(p.equals(p1))
				continue;
			players.add(p1);
		}
		if(players.size() >= 3 && p != null){
			throw new Exception("Logic error: the function only can return two player max");
		}
		return players;
	}



	/**
	 * Only trigger on the numberOfskip == 2
	 */
	public void newRound(){
//		System.out.println("[Debug] : new round");
		numberOfSkip = 0;
		tableController.emptyLastHand();
	}

	public boolean isCurrentTurnPlayerLandLoad() throws Exception {
		PlayerAndCards playerAndCards = tableController.getPlayerAndCardsObjByPlayer(getCurrentTurnPlayer());
		if(playerAndCards == null){
			throw new Exception("Logic Error: playerAndCard should nerver be null");
		}
		return playerAndCards.isLandLord();
	}

	/**
	 *
	 * @return The Player the can play in the current turn
	 */
	public Player getCurrentTurnPlayer(){
		return playersInThisGame.get((turn + initTurn) % 3);
	}

	/**
	 *
	 * @return The Player cannot have any action in this turn
	 */
	public List<Player> getNotCurrentTurnPlayer(){
		List<Player> players = new ArrayList<>();
		for(int i = 1; i <= 2; i ++){
			players.add( playersInThisGame.get((turn + initTurn + i) % 3 ));
		}
		return  players;
	}


	public Player getLandLord(){
		List<PlayerAndCards> cardsList = tableController.getTablePlayerCards();
//		System.out.println("[Debug] : " + cardsList.size());
		for(int i =0; i < cardsList.size(); i ++){
			PlayerAndCards playerAndCards = cardsList.get(i);
			if(playerAndCards.isLandLord())
				return playerAndCards.getPlayer();
		}
		return null;
	}

	public List<Player> getFarmer() throws Exception {
		List<Player> farmers = new ArrayList<>();
		List<PlayerAndCards> cardsList = tableController.getTablePlayerCards();
		for(int i =0; i < cardsList.size(); i ++){
			PlayerAndCards playerAndCards = cardsList.get(i);
			if(!playerAndCards.isLandLord())
				farmers.add(playerAndCards.getPlayer());
		}
		if(farmers.size() < 2){
			throw new Exception("Logic error: Farmer should never less than 2");
		}
		return farmers;
	}





	public boolean getIsEnd() {
		return isEnd;
	}
	
	private void info(String message) {
		// give info to user
		System.out.println(message);
	}
}
