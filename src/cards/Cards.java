package cards;

import DDZ.DDZ;
import pattern.Pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Include some static function for Card
 */
public class Cards {
    /**
     *
     * @param rawString Expect a input like "C1 S1 D1 H1" OR "JB JR"
     * @return
     */
	List<Card> cards;
	Pattern pattern;
	
	public Cards (ArrayList<Card> cards, Pattern pattern){
		this.cards=cards;
		this.pattern=pattern;
	}

	public Cards(List<Card> cards){
	    this.cards = cards;
	    DDZ ddz = new DDZ();
	    this.pattern = ddz.identifyPattern(cards);
    }


    public static ArrayList<Card> createCardsListFromString(String rawString) throws ExCardNoExists {
        ArrayList<Card> cards = new ArrayList<Card>();
        String[] cardsStr = rawString.split(" ");
        
        for(int i = 0; i < cardsStr.length; i ++) {
            String tempCard  = cardsStr[i];
            cards.add(new Card(tempCard) );
        }
        Collections.sort((List<Card>) cards,new Comparator<Card>(){
        	public int compare(Card c1,Card c2){
        		return Integer.compare(c1.getValue(),c2.getValue());
        	}
        });
        return cards;
    }

    public static String toString(List<Card> cards){
	    if(cards== null){
	        return null;
        }
        String s = "";
        for(Card c : cards){
            s += c + " ";
        }
        return s;
    }



    
    public List<Card> getCards(){
    	return cards;
    }
    
    public Pattern getPattern() {
        // TODO Auto-generated method stub
        return null;
    }
}
