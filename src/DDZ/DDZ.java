package DDZ;

import cards.Card;
import cards.Cards;
import pattern.*;

import java.util.*;
public class DDZ extends Rules{
	
	
	private static final String Set = null;

	public Pattern validateDDZ(ArrayList<Card> cards, Cards lastHand) {
		Pattern thisPattern;
		thisPattern = identifyPattern(cards);
		if(thisPattern!=null) {
			if(lastHand!= null) {
				Pattern lastPattern = lastHand.getPattern();
				if(lastPattern.isSamePattern(thisPattern)) {
					if(thisPattern.isLarger(lastPattern)) {
						return thisPattern;
					}
				}
			}
			else{
				return thisPattern;
			}
		}
		return null;
	}
	
	public HashMap<Integer,Integer> hashAllCards(ArrayList<Card> cards) {
		HashMap<Integer, Integer> cardsMap = new HashMap();
		for(Card aCard: cards) {
			int cardValue = aCard.getValue();
			if(cardsMap.get(cardValue) != null) {
				cardsMap.put(cardValue,cardsMap.get(cardValue)+1);
			}
			else {
				cardsMap.put(cardValue,1);
			}
		}
		return cardsMap;
	}
	
	
	public Pattern identifyPattern(ArrayList<Card> cards) {
		
		Pattern pattern=null;
		HashMap cardsMap = hashAllCards(cards);

		if(cards.size()==1) {
			pattern = new Pone(cards.get(0).getValue()); 
			return pattern;
		}
		else if(allDifferent(cardsMap)) {
				if(cards.size()<5)
					return null;
				else {
					ArrayList<Integer> sortedCards = new ArrayList();
					for(int i=0;i<cards.size();i++) {
						sortedCards.add(cards.get(i).getValue());
					}
					Collections.sort(sortedCards);
					
					if(isStraight(sortedCards)) {
						ArrayList<Integer> listOfStraight = numOfN(cardsMap,3);
						Collections.sort(listOfStraight);
						pattern = new Pstraight(listOfStraight);
						return pattern;
					}
				}
			}
		else if(allSame(cards,cards.size())) {
					if(cards.size()==4) {
						pattern = new Pbomb(cards.get(0).getValue());
						return pattern;
					}
					else {
						if(cards.size()==3) {
							pattern = new Pthree(cards.get(0).getValue());
						}
						else {
							pattern = new Ptwo(cards.get(0).getValue());
						}
					}
						
				}
		else {
			if(cards.size() == 2) {
				if((cards.get(0).getValue()==16 && cards.get(1).getValue()==17) || 
						(cards.get(1).getValue()==16 && cards.get(0).getValue()==17)) {
					pattern = new Procket();
					return pattern;
				}
			}
			else if(cards.size()>=4) {
				
				int numOfTripleWithOne = isTripleWithOne(cardsMap,cards.size());
				int numOfFullHouse = isFullHouse(cardsMap,cards.size());
				if(numOfTripleWithOne>0) {
					ArrayList<Integer> listOfThree = numOfN(cardsMap,3);
					Collections.sort(listOfThree);
					pattern = new PtriplePlusOne(listOfThree,numOfTripleWithOne);
					return pattern;
				}
				else if(numOfFullHouse>0) {
					ArrayList<Integer> listOfThree = numOfN(cardsMap,3);
					Collections.sort(listOfThree);
					pattern = new PfullHouse(listOfThree,numOfFullHouse);
					return pattern;
				}
				
				else if(cards.size()>=6) {
					if(isDoubleStraight(cardsMap)>2) {
						ArrayList<Integer> listOfValues = numOfN(cardsMap,2);
						Collections.sort(listOfValues);
						pattern = new PdoubleStraight(listOfValues);
						return pattern;
					}
					else if(isTripleStraight(cardsMap)>2) {
						ArrayList<Integer> listOfValues = numOfN(cardsMap,3);
						Collections.sort(listOfValues);
						pattern = new PtripleStraight(listOfValues);
						return pattern;
					}
					else if(isMultipleBomb(cardsMap)>1) {
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

	private int isMultipleBomb(HashMap cardsMap) {
		Set<Integer> cardsValues= cardsMap.keySet();
		List<Integer> valuelist = new ArrayList<Integer>(cardsValues);
		ArrayList<Integer> listOfAllValues = new ArrayList();
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

	private int isTripleStraight(HashMap cardsMap) {
		Set<Integer> cardsValues= cardsMap.keySet();
		List<Integer> valuelist = new ArrayList<Integer>(cardsValues);
		ArrayList<Integer> listOfAllValues = new ArrayList();
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

	private int isDoubleStraight(HashMap cardsMap) {
		Set<Integer> cardsValues= cardsMap.keySet();
		List<Integer> valuelist = new ArrayList<Integer>(cardsValues);
		ArrayList<Integer> listOfAllValues = new ArrayList();
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

	private int isFullHouse(HashMap cardsMap, int numOfAll) {
		ArrayList<Integer> listOfThree = numOfN(cardsMap,3);
		Collections.sort(listOfThree);
		if(isStraight(listOfThree)) {
			int a = (numOfAll-(3*listOfThree.size()));
			if(a == listOfThree.size()*2)
				return listOfThree.size();
 		}
 		return -1;
	}

	private int isTripleWithOne(HashMap cardsMap,int numOfAll) {
		ArrayList<Integer> listOfThree = numOfN(cardsMap,3);
		Collections.sort(listOfThree);
 		
 		if(isStraight(listOfThree)) {
			if(numOfAll-(3*listOfThree.size()) == listOfThree.size())
				return listOfThree.size();
 		}
 		return -1;
	}


	
	private ArrayList<Integer> numOfN(HashMap cardsMap, int n){
		Set<Integer> cardsValues= cardsMap.keySet();
		List<Integer> valuelist = new ArrayList<Integer>(cardsValues);
		ArrayList<Integer> listOfN = new ArrayList();
 		for(int i=0;i<valuelist.size();i++) {
 			int num = (int) cardsMap.get(valuelist.get(i));
 			if(num == n) {
 				listOfN.add(valuelist.get(i));
 			}
 		}
		return listOfN;
	}


	
	private boolean allSame(ArrayList<Card> cards, int length) {
		for(int i=0;i<length-1;i++) {
			if(cards.get(i).getValue()==cards.get(1).getValue())
				continue;
			else
				return false;
		}
		return true;
	}
	

	private boolean isStraight(ArrayList<Integer> cards) {
		for(int i=0;i<cards.size()-1;i++) {
			if(cards.get(i)+1 == cards.get(i+1))
				continue;
			else
				return false;
		}
		return true;
	}

	private boolean allDifferent(HashMap cardsMap) {
		Set<Integer> cardValues = cardsMap.keySet();
		for(int card:cardValues) {
			int num = (int) cardsMap.get(card);
			if(num>1)
				return false;
		}
		
		return true;
	}

	
	

	
	
}