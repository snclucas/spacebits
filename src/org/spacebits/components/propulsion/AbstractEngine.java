package org.spacebits.components.propulsion;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.comms.Status;
import org.spacebits.software.Message;
import org.spacebits.software.PropulsionManagementSoftware;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.BusRequirement;
import org.spacebits.status.SystemStatus;

public abstract class AbstractEngine extends AbstractBusComponent implements Engine {

	
	protected double powerLevel;
	protected EngineVector engineVector;
	protected boolean vectored;
	protected double requestedPowerLevel;
	protected EngineVector requestedEngineVector;

	public AbstractEngine(String name, BusComponentSpecification busResourceSpecification, 
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification);

		this.engineVector = engineVector;
		this.requestedEngineVector = engineVector;
		this.vectored = vectored;
		this.powerLevel = 0;
		this.requestedPowerLevel = 0;
	}


	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		if(getSystemComputer().hasSoftware(PropulsionManagementSoftware.typeID) != true) 
			systemStatus.addSystemMessage("No engine management software loaded", getUniversalTime(), Status.PROBLEM);
		else
			systemStatus.addSystemMessage("Engine management software loaded", getUniversalTime(), Status.OK);
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		return systemStatus;	
	}


	@Override
	public void execute() {
		this.powerLevel = this.requestedPowerLevel;
		this.engineVector = this.requestedEngineVector;
	}
	
	
	@Override
	public void setPowerLevel(double powerLevel) {
		this.powerLevel = powerLevel;
	}
	

	@Override
	public double getPowerLevel() {
		return powerLevel;
	}
	
	


	public BusRequirement callVector(EngineVector engineVector) {
		this.requestedEngineVector = engineVector;
		double requiredPower = getCurrentPower();
		double requiredCPUThroughput = getCurrentCPUThroughput();
		return new BusRequirement(requiredPower, requiredCPUThroughput);
	}





	@Override
	public String describe() {
		String description = " -- Simple Ion Engine -- \n" + 
				"Mass: " + getMass() + " Kg, Volume: " + getVolume() + " m3, Power: <thrust dep.> GJ/s, CPU: " + getNominalCPUThroughput() + " GFLOPS";
		description += "\n";
		description += "   ---------------------------------------------";
		return description;
	}


	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}


	@Override
	public EngineVector getEngineVector() {
		return engineVector;
	}
	

	@Override
	public boolean isVectored() {
		return vectored;
	}


	@Override
	public double getCurrentPower() {
		return getRequiredPower(this.powerLevel);
	}
	
	@Override
	public double getCurrentCPUThroughput() {
		return getRequiredCPUThroughput(this.powerLevel);
	}



}
