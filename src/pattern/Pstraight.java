package pattern;

import java.util.*;

public class Pstraight extends Pattern {
	private int num;
	private int max;
	
	// precondition : 
	public Pstraight(ArrayList<Integer> listOfStraight) {
		num =  listOfStraight.size();
		max = listOfStraight.get(num - 1);
	}
	
	@Override
	public boolean isSamePattern(Pattern another) {
		if(another instanceof Pstraight) {
			if(this.num == another.getNum()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(max > lastPattern.getValue())
			return true;
		return false;
	}
	
	@Override
	public int getValue() {
		return max;
	}
	
	@Override
	public int getNum() {
		return num;
	}

}
