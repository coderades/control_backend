package com.control.corporate.model.enumerator;

public enum HumanResourcePunchPeriod {
	PERIOD_1(1), PERIOD_2(2), PERIOD_3(3);

	private int numVal;

	HumanResourcePunchPeriod(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}
	
}
