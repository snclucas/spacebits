package org.braincycles.spacebits.algorithm.thrust;

public class ThrustAlgorithmFactory {
	
	
	public static String SIMPLE_LINEAR = "Simple Linear";


	public static ThrustAlgorithm getThrustAlgorithm(String thrustModelType){
		if(thrustModelType.equalsIgnoreCase(SIMPLE_LINEAR)){
			return new SimpleLinearThrustAlgorithm(SIMPLE_LINEAR);
		}

		return null;
	}

}
