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
// Each table has 4 players and a deck: for testing, it only has 1 player: (54-6)/4 = 12

public class Table {
//	private Deck deck;
//	// supposed to have 4 players, but just 1 now
//	private Player me;
//	public Table(Deck d) {
//		System.out.println("Table.java initiating table");
//		
//		// Hack-like implementation: please expand on this (Priority 1)
//		// Design question: should we let Table control player actions, or let the specific command control it?
//		me = new Player();
//		this.deck = d;
//		me.take(deck.distribute());
//		System.out.println(me);
//	}

	List <CardOfEachTurn> historyCard;
	List <CardOfEachRound> roundCard;
	List <Player> players;
	List <PlayerAndCards> entities;
	Cards lastHandCard;


	int round;
	int turn;
	public Table(List<Player> players){
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
	//distribute the cards to the players and create entity for each player	
	//each entity saves the player info and his related cards
	//the entity is saved in the entities list
	//return the distributed cards in string format and the delimiter is " "
	public String distributeCards() throws ExCardNoExists {
		// TODO Auto-generated method stub
		ArrayList<ArrayList <Card>> cards=randomGenerateCards();
		String result="";
		int i=1;
		PlayerAndCards entity;
		for (Player p:players){
			if(p.isDiZhu()) {
				entity=new PlayerAndCards(p,cards.get(0));	
			}
			else{
				entity=new PlayerAndCards(p,cards.get(i++));	
			}
			entities.add(entity);
			result=result+cards.toString()+" ";			
		}
		return result;
	}

	private ArrayList<ArrayList<Card>> randomGenerateCards() throws ExCardNoExists {
		// TODO Auto-generated method stub
		List<Card> wholeDeck=new ArrayList<>();
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
		ArrayList<Card> player1=new ArrayList<>();
		ArrayList<Card> player2=new ArrayList<>();
		ArrayList<Card> player3=new ArrayList<>();
		//have dizhu
		Random rand=new Random();
		int n = rand.nextInt(53) + 0;
		player1.add(wholeDeck.get(n));
		n = rand.nextInt(53) + 0;
		player1.add(wholeDeck.get(n));
		n = rand.nextInt(53) + 0;
		player1.add(wholeDeck.get(n));
		//no dizhu
		
		ArrayList<ArrayList<Card>> result=new ArrayList<>();
		for(int i=0;i<wholeDeck.size();i++){
			if(i%3==0) player1.add(wholeDeck.get(i));
			if(i%3==1) player2.add(wholeDeck.get(i));
			if(i%3==2) player3.add(wholeDeck.get(i));
		}
		result.add(player1);
		result.add(player2);
		result.add(player3);
		return result;
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
	
}
//Version1: table should keep the info about the card on the table and 
//what cards each player hold
//More or like a memory