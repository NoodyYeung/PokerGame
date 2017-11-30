package pattern;

public abstract class Pattern {
	abstract public boolean isSamePattern(Pattern nextPattern);
	abstract public boolean isLarger(Pattern lastPattern);
	abstract public int getValue();
	abstract public int getNum();

	
}