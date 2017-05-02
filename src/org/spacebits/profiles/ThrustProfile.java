package org.spacebits.profiles;

import org.spacebits.Diagnosable;
import org.spacebits.components.TypeInfo;
import org.spacebits.status.StatusProvider;

public interface ThrustProfile extends Diagnosable, StatusProvider {
	
	TypeInfo categoryID = new TypeInfo("ThrustProfile");

	double getNormalizedThrust(double powerLevel);
	double getNormalizedPower(double powerLevel);
	double getNormalizedCPU(double powerLevel);
	
	double[][] getNormalizedThrustProfile();
	double[][] getNormalizedPowerProfile();
	
}
