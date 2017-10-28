package pattern;

import java.util.ArrayList;

public class PmultiBomb extends Pattern {
	//private ArrayList<Integer> listOfValues;
	private int num;
	private int max;
	public PmultiBomb(ArrayList<Integer> thisListOfValues) {
		//listOfValues = thisListOfValues;
		num = thisListOfValues.size();
		max = thisListOfValues.get(thisListOfValues.size()-1);
	}

	@Override
	public boolean isSamePattern(Pattern another) {
		if(this.getClass().equals(another.getClass())) {
			if(this.num == ((PmultiBomb) another).getNum()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(max>lastPattern.getValue()) {
			return true;
		}
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
