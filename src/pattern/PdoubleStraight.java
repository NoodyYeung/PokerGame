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
		if(this.getClass().equals(nextPattern.getClass())) {
			if(this.num == ((PdoubleStraight) nextPattern).getNum()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(this.max > lastPattern.getValue())
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
