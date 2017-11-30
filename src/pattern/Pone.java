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
//			System.out.printf("[DEBUG] comparing in Pone.class -- values; %d %d\n", card, lastPattern.getValue());
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
	
	


}
