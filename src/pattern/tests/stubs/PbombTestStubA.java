package pattern.tests.stubs;

import pattern.*;

public class PbombTestStubA extends Pbomb {
	
	int val;
	
	public PbombTestStubA(int val) {
		super(val);
	}
	@Override
	public boolean isSamePattern(Pattern nextPattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLarger(Pattern lastPattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return val;
	}

	@Override
	public int getNum() {
		// TODO Auto-generated method stub
		return 0;
	}

}
