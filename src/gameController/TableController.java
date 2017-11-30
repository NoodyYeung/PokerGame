package gameController;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import pattern.Pattern;
import table.ExNotEnoughPlayers;
import table.PlayerAndCards;
import table.Table;

import java.util.ArrayList;
import java.util.List;

public class TableController {

	Table table;
	public  <T extends Player> String createTableForGame(List<T> players) throws ExCardNoExists, ExNotEnoughPlayers{
		table=new Table(players);
		return table.distributeCards();
	}

	public boolean checkGameEnd(){
		return table.checkGameEnd();
	}

	//return the card info in string format
	//the delimiter is ","
	//the sequence is based on the increasing player id
	public String getCardsInfo() {
		// TODO Auto-generated method stub
		return table.getCardsOfEachPlayer();
	}

	public Cards getLastHandCard(){
		return table.getLastHandCard();
	}

	public boolean updateTableInfo(int playerID, ArrayList<Card> cards, Pattern pattern){
		return table.updateTableInfo(playerID, cards,pattern);
	}

	public void emptyLastHand(){
		table.emptyLastHand();
	}


	public boolean validateCards(int playerID, ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return table.validateCards(playerID,cards);
	}
	public Player getGameWinner() {
		// TODO Auto-generated method stub
		return table.getGameWinner();
	}

	public List getTablePlayerCards() {
		return table.getPlayerCardsEntities();
	}

	public List<Card> getCardsByPlayer(Player player){
		List<PlayerAndCards> playerAndCardss = table.getPlayerCardsEntities();
		for(PlayerAndCards i : playerAndCardss ){
			if(i.equalsPlayer(player)){
				return i.getCards();
			}
		}
		return null;
	}

	public void setLastHandCard(List<Card> playedAndValidCard){
		table.setLastHand(playedAndValidCard);
	}


	public PlayerAndCards getPlayerAndCardsObjByPlayer(Player p){
		List<PlayerAndCards> playerAndCardss = table.getPlayerCardsEntities();
		for(PlayerAndCards i : playerAndCardss ){
			if(i.equalsPlayer(p)){
				return i;
			}
		}
		return  null;
	}
	public void  reduceUserPlayedCard(Player player, List<Card> playedCard){
		PlayerAndCards playerAndCards =  getPlayerAndCardsObjByPlayer(player);
		table.reduceTheCardFromPlayer( player, playedCard);
		System.out.println("[DEBUG] : Card after delete : " + Cards.toString(		getCardsByPlayer(player)));

	}

}
