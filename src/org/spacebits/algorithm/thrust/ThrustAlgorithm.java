package org.spacebits.algorithm.thrust;

import org.spacebits.Diagnosable;
import org.spacebits.components.TypeInfo;
import org.spacebits.status.StatusProvider;

public interface ThrustAlgorithm extends Diagnosable, StatusProvider {
	
	TypeInfo categoryID = new TypeInfo("ThrustAlgorithm");

	double getNormalizedThrust(double powerLevel);
	double getNormalizedPower(double powerLevel);
	double getNormalizedCPU(double powerLevel);
	
	double[][] getNormalizedThrustProfile();
	double[][] getNormalizedPowerProfile();
	
}
