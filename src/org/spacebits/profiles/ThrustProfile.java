package org.spacebits.profiles;

import org.spacebits.components.Diagnosable;
import org.spacebits.components.TypeInfo;
import org.spacebits.status.StatusProvider;

public interface ThrustProfile extends Diagnosable, StatusProvider {
	
	TypeInfo category = new TypeInfo("ThrustProfile");

	double getNormalizedThrust(double powerLevel);
	double getNormalizedPower(double powerLevel);
	double getNormalizedCPU(double powerLevel);
	
	double[][] getNormalizedThrustProfile();
	double[][] getNormalizedPowerProfile();
	
}
