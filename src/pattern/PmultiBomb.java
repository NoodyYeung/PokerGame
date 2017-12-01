package pattern;

import java.util.ArrayList;

public class PmultiBomb extends Pattern {
	
	private int num;
	private int max;
	
	public PmultiBomb(ArrayList<Integer> thisListOfValues) {
		num = thisListOfValues.size();
		max = thisListOfValues.get(thisListOfValues.size()-1);
	}

	@Override
	public boolean isSamePattern(Pattern another) {
		if(another instanceof PmultiBomb) {
			if(this.num ==  another.getNum()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(max > lastPattern.getValue()) {
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
	
	@Override
	public boolean equals (Object o) {
	    if (!(o instanceof PmultiBomb)) {
	        return false;
	    }
	    PmultiBomb other = (PmultiBomb)o;
	    return getValue()==other.getValue()&&getNum()==other.getNum();
	}
	

}
