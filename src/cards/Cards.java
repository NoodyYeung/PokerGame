package cards;

import java.util.ArrayList;
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
    public static List<Card> createCardsListFromString(String rawString) throws ExCardNoExists {
        ArrayList cards = new ArrayList();
        String[] cardsStr = rawString.split(" ");
        for(int i = 0; i < cardsStr.length; i ++) {
            String tempCard  = cardsStr[i];
            cards.add(new Card(tempCard) );
        }
        return cards;
    }

    public static String toString(List<Card> cards){
        String s = "";
        for(Card c : cards){
            s += c + " ";
        }
        return s;
    }
}
