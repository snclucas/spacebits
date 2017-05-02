package org.spacebits.profiles;

public class FuelConsumptionProfileFactory {
	
	public static String SIMPLE_LINEAR = "Simple Linear";

	public static FuelConsumptionProfile getThrustAlgorithm(String thrustModelType){
		if(thrustModelType.equalsIgnoreCase(SIMPLE_LINEAR)){
			return new SimpleLinearFuelConsumptionProfile(SIMPLE_LINEAR);
		}
		return null;
	}

}
