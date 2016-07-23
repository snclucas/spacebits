package org.braincycles.spacebits.components.energygeneration;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.comms.Status;
import org.braincycles.spacebits.software.Message;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.status.SystemStatus;

public class SubspacePowerExtractor extends AbstractPowerGenerator {
	
	public static TypeInfo typeID = new TypeInfo("SubEtherPowerExtractor");
	
	private double maximumPowerOutput;
	
	public SubspacePowerExtractor(String name, BusComponentSpecification busResourceSpecification, double maximumPowerOutput) {
		super(name, busResourceSpecification);
		this.maximumPowerOutput = maximumPowerOutput;
	}
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	
	@Override
	public double getOperatingPower() {
		return getNominalPower();
	}
	
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", systemComputer.getUniversalTime(), Status.OK);
		return systemStatus; 
	}
	
	
	@Override
	public double getOperatingCPUThroughput() {
		// Nominal and operation CPU are the same for this hull
		return getNominalCPUThroughput();
	}
	
	


	@Override
	public SystemStatus runDiagnostics(int level) {
		//Nothing really to diagnose with this simple hull
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		return systemStatus;
	}

	@Override
	public void recieveMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String describe() {
		return toString();
	}


	@Override
	public String toString() {
		return "SubEtherPowerGenerator [maximumPowerOutputFromEther="
				+ maximumPowerOutput + "]";
	}


	@Override
	public double getMaximumPowerOutput() {
		return maximumPowerOutput;
	}


}
