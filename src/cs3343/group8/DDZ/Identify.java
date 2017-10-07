package cs3343.group8.DDZ;

import java.util.ArrayList;

import cards.Card;
// TODO: after user plays a hand (eg. JJJ QQ), identify identifies what the hand is, and calculates the value?
// This package is incomplete! You can start from here
// eg. does JJJ QQ match the pattern 
// this should be a singleton, because identify only has 1 set of rules

/*Patterns:
 * each a type of 'combo' (auto * suit)
	33
	333
	3333 or larger (bombing)
	444 55
	444 555 or larger
	44 55 66 or larger
	34567 or larger (watch largest, and numbers should match)
	-value: bombs have large values & highest priority, others have same values/priority (eg. you can't use JJJ QQ against 33 44 55)
 * */

public class Identify {
	
	public static void look(ArrayList<Card> cards){
		
	}

}
