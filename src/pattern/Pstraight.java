package pattern;

import java.util.*;

public class Pstraight extends Pattern {
	private int num;
	private int max = 0;
	
	// precondition : 
	public Pstraight(ArrayList<Integer> listOfStraight) {
		num =  listOfStraight.size();
		for(Integer i : listOfStraight){
			max = Math.max(i,max);
		}
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
	@Override
	public boolean equals (Object o) {
		if (!(o instanceof Pstraight)) {
			return false;
		}
		Pstraight other = (Pstraight)o;
		return getNum()==other.getNum();
	}
}


