package gameController;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import pattern.Pattern;

import java.util.ArrayList;


/**
 *
 * GameController should control the interaction with players
 *
 *
 */
public class GameController {
    /**
     * 0, 1, 2 for indicating the user turn
     */
    private int turn = 0;
  	int round=0;

    private ArrayList<Player> playersInThisGame;
    private Deck deck;
    private boolean isGameInit = false;
  	ArrayList<Integer> playerSequence=new ArrayList<>();
  	
  	//pass in the player object list, and create a table to play and distribute cards
  	//the Cards for each player is saved in the table.java
  	//before gamecontroller is created, the server should assign id to the player
  	public GameController(ArrayList<Player> players) throws InsufficientPlayerException {
		if (players.size() != 3) {
			throw new InsufficientPlayerException("Insufficient players. At least three player is needed");
		}
		round = 0;
		turn = 0;
		this.playersInThisGame = players;

		int playerId = 0;
		for (Player p : this.playersInThisGame) {
		    if(p.getId() != -1)
		        p.setId(playerId ++);
			p.setGameController(this);
		}
	}

	/**
	 * Noody: This is not my code.
	 * The main main different i thought is that
	 *
    public void startGame(){
        List<Card>[] decks = deck.distribute();
        for(int i = 0; i < playersInThisGame.size(); i ++) {
            Player player = playersInThisGame.get(i);
            player.setHand(decks[i]);
        TableController.createTableForGame(players);

    }
    **/

    /**
     * TODO 1.Prompt player got cards
     * TODO 2.ASk Player to call for land lord
     */
    public void startGame(){
		try {
			TableController.createTableForGame(playersInThisGame);
		} catch (ExCardNoExists exCardNoExists) {
			exCardNoExists.printStackTrace();
		}

	}


  	public boolean checkGameEnd(){
  		return TableController.checkGameEnd();
  	}

  	/**
     *  return the card info in string format
        the delimiter is ","
        the sequence is based on the increasing player id
     */
  	public String getCardsOfEachPlayer(){
  		return TableController.getCardsInfo();
  	}

  	//based on the input string by the player create the list of card objects
  	public ArrayList<Card> createThisHandOfCards(String input) throws ExCardNoExists{
  		return Cards.createCardsListFromString(input);
  	}

  	//when the player has cards to play
  	//then pass the following validation
  	//will automatically update table info
  	public String cardsValidation(int playerID, String cards) throws ExCardNoExists{
  		ArrayList<Card> thisHandOfCards=createThisHandOfCards(cards);
  		//check if the player has those cards
  		boolean isExist=TableController.validateCards(playerID,thisHandOfCards);
  		if(isExist){
  			Cards lastHand=TableController.getLastHandCard();
  			//validateDDZ need to check if the lastHand is null
  			Pattern pattern=validateDDZ(thisHandOfCards,lastHand);
  			if(pattern!=null){
  				//put this hand of cards to the table and update round and turn info
  				//also remove the cards from the player
  				updateTheTable(playerID, thisHandOfCards,pattern);
  				playerSequence.add(playerID);
  				turn++;
  			}
  		}
  		if(checkGameEnd()) {
  			return getGameWinner();
  		} else return null;
  	}

  	private String getGameWinner() {
  		// TODO Auto-generated method stub
  		return TableController.getGameWinner();
  	}

  	private Pattern validateDDZ(ArrayList<Card> thisHandOfCards, Cards lastHand) {
  		// TODO Auto-generated method stub
  		return null;
  	}
  	
    public void playCard(Player player){

    }

    public void callForLandLord(Player player){
        player.yourTurnToChooseToBeALandLord();
    }

    public void grapForLandLord(Player player){

    }

    public void skipToPlayCard(Player player){}



	//when the player has no cards to play
	public boolean skipTheCurrentPlayer(int playerID){
		if(playerID==playerSequence.get(playerSequence.size()-1)){
			round++;
			TableController.startANewRound();
			TableController.emptyLastHand();
		}
		return true;
	}

	//when the player have cards to play
	//if validation is passed, then call this function to 
	//increase the turn and update the info in the table.java
	//the parameter needed is the player id and cards 
	public boolean updateTheTable(int playerID, ArrayList<Card> cards,Pattern pattern){
		TableController.updateTableInfo(playerID, cards, pattern, round,turn);
		return true;
	}
	
}
