package pattern.tests.stubs;

import pattern.*;

public class PoneTestStubA extends Pone {
	
	int val;
	public PoneTestStubA(int thiscard) {
		super(thiscard);
		this.val = thiscard;
		// TODO Auto-generated constructor stub
	}
	
	public int getValue() {
		return val;
	}
	
}
