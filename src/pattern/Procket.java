package pattern;

public class Procket extends Pattern {
	
	public Procket() {}
	@Override
	public boolean isSamePattern(Pattern another) {
		return false;
	}
	@Override
	public boolean isLarger(Pattern lastPattern) {
		return true;
	}
	@Override
	public int getValue() {
		return 16;
	}
	
	@Override
	public int getNum() {
		
		return 2;
	}

	@Override
	public boolean equals (Object o) {
	    return true;
	}
	

}
