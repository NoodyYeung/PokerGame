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
		if (lastPattern instanceof Pbomb) {
			if(value>((Pbomb)lastPattern).getValue())
				return true;
			else
				return false;
		} else {
			if(lastPattern instanceof Procket) {
				return false;
			}
			else {
				return true;
			}	 // Pbomb is always bigger than any other pattern
		}
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getNum() {
		return 4;
	}

	
	
	

}
