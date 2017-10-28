package pattern;

public class Pone extends Pattern{
	private int card;
	public Pone(int thiscard) {
		this.card=thiscard;
	}
	@Override
	public boolean isSamePattern(Pattern nextPattern) {
		if(this.getClass().equals(nextPattern.getClass()))
			return true;
		return false;
	}
	@Override
	public boolean isLarger(Pattern lastPattern) {
		if(card > ((Pone) lastPattern).getValue())
			return true;
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
	
	


}
