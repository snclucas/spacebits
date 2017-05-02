package org.spacebits.components.propulsion.thrust;

import org.spacebits.components.propulsion.AbstractEngine;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.profiles.SimpleLinearThrustProfile;
import org.spacebits.profiles.ThrustProfile;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.BusRequirement;
import org.spacebits.status.SystemStatus;

public abstract class AbstractThrustingEngine extends AbstractEngine implements ThrustingEngine {
	
	protected ThrustProfile thrustModel;
	private double maximumThrust;

	public AbstractThrustingEngine(String name,
			BusComponentSpecification busResourceSpecification,
			double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, engineVector, vectored);
		this.maximumThrust = maximumThrust;
		this.thrustModel = new SimpleLinearThrustProfile("Linear thrust model");
	}

	
	public AbstractThrustingEngine(String name,
			BusComponentSpecification busResourceSpecification,
			double maximumThrust, ThrustProfile thrustModel,
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, engineVector,vectored);
		this.maximumThrust = maximumThrust;
		this.thrustModel = thrustModel;
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
	public ThrustProfile getThrustProfile() {
		return this.thrustModel;
	}


	@Override
	public void setThrustProfile(ThrustProfile thrustModel) {
		this.thrustModel = thrustModel;
	}


	@Override
	public double[] getThrust(double[] velocity) {
		double thrust = maximumThrust * thrustModel.getNormalizedThrust(powerLevel);
		double[] vector = engineVector.getVectorComponents();		
		return new double[]{thrust * vector[0], thrust * vector[1], thrust * vector[2]};
	}
	
	
	@Override
	public double getMaximumThrust() {
		return this.maximumThrust;
	}
	
	
	@Override
	public void setMaximumThrust(double maximumThrust) {
		this.maximumThrust = maximumThrust;
	}


	@Override
	public void callVector(EngineVector engineVector) {
		this.engineVector = engineVector;
	}
	
	
	@Override
	public double getRequiredPower(double requiredPowerLevel) {
		double nominalPower = busResourceSpecification.getNominalPower();
		double maximumOperatingPower = busResourceSpecification.getMaximumOperationalPower();
		return nominalPower + (maximumOperatingPower-nominalPower) * thrustModel.getNormalizedPower(requiredPowerLevel);
	}
	

	@Override
	public double getRequiredCPUThroughput(double requiredPowerLevel) {
		// The CPU throughput does not depend upon power level in this model
		double nominalCPU = busResourceSpecification.getNominalCPUThroughout();
		double maximumOperatingCPU = busResourceSpecification.getMaximumOperationalCPUThroughput();
		return nominalCPU + (maximumOperatingCPU-nominalCPU) * thrustModel.getNormalizedCPU(requiredPowerLevel);
	}
	
	
	@Override
	public SystemStatus runDiagnostics(int level) {
		return thrustModel.runDiagnostics(level);
	}

}
