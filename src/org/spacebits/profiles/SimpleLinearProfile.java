package org.spacebits.profiles;

public class SimpleLinearProfile implements Profile {

	@Override
	public double getValue(double input) {
		return input;
	}
	
}
