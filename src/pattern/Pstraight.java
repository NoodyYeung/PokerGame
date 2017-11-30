package pattern;

import java.util.*;

public class Pstraight extends Pattern {
	private int num;
	private int max;
	
	public Pstraight(ArrayList<Integer> listOfStraight) {
		num =  listOfStraight.size();
	}
	@Override
	public boolean isSamePattern(Pattern another) {
		if(this.getClass().equals(another.getClass())) {
			if(this.num == ((Pstraight) another).getNum()) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(max>lastPattern.getValue())
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


