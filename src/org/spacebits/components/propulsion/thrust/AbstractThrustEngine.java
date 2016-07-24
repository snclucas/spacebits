package org.spacebits.components.propulsion.thrust;

import org.spacebits.algorithm.thrust.ThrustAlgorithm;
import org.spacebits.components.propulsion.AbstractEngine;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.BusRequirement;

public abstract class AbstractThrustEngine extends AbstractEngine implements ThrustEngine {

	public AbstractThrustEngine(String name,
			BusComponentSpecification busResourceSpecification,
			double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, engineVector, vectored);
	}

	public AbstractThrustEngine(String name,
			BusComponentSpecification busResourceSpecification,
			double maximumThrust, ThrustAlgorithm thrustModel,
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel, engineVector,
				vectored);
	}
	
	
	public BusRequirement callDrive(double requestedPowerLevel) {
		this.requestedPowerLevel = requestedPowerLevel;
		double requiredPower = getRequiredPower(requestedPowerLevel);
		double requiredCPUThroughput = getRequiredCPUThroughput(requestedPowerLevel);
		return new BusRequirement(requiredPower, requiredCPUThroughput);
	}
	
	
	public BusRequirement callStop() {
		return callDrive(0.0);
	}
	
	

	@Override
	public ThrustAlgorithm getThrustAlgorithm() {
		return this.thrustModel;
	}


	@Override
	public void setThrustAlgorithm(ThrustAlgorithm thrustModel) {
		this.thrustModel = thrustModel;
	}


	@Override
	public double[] getThrust(double[] velocity) {
		double thrust = maximumThrust * thrustModel.getNormalizedThrust(powerLevel);
		double[] vector = engineVector.getVectorComponents();		
		return new double[]{thrust * vector[0], thrust * vector[1], thrust * vector[2]};
	}

}
