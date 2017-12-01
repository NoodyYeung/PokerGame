package pattern;

import java.util.ArrayList;

public class PdoubleStraight extends Pattern {

	//private ArrayList<Integer> listOfValues;
	private int num;
	private int max;
	public PdoubleStraight(ArrayList<Integer> thisListOfValues) {
		//listOfValues = thisListOfValues;
		num = thisListOfValues.size();
		max = thisListOfValues.get(thisListOfValues.size()-1);
	}

	@Override
	public boolean isSamePattern(Pattern nextPattern) {
		if(nextPattern instanceof PdoubleStraight) {
			if(this.num == nextPattern.getNum()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(lastPattern instanceof Pbomb || lastPattern instanceof Procket){ 
			return false;
		} 
		else if(this.max > lastPattern.getValue()){
			return true;
		}
		else {
			return false;
		}
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
	    if (!(o instanceof PdoubleStraight)) {
	        return false;
	    }
	    PdoubleStraight other = (PdoubleStraight)o;
	    return getValue()==other.getValue()&&getNum()==other.getNum();
	}
	
	

}
