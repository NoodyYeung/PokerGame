package pattern;

public class Pone extends Pattern{
	private int card;
	public Pone(int thiscard) {
		this.card=thiscard;
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
			if(card > lastPattern.getValue())
				return true;
			else
				return false;
		}
		return false;
	}
	@Override
	public int getValue() {
		return card;
	}
	@Override
	public int getNum() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean equals (Object o) {
	    if (!(o instanceof Pone)) {
	        return false;
	    }
	    Pone other = (Pone)o;
	    return getValue()==other.getValue();
	}


}
