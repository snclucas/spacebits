package org.spacebits.components.comms;

import org.spacebits.algorithm.Model;
import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public class SubSpaceCommunicator extends AbstractCommunicationComponent {
	
	public static TypeInfo typeID = new TypeInfo("SubSpaceCommunicator");

	public SubSpaceCommunicator(String name, BusComponentSpecification busResourceSpecification, Model propagationModel) {
		super(name, busResourceSpecification, propagationModel);
	}

	@Override
	public String describe() {
		return "Sub-space communication device";
	}
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", systemComputer.getUniversalTime(), Status.OK);
		return systemStatus; 
	}
	
	public TypeInfo getTypeId() {
		return typeID;
	}

	
	@Override
	public double getOperatingPower() {
		return getNominalPower();
	}
	
	@Override
	public double getOperatingCPUThroughput() {
		// Nominal and operation CPU are the same for this XX change
		return getNominalCPUThroughput();
	}
	
	
	@Override
	public SystemStatus runDiagnostics(int level) {
		SystemStatus systemStatus = new SystemStatus(this);
		
		if(propagationModel == null)
			systemStatus.addSystemMessage(
					"Level " + level + "diagnostics : Problem. No propagation model.", systemComputer.getUniversalTime(), Status.PROBLEM);
		else 
			systemStatus.addSystemMessage(
					"Running diagnostics [level " + level + "].", systemComputer.getUniversalTime(), Status.OK);
		
		return systemStatus;
	}
	
	
	
	
}
