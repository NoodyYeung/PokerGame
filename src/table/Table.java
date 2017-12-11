package table;

import DDZ.DDZ;
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


	public Table(List<T> players){
		this.players=players;
		historyCard= new ArrayList<>();
		roundCard=new ArrayList<>();
		entities=new ArrayList<>();

	}


	public List<PlayerAndCards> getPlayerCardsEntities() {
		return entities;
	}


	
	// add player????
	/**The function call from TableController createTable **/
	//distribute the cards to the players and create entity for each player	
	//each entity saves the player info and his related cards
	//the entity is saved in the entities list
	//return the distributed cards in string format and the delimiter is " "
	public String distributeCards(Random rand) throws ExCardNoExists, ExNotEnoughPlayers {
		ArrayList<Card> cards=randomGenerateCards(rand);
		ArrayList<Card> cardsOnBottom = new ArrayList<>();

		if(players == null){
			throw new ExNotEnoughPlayers("Players object is null");
		}

		if(players.size()<3)
			throw new ExNotEnoughPlayers("Not enough players at this table! There are only "+players.size()+" players here.");
		
//		if(hasDiZhu()){
		for(int i=0; i<3; i++){
			Card c = cards.get(rand.nextInt(cards.size()) + 0);
			cardsOnBottom.add(c);
			cards.remove(c);
		}
//		}
		
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
//			players.get(playerCounter).take(c);
			playerAndCards.get(playerCounter).add(cards.get(cards.size()-1));
			cards.remove(cards.size()-1);
			playerCounter++;
			playerCounter = playerCounter%3;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		int randomLandLord = rand.nextInt(3);
//		System.out.println("[Debug] randomLandLord :" + randomLandLord);
//		System.out.println("[Debug] randomLandLord cardSize :" + cards.size());
//		System.out.println("[Debug] randomLandLord cardsOnBottomSize :" + cardsOnBottom.size());

		while(cardsOnBottom.size() > 0) {
			playerAndCards.get(randomLandLord).add(cardsOnBottom.get(cardsOnBottom.size() - 1));
			cardsOnBottom.remove((cardsOnBottom.size()- 1));
		}
		String result="";
		int i=0;
		PlayerAndCards entity;
		for (Player p:players){
			ArrayList<Card> tempCards = playerAndCards.get(i++);
			entity=new PlayerAndCards(p,tempCards);
			entities.add(entity);
			result=result+tempCards.toString()+" ";
		}
		return result;
	}

	private ArrayList<Card> randomGenerateCards(Random rand) throws ExCardNoExists {
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
		Collections.shuffle(wholeDeck, rand);

		return wholeDeck;
	}


	public Cards getLastHandCard(){
		return lastHandCard;
	}


	public boolean checkGameEnd(){
		for(PlayerAndCards e:entities){
			if(e.isEmpty()) return true;
		}
		return false;
	}


	public void emptyLastHand() {
		// TODO Auto-generated method stub
		lastHandCard=null;
	}

	public Player getGameWinner() {
		// TODO Auto-generated method stub
		for(PlayerAndCards e:entities){
			if(e.isEmpty()) return e.getPlayer();
		}
		return null;
	}


	public void setLastHand(List<Card> lastHand) {
		DDZ ddz = new DDZ();
		Pattern pattern = ddz.identifyPattern(lastHand);
		this.lastHandCard = new Cards((ArrayList<Card>) lastHand, pattern);
	}


	/**
	 * Reduce the card from player hand and add the played Card to the used Card List
	 * @param player
	 * @param playedCard
	 * @return True if the player is found and the player card is reduced AND the reduced card number equals to the number of played cards; Otherwise False
	 */
	public boolean reduceTheCardFromPlayer(Player player, List<Card> playedCard) {
		int removedCard  = 0;
		List<Card> cardNeededToBeRemoved = new ArrayList();
		for(PlayerAndCards pc : entities){
			if(pc.equalsPlayer(player)){
				System.out.println("[DEBUG] reduceTheCardFromPlayer : FOUND equals player !");
				List<Card > cards = pc.getCards();


				for(Card playedC : playedCard){
					boolean matched = false;
					Card matchedCard = null;
					for(int i = cards.size()-1; i >= 0 ; i --) {
						Card c = cards.get(i);
						if (c.equals(playedC)) {
							matchedCard = c;
							matched = true;
							break;
						}
					}
					if(matched) {
						removedCard++;
						cardNeededToBeRemoved.add(matchedCard);
					}
				}
				System.out.println("[DEBUG] cardNeededToBeRemoved: "+Cards.toString(cardNeededToBeRemoved));
				System.out.println("[DEBUG] playedCard: "+Cards.toString(playedCard));
				if(removedCard != playedCard.size()) {
					System.out.println("[Debug] fail to reduce the card");
					return false;
				}else {
					System.out.println("[DEBUG] : Card before delete : " + Cards.toString(cards));
					List<Card> userHandCards = pc.getCards();
					List<Card> userUsedCards = pc.getUsedCards();
					for(Card rc: cardNeededToBeRemoved){
						userHandCards.remove(rc);
						userUsedCards.add(rc);
					}
					System.out.println("[DEBUG] : Card after delete : " + Cards.toString(userHandCards));
					return true;
				}
			}
		}
		return false;
	}
}
//Version1: table should keep the info about the card on the table and 
//what cards each player hold
//More or like a memory