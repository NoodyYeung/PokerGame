package pattern;

import java.util.ArrayList;
//import java.util.HashMap;

public class PtriplePlusOne extends Pattern {
	
	private int num;
	//private ArrayList<Integer> listOfThrees;
	private int maxOfThrees;
	public PtriplePlusOne(ArrayList<Integer> thislistOfThree, int numOfTripleWithOne) {
		//listOfThrees = thislistOfThree;
		maxOfThrees = thislistOfThree.get(thislistOfThree.size()-1);
		num = thislistOfThree.size();
	}

	@Override
	public boolean isSamePattern(Pattern another) {
		if(this.getClass().equals(another.getClass())) {
			if(this.num == ((PtriplePlusOne) another).getNum()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(maxOfThrees>lastPattern.getValue())
			return true;
		return false;
	}

	@Override
	public int getValue() {
		return maxOfThrees;
	}

	@Override
	public int getNum() {
		return num;
	}
	
	@Override
	public boolean equals (Object o) {
	    if (!(o instanceof PtriplePlusOne)) {
	        return false;
	    }
	    PtriplePlusOne other = (PtriplePlusOne)o;
	    return getValue()==other.getValue()&&getNum()==other.getNum();
	}

	
}
