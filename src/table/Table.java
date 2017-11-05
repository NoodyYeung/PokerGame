package table;

import cards.Card;
import cards.Cards;
import cards.ExCardNoExists;
import gameController.Player;
import pattern.Pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Random;

public class Table<T extends Player> {
	List <CardOfEachTurn> historyCard;
	List <CardOfEachRound> roundCard;
	List <T> players;
	List <PlayerAndCards> entities;
	Cards lastHandCard;


	int round;
	int turn;
	public Table(List<T> players){
		this.players=players;
		historyCard= new ArrayList<CardOfEachTurn>();
		roundCard=new ArrayList<CardOfEachRound>();
		entities=new ArrayList<PlayerAndCards>();
		round=0;
		turn=0;		
	}



    public boolean addCardToTable(int playerID, Cards cards,int round, int turn){
		this.round=round;
		this.turn=turn;
		
		return addCardToTurn(playerID, cards)&& addCardToRound(playerID, cards)&&updateEntityStatus(playerID, cards);
	}
	

	private boolean updateEntityStatus(int playerID, Cards cards) {
		// TODO Auto-generated method stub
		for(PlayerAndCards e:entities){
			if(e.getPlayer().getId()==playerID) {
				e.updateCards(cards);
			}
		}
		return false;
	}

	private boolean addCardToRound(int playerID, Cards cards) {
		CardOfEachRound temp=new CardOfEachRound(round,playerID,cards);
		roundCard.add(temp);
		return true;
	}
	private boolean addCardToTurn(int playerID, Cards cards) {
		CardOfEachTurn temp=new CardOfEachTurn(turn,playerID,cards);
		historyCard.add(temp);
		return true;		
	}
	
	// add player????
	
	//distribute the cards to the players and create entity for each player	
	//each entity saves the player info and his related cards
	//the entity is saved in the entities list
	//return the distributed cards in string format and the delimiter is " "
	public String distributeCards() throws ExCardNoExists, ExNotEnoughPlayers {
		ArrayList<Card> cards=randomGenerateCards();
		
		if(players == null || players.size()<3)
			throw new ExNotEnoughPlayers("Not enough players at this table! There are only "+players.size()+" players here.");
		
		if(hasDiZhu()){
			Random rand=new Random();
			ArrayList<Card> cardsOnBottom = new ArrayList<Card>();
			for(int i=0; i<3; i++){
				Card c = cards.get(rand.nextInt(53) + 0);
				cardsOnBottom.add(c);
				cards.remove(c);
			}
		}
		
		// TODO hardcoded here assuming 3 players
		// PlayerAndCards immutable card checks:
		ArrayList<ArrayList<Card>> playerAndCards = new ArrayList<ArrayList<Card>>();
		playerAndCards.add(new ArrayList<Card>());
		playerAndCards.add(new ArrayList<Card>());
		playerAndCards.add(new ArrayList<Card>());
		try{
		// Takes card in turn
		int playerCounter = 0;
		while(cards.size()>0){
			// System.out.println(cards.size());
			Card c = cards.get(cards.size()-1);
			players.get(playerCounter).take(c);
			playerAndCards.get(playerCounter).add(cards.get(cards.size()-1));
			cards.remove(cards.size()-1);
			playerCounter++;
			playerCounter = playerCounter%3;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(cards);
		String result="";
		int i=0;
		PlayerAndCards entity;
		for (Player p:players){
			entity=new PlayerAndCards(p,playerAndCards.get(i++));
			entities.add(entity);
			result=result+cards.toString()+" ";
		}
		return result;
	}

	private ArrayList<Card> randomGenerateCards() throws ExCardNoExists {
		// TODO Auto-generated method stub
		ArrayList<Card> wholeDeck=new ArrayList<>();
		for (int i=0;i<13;i++){
			String diamond="D"+ Card.cardType.get(i);
			String spade="S"+Card.cardType.get(i);
			String club="C"+Card.cardType.get(i);
			String heart="H"+Card.cardType.get(i);
			wholeDeck.add(new Card(diamond));
			wholeDeck.add(new Card(spade));
			wholeDeck.add(new Card(club));
			wholeDeck.add(new Card(heart));
		}
		wholeDeck.add(new Card("JR"));
		wholeDeck.add(new Card("JB"));
		Collections.shuffle(wholeDeck);

		return wholeDeck;
	}

	public String getCardsOfEachPlayer() {
		// TODO Auto-generated method stub
		String result="";
		for(PlayerAndCards e:entities){
			result+=e.getCardsString()+",";
		}
		return result;
	}

	public Cards getLastHandCard(){
		return lastHandCard;
	}

	public boolean updateTableInfo(int playerID, ArrayList<Card> cards,Pattern pattern,int round, int turn) {
		// TODO Auto-generated method stub
		lastHandCard=createLastHand(cards,pattern);
		addCardToTable(playerID,lastHandCard,round,turn);
		return true;
	}

	public boolean checkGameEnd(){
		for(PlayerAndCards e:entities){
			if(e.isEmpty()) return true;
		}
		return false;
	}

	public Cards createLastHand(ArrayList<Card> cards, Pattern pattern){
		Cards lastHandCard=new Cards(cards, pattern);
		return lastHandCard;
	}

	public void startANewRound() {
		// TODO Auto-generated method stub
		round++;
	}

	public boolean validateCards(int playerID, ArrayList<Card> cards) {
		for (PlayerAndCards e:entities){
			if(e.getPlayer().getId()==playerID) {
				return e.checkCards(cards);
			}		
		}
		return false;
	}

	public void emptyLastHand() {
		// TODO Auto-generated method stub
		lastHandCard=null;
	}

	public String getGameWinner() {
		// TODO Auto-generated method stub
		for(PlayerAndCards e:entities){
			if(e.isEmpty()) return e.getPlayer().getName();
		}
		return null;
	}
	
	public boolean hasDiZhu() {
		if(players==null) return false;
		for(Player p: players){
			if(p.isDiZhu()) return true;
		}
		return false;
	}
}
//Version1: table should keep the info about the card on the table and 
//what cards each player hold
//More or like a memory