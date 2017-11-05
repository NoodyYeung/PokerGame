package gameController;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import pattern.Pattern;
import table.ExNotEnoughPlayers;
import table.Table;

import java.util.ArrayList;
import java.util.List;

public class TableController {
	static Table table;


	public static  <T extends Player> String createTableForGame(List<T> players) throws ExCardNoExists, ExNotEnoughPlayers{
		table=new Table(players);		
		return table.distributeCards();
	}

	public static boolean checkGameEnd(){
		return table.checkGameEnd();
	}

	//return the card info in string format
	//the delimiter is ","
	//the sequence is based on the increasing player id
	public static String getCardsInfo() {
		// TODO Auto-generated method stub
		return table.getCardsOfEachPlayer();
	}

	public static Cards getLastHandCard(){
		return table.getLastHandCard();
	}

	public static boolean updateTableInfo(int playerID, ArrayList<Card> cards, Pattern pattern, int round, int turn){
		return table.updateTableInfo(playerID, cards,pattern,round,turn);
	}

	public static void emptyLastHand(){
		table.emptyLastHand();
	}

	public static void startANewRound() {
		// TODO Auto-generated method stub
		table.startANewRound();
	}

	public static boolean validateCards(int playerID, ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return table.validateCards(playerID,cards);
	}
	public static String getGameWinner() {
		// TODO Auto-generated method stub
		return table.getGameWinner();
	}
}
