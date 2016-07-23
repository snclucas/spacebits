package org.braincycles.spacebits.algorithm.thrust;

import org.braincycles.spacebits.Diagnosable;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.status.StatusProvider;

public interface ThrustAlgorithm extends Diagnosable, StatusProvider {
	
	TypeInfo categoryID = new TypeInfo("ThrustAlgorithm");

	double getNormalizedThrust(double powerLevel);
	double getNormalizedPower(double powerLevel);
	double getNormalizedCPU(double powerLevel);
	
	double[][] getNormalizedThrustProfile();
	double[][] getNormalizedPowerProfile();
	
}
