package pattern;

public class Pthree extends Pattern {
	private int value;
	
	public Pthree(int threeValue) {
		value = threeValue;
	}

	@Override
	public boolean isSamePattern(Pattern another) {
		if(this.getClass().equals(another.getClass())) {
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
		// TODO Auto-generated method stub
		return 0;
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
