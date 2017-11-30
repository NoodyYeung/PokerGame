package DDZ;

import cards.Card;
import cards.Cards;
import pattern.*;

import java.util.*;
public class DDZ extends Rules{
	
	private static final String Set = null; // what is this doing here?

	/**
	 *
	 * @param cards
	 * @param lastHand
	 * @return The played card pattern if the plays is valid. If cards is not belongs any pattern , return null
	 */
	public Pattern validateDDZ(List<Card> cards, Cards lastHand) {
//		System.out.printf("[Debug] : cards.toString() %s \n" ,cards.toString() );
		Pattern thisPattern;
		thisPattern = identifyPattern(cards);
		if(thisPattern != null) {
//			System.out.println("[Debug] thisPattern:" + thisPattern.getClass().getName());
		}else{
//			System.out.println("[Debug] thisPattern: null");

		}
		if(thisPattern!=null) { // TODO 1 : validate for isNull == true
			if(lastHand!= null) { // TODO 2 : validate for lastHand isNull == true
				List<Card> lastHandCard = lastHand.getCards();
				Pattern lastPattern = identifyPattern(lastHand.getCards());
				if(lastPattern != null) {
//					System.out.println("[Debug] identifyPattern:" + lastPattern.getClass().getName());
				}else{
					System.out.println("[Debug] identifyPattern: null");

				}
				if(lastPattern.isSamePattern(thisPattern)) { // TODO 3 : validate for is not same
					if (thisPattern.isLarger(lastPattern)) {  // TODO 4 : validate for is not larger
						return thisPattern;
					}
				}else if(isBomb(cards)){
					if(!isRocket(lastHandCard) && isMultipleBomb(hashAllCards(lastHandCard)) == -1){
						return thisPattern;
					}else{
						return null;
					}

				}else if(isRocket(cards)){
					return thisPattern;
				}else if (isMultipleBomb(hashAllCards(cards)) != -1){
					if(!isRocket(lastHand.getCards())){
						return thisPattern;
					}else return null;
				}
			}
			else{
				return thisPattern;
			}
		}
		return null;
	}
	
	public HashMap<Integer,Integer> hashAllCards(List<Card> cards) {
		HashMap<Integer, Integer> cardsMap = new HashMap<>();
		for(Card aCard: cards) {
			int cardValue = aCard.getNumber();
			if(cardsMap.get(cardValue) != null) {
				cardsMap.put(cardValue,cardsMap.get(cardValue)+1);
			}
			else {
				cardsMap.put(cardValue,1);
			}
		}
		return cardsMap;
	}
	
	public Pattern identifyPattern(List<Card> cards) {
		
		Pattern pattern=null;
		HashMap<Integer, Integer> cardsMap = hashAllCards(cards); 

		if(cards.size()==1) { // TODO : condition 1
			pattern = new Pone(cards.get(0).getValue()); 
			return pattern;
		}
		else if(allDifferent(cardsMap)) {
				if(cards.size()<5) // TODO : condition 2
					return null;
				else {
					ArrayList<Integer> sortedCards = new ArrayList<>();
					for(int i=0;i<cards.size();i++) {
						sortedCards.add(cards.get(i).getValue());
					}
					Collections.sort(sortedCards);
					
					if(isStraight(sortedCards)) { // TODO : condition 3 - what happens when pattern !isStraight?
						ArrayList<Integer> listOfStraight = numOfN(cardsMap,3);
						Collections.sort(listOfStraight);
						pattern = new Pstraight(listOfStraight);
						return pattern;
					}
				}
			}
		else if(allSame(cards,cards.size())) {
					if(isBomb(cards)) { // TODO : condition 4a (line 72 + 73)
						pattern = new Pbomb(cards.get(0).getValue());
						return pattern;
					}
					else {
						if(cards.size()==3) { // TODO : condition 4b (line 72 + 78)
							pattern = new Pthree(cards.get(0).getValue());
						}
						else {
							pattern = new Ptwo(cards.get(0).getValue());
						}
						return pattern;
					}
						
				}
		else {
			if(cards.size() == 2) {
				if(isRocket(cards)) { // TODO 5b : condition coverage
					pattern = new Procket();
					return pattern;
				}
			}
			else if(cards.size()>=4) {
				int numOfTripleWithOne = isTripleWithOne(cardsMap,cards.size());
				int numOfFullHouse = isFullHouse(cardsMap,cards.size()); 
				
				if(numOfTripleWithOne>0) { // TODO 6a 
					ArrayList<Integer> listOfThree = numOfN(cardsMap,3);
					Collections.sort(listOfThree);
					pattern = new PtriplePlusOne(listOfThree,numOfTripleWithOne);
					return pattern;
				}
				else if(numOfFullHouse>0) { // TODO 6b
					ArrayList<Integer> listOfThree = numOfN(cardsMap,3);
					Collections.sort(listOfThree);
					pattern = new PfullHouse(listOfThree,numOfFullHouse);
					return pattern;
				}
				
				else if(cards.size()>=6) { // TODO 6c
					if(isDoubleStraight(cardsMap)>2) { // TODO 6c(i)
						ArrayList<Integer> listOfValues = numOfN(cardsMap,2);
						Collections.sort(listOfValues);
						pattern = new PdoubleStraight(listOfValues);
						return pattern;
					}
					else if(isTripleStraight(cardsMap)>2) { // TODO 6c(ii)
						ArrayList<Integer> listOfValues = numOfN(cardsMap,3);
						Collections.sort(listOfValues);
						pattern = new PtripleStraight(listOfValues);
						return pattern;
					}
					else if(isMultipleBomb(cardsMap)>1) { // TODO 6c(iii)
						ArrayList<Integer> listOfValues = numOfN(cardsMap,4);
						Collections.sort(listOfValues);
						pattern = new PmultiBomb(listOfValues);
						return pattern;
					}
				}
			}
		}
			
		return null;
	}

	private boolean isRocket(List<Card> cards) {
		return cards.size() == 2 && (
				(cards.get(0).getValue()==53 && cards.get(1).getValue()==54) || // TODO 5a : decision coverage
				(cards.get(1).getValue()==53 && cards.get(0).getValue()==54));
	}

	private boolean isBomb(List<Card> cards) {
		if(cards == null) return false;
		if(cards.size() == 4 && allSame(cards, cards.size())){
			return true;
		}
		return false;
	}

	private int isMultipleBomb(HashMap<Integer, Integer> cardsMap) {
		Set<Integer> cardsValues= cardsMap.keySet();
		List<Integer> valuelist = new ArrayList<Integer>(cardsValues);
		ArrayList<Integer> listOfAllValues = new ArrayList<>();
 		for(int i=0;i<valuelist.size();i++) {
 			int num = (int) cardsMap.get(valuelist.get(i));
 			if(num == 4) {
 				listOfAllValues.add(valuelist.get(i));
 			}
 			else {
 				return -1;
 			}
 		}
 		Collections.sort(listOfAllValues);
 		if(isStraight(listOfAllValues)) {
 			if(listOfAllValues.size()>=2)
 				return listOfAllValues.size();
 		}
		//return listOfThree;
 		return -1;
	}

	private int isTripleStraight(HashMap<Integer, Integer> cardsMap) {
		Set<Integer> cardsValues= cardsMap.keySet();
		List<Integer> valuelist = new ArrayList<Integer>(cardsValues);
		ArrayList<Integer> listOfAllValues = new ArrayList<>();
 		for(int i=0;i<valuelist.size();i++) {
 			int num = (int) cardsMap.get(valuelist.get(i));
 			if(num == 3) {
 				listOfAllValues.add(valuelist.get(i));
 			}
 			else {
 				return -1;
 			}
 		}
 		Collections.sort(listOfAllValues);
 		if(isStraight(listOfAllValues)) {
 			return listOfAllValues.size();
 		}
		//return listOfThree;
 		return -1;
	}

	private int isDoubleStraight(HashMap<Integer, Integer> cardsMap) {
		Set<Integer> cardsValues= cardsMap.keySet();
		List<Integer> valuelist = new ArrayList<Integer>(cardsValues);
		ArrayList<Integer> listOfAllValues = new ArrayList<>();
 		for(int i=0;i<valuelist.size();i++) {
 			int num = (int) cardsMap.get(valuelist.get(i));
 			if(num == 2) {
 				listOfAllValues.add(valuelist.get(i));
 			}
 			else {
 				return -1;
 			}
 		}
 		Collections.sort(listOfAllValues);
 		if(isStraight(listOfAllValues)) {
 			return listOfAllValues.size();
 		}
		//return listOfThree;
 		return -1;
	}

	private int isFullHouse(HashMap<Integer, Integer> cardsMap, int numOfAll) {
		ArrayList<Integer> listOfThree = numOfN(cardsMap,3);
		Collections.sort(listOfThree);
		if(isStraight(listOfThree)) {
			int a = (numOfAll-(3*listOfThree.size()));
			if(a == listOfThree.size()*2)
				return listOfThree.size();
 		}
 		return -1;
	}

	private int isTripleWithOne(HashMap<Integer, Integer> cardsMap,int numOfAll) {
		ArrayList<Integer> listOfThree = numOfN(cardsMap,3);
		Collections.sort(listOfThree);
 		
 		if(isStraight(listOfThree)) {
			if(numOfAll-(3*listOfThree.size()) == listOfThree.size())
				return listOfThree.size();
 		}
 		return -1;
	}


	private ArrayList<Integer> numOfN(HashMap<Integer, Integer> cardsMap, int n){
		Set<Integer> cardsValues= cardsMap.keySet();
		List<Integer> valuelist = new ArrayList<Integer>(cardsValues);
		ArrayList<Integer> listOfN = new ArrayList<>();
 		for(int i=0;i<valuelist.size();i++) {
 			int num = (int) cardsMap.get(valuelist.get(i));
 			if(num == n) {
 				listOfN.add(valuelist.get(i));
 			}
 		}
		return listOfN;
	}
	
	public boolean allSame(List<Card> cards, int length) {
		boolean result = true;
		for(int i=0;i<length-1;i++) {
//			System.out.printf("[Debug] : %d %d \n", cards.get(i).getNumber(), cards.get(i+ 1).getNumber());
			if(cards.get(i).getNumber()==cards.get(i+ 1).getNumber()) {
				continue;
			}
			else {
				result =  false;
				break;
			}
		}
		return result;
	}
	
	// TODO : What is the precondition on the input here? is it sorted?
	private boolean isStraight(ArrayList<Integer> cards) {
		for(int i=0;i<cards.size()-1;i++) {
			if(cards.get(i)+1 == cards.get(i+1))
				continue;
			else
				return false;
		}
		return true;
	}

	private boolean allDifferent(HashMap<Integer, Integer> cardsMap) {
		Set<Integer> cardValues = cardsMap.keySet();
		for(int card:cardValues) {
			int num = cardsMap.get(card); // TODO : why do we need to typecast this when it is already a set of ints?
			if(num>1)
				return false;
		}
		
		return true;
	}

	
}
