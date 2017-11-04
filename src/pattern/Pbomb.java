package pattern;

public class Pbomb extends Pattern {
	private int value;
	
	public Pbomb(int bombValue) {
		value = bombValue;
	}

	@Override
	public boolean isSamePattern(Pattern nextPattern) {
		if(this.getClass().equals(nextPattern.getClass()))
			return true;
		return false;
	}

	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(value>((Pbomb)lastPattern).getValue())
			return true;
		return false;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getNum() {
		return 0;
	}

	
	
	

}