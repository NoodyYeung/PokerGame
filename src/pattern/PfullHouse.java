package pattern;

import java.util.ArrayList;

public class PfullHouse extends Pattern {

	//private ArrayList<Integer> listOfThree;
	private int num;
	private int max;
	public PfullHouse(ArrayList<Integer> thislistOfThree, int numOfFullHouse) {
		//listOfThree = thislistOfThree;
		num = numOfFullHouse;
		max = thislistOfThree.get(thislistOfThree.size()-1);
	}

	
	@Override
	public boolean isSamePattern(Pattern another) {
		if(this.getClass().equals(another.getClass())) {
			if(this.num == ((PfullHouse) another).getNum()) {
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
