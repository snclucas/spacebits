package org.spacebits.components.propulsion.thrust;

import org.spacebits.components.propulsion.Engine;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.profiles.ThrustProfile;
import org.spacebits.spacecraft.BusRequirement;


public interface ThrustingEngine extends Engine {
	BusRequirement callDrive(double powerLevel);
	BusRequirement callStop();
	void callVector(EngineVector engineVector);

	ThrustProfile getThrustProfile();

	void setThrustProfile(ThrustProfile thrustProfile);

	double[] getThrust(double[] velocity);

	void setMaximumThrust(double maxThrust);

	double getMaximumThrust();
}
