package org.spacebits.components.propulsion.thrust;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.profiles.ThrustProfile;
import org.spacebits.spacecraft.BusRequirement;


public interface ThrustingEngine extends Engine {
	
	public static TypeInfo type() {
		return new TypeInfo("ThrustingEngine");
	}
	
	BusRequirement callDrive(double powerLevel);
	BusRequirement callStop();
	BusRequirement callVector(EngineVector engineVector);

	ThrustProfile getThrustProfile();

	double[] getThrust(double[] velocity);

	double getMaximumThrust();
}
