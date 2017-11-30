package pattern;

import java.util.ArrayList;
//import java.util.HashMap;

public class PtriplePlusOne extends Pattern {

	private int num;
	private int maxOfThrees;
	
	public PtriplePlusOne(ArrayList<Integer> thislistOfThree, int numOfTripleWithOne) {
		maxOfThrees = thislistOfThree.get(thislistOfThree.size()-1);
		num = thislistOfThree.size();
	}

	@Override
	public boolean isSamePattern(Pattern another) {
		if(another instanceof PtriplePlusOne) {
			if(this.num ==  another.getNum()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(maxOfThrees > lastPattern.getValue())
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

	
}
