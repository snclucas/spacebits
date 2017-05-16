package org.spacebits.components.energygeneration;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.software.Message;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public class SubspacePowerExtractor extends AbstractPowerGenerator {
	
	public static TypeInfo type() {
		return new TypeInfo("SubspacePowerExtractor");
	}
	
	private double maximumPowerOutput;
	
	public SubspacePowerExtractor(String name, BusComponentSpecification busResourceSpecification, double maximumPowerOutput) {
		super(name, busResourceSpecification);
		this.maximumPowerOutput = maximumPowerOutput;
	}
	
	
	@Override
	public TypeInfo getType() {
		return type();
	}
	
	
	@Override
	public double getCurrentPower() {
		return getNominalPower();
	}
	
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getSystemComputer().getUniversalTime(), Status.OK);
		return systemStatus; 
	}
	
	
	@Override
	public double getCurrentCPUThroughput() {
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
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
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
