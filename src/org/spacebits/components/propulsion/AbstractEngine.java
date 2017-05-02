package org.spacebits.components.propulsion;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.software.Message;
import org.spacebits.software.PropulsionManagementSoftware;
import org.spacebits.spacecraft.BusComponentSpecification;
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
	public final TypeInfo getCategoryId() {
		return categoryID;
	}


	@Override
	public TypeInfo getTypeId() {
		return typeID;
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
	public void setPowerLevel(double powerLevel) {
		this.powerLevel = powerLevel;
	}
	

	@Override
	public double getPowerLevel() {
		return powerLevel;
	}
	

	


	


	//public BusRequirement callVector(EngineVector engineVector) {
	//	this.requestedEngineVector = engineVector;
	//	double requiredPower = getOperatingPower();
	//	double requiredCPUThroughput = getOperatingCPUThroughput();
	//	return new BusRequirement(requiredPower, requiredCPUThroughput);
	//}





	





	@Override
	public String describe() {
		String description = " -- Simple Ion Engine -- \n" + 
				"Mass: " + getMass() + " Kg, Volume: " + getVolume() + " m3, Power: <thrust dep.> GJ/s, CPU: " + getNominalCPUThroughput() + " GFLOPS";
		description += "\n";
		description += "   ---------------------------------------------";
		return description;
	}


	


	@Override
	public void recieveMessage(Message message) {
		System.out.println("Message recieved by engine : " + getName() + "\n " + message.getMessage());

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
	public double getOperatingPower() {
		return getRequiredPower(this.powerLevel);
	}
	
	@Override
	public double getOperatingCPUThroughput() {
		return getRequiredCPUThroughput(this.powerLevel);
	}



}
