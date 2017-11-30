package cards;

public enum Suit {
	CLUB("C"), SPADE("S"), DIAMOND("D"), HEART("H"), JOKER_BLACK("JB"), JOKER_RED("JR");
	private final String name;

	private Suit(String s) {
		name = s;
	}
	public boolean equalsName(String otherName) {
		// (otherName == null) check is not needed because name.equals(null) returns false
		return name.equals(otherName);
	}
	public String toString() {
		return this.name;
	}
}