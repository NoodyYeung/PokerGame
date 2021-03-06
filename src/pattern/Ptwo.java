package pattern;

public class Ptwo extends Pattern {

	private int value;
	public Ptwo(int twoValue) {
		value = twoValue;
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
		if(value>lastPattern.getValue())
			return true;
		return false;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getNum() {
		return 2;
	}

	@Override
	public boolean equals (Object o) {
	    if (!(o instanceof Ptwo)) {
	        return false;
	    }
	    Ptwo other = (Ptwo)o;
	    return getValue()==other.getValue() && getNum()==other.getNum();
	}

}
