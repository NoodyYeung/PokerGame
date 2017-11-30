package pattern;

public class Pone extends Pattern{
	private int value;
	public Pone(int val) {
		this.value=val;
	}
	@Override
	public boolean isSamePattern(Pattern nextPattern) {
		if(nextPattern instanceof Pone)
			return true;
		return false;
	}
	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(lastPattern instanceof Pone) {
			if(value > lastPattern.getValue())
				return true;
			else
				return false;
		}
		return false; // will never have to compare with a hand that is not the same
	}
	
	@Override
	public int getValue() {
		return value;
	}
	@Override
	public int getNum() {
		return 1;
	}
}
