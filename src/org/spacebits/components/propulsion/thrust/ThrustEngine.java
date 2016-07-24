package org.spacebits.components.propulsion.thrust;

import org.spacebits.algorithm.thrust.ThrustAlgorithm;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.spacecraft.BusRequirement;


public interface ThrustEngine extends Engine {
	BusRequirement callDrive(double powerLevel);
	BusRequirement callStop();
	BusRequirement callVector(EngineVector engineVector);

	ThrustAlgorithm getThrustAlgorithm();

	void setThrustAlgorithm(ThrustAlgorithm thrustAlgorithm);

	double[] getThrust(double[] velocity);

	void setMaximumThrust(double maxThrust);

	double getMaximumThrust();
}
