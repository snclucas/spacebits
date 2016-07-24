package org.spacebits.components.comms;

import org.spacebits.algorithm.Model;
import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public class RadioCommunicator extends AbstractCommunicationComponent {
	
	public static TypeInfo typeID = new TypeInfo("RadioCommunicator");

	public RadioCommunicator(String name, BusComponentSpecification busResourceSpecification, Model propagationModel) {
		super(name, busResourceSpecification, propagationModel);
	}

	@Override
	public String describe() {
		return "RF communication device";
	}

	
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", systemComputer.getUniversalTime(), Status.OK);
		return systemStatus; 
	}

	@Override
	public double getOperatingPower() {
		return getNominalPower();
	}
	
	@Override
	public double getOperatingCPUThroughput() {
		// Nominal and operation CPU are the same
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
