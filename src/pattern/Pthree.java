package pattern;

public class Pthree extends Pattern {
	private int value;
	private int num = 3;
	
	public Pthree(int threeValue) {
		value = threeValue;
	}

	@Override
	public boolean isSamePattern(Pattern another) {
		if(another instanceof Pthree) {
				return true;
		}
		return false;
	}

	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(value > lastPattern.getValue())
			return true;
		return false;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getNum() {
		return 3;
	}
	@Override
	public boolean equals (Object o) {
	    if (!(o instanceof Pthree)) {
	        return false;
	    }
	    Pthree other = (Pthree)o;
	    return getValue()==other.getValue();
	}
	
	
}
