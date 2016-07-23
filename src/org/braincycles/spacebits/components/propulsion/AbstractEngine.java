package org.braincycles.spacebits.components.propulsion;

import org.braincycles.spacebits.algorithm.thrust.SimpleLinearThrustAlgorithm;
import org.braincycles.spacebits.algorithm.thrust.ThrustAlgorithm;
import org.braincycles.spacebits.components.AbstractBusComponent;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.comms.Status;
import org.braincycles.spacebits.software.PropulsionManagementSoftware;
import org.braincycles.spacebits.software.Message;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.BusRequirement;
import org.braincycles.spacebits.status.SystemStatus;

public abstract class AbstractEngine extends AbstractBusComponent implements Engine {

	protected ThrustAlgorithm thrustModel;
	protected double powerLevel;
	protected double maximumThrust;
	protected EngineVector engineVector;
	protected boolean vectored;
	protected double requestedPowerLevel;
	protected EngineVector requestedEngineVector;

	public AbstractEngine(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, ThrustAlgorithm thrustModel, 
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification);

		this.thrustModel = thrustModel;
		this.maximumThrust = maximumThrust;
		this.engineVector = engineVector;
		this.requestedEngineVector = engineVector;
		this.vectored = vectored;
		this.powerLevel = 0;
		this.requestedPowerLevel = powerLevel;
	}

	
	
	public AbstractEngine(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, 
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification);

		this.thrustModel = new SimpleLinearThrustAlgorithm("Linear thrust model");
		this.maximumThrust = maximumThrust;
		this.engineVector = engineVector;
		this.requestedEngineVector = engineVector;
		this.vectored = vectored;
		this.powerLevel = 0;
		this.requestedPowerLevel = powerLevel;
	}
	

	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}


	public void setMaximumThrust(double maxThrust) {
		this.maximumThrust = maxThrust;
	}


	public double getMaximumThrust() {
		return this.maximumThrust;
	}


	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		if(systemComputer.hasSoftware(PropulsionManagementSoftware.typeID) != true) 
			systemStatus.addSystemMessage("No engine management software loaded", systemComputer.getUniversalTime(), Status.PROBLEM);
		else
			systemStatus.addSystemMessage("Engine management software loaded", systemComputer.getUniversalTime(), Status.OK);
		systemStatus.addSystemMessage(getName() + " online.", systemComputer.getUniversalTime(), Status.OK);
		return systemStatus;	
	}


	@Override
	public void execute() {
		this.powerLevel = this.requestedPowerLevel;
		this.engineVector = this.requestedEngineVector;
	}
	

	@Override
	public double getPowerLevel() {
		return powerLevel;
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


	


	public BusRequirement callVector(EngineVector engineVector) {
		this.requestedEngineVector = engineVector;
		double requiredPower = getOperatingPower();
		double requiredCPUThroughput = getOperatingCPUThroughput();
		return new BusRequirement(requiredPower, requiredCPUThroughput);
	}





	





	@Override
	public String describe() {
		String description = " -- Simple Ion Engine -- \n" + 
				"Mass: " + getMass() + " Kg, Volume: " + getVolume() + " m3, Power: <thrust dep.> GJ/s, CPU: " + getNominalCPUThroughput() + " GFLOPS";
		description += "\n";
		description += "   ---------------------------------------------";
		description += "   Thrust model: " + thrustModel.toString() +"\n";
		return description;
	}


	@Override
	public SystemStatus runDiagnostics(int level) {
		return thrustModel.runDiagnostics(level);
	}


	@Override
	public void recieveMessage(Message message) {
		System.out.println("Message recieved by engine : " + getName() + "\n " + message.getMessage());

	}


	public EngineVector getEngineVector() {
		return engineVector;
	}


	public boolean isVectored() {
		return vectored;
	}


	@Override
	public double getOperatingPower() {
		return getRequiredPower(this.powerLevel);
	}
	
	@Override
	public double getOperatingCPUThroughput() {
		return getRequiredCPUThroughput(this.powerLevel);
	}



}
