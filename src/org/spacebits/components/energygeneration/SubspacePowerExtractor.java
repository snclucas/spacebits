package org.spacebits.components.energygeneration;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.physics.Unit;
import org.spacebits.software.Message;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public class SubspacePowerExtractor extends AbstractPowerGenerator {
	
	private double arrayArea;
	private double efficiency;
	
	public static TypeInfo type() {
		return new TypeInfo("SubspacePowerExtractor");
	}

	
	public SubspacePowerExtractor(String name, BusComponentSpecification busResourceSpecification, 
			double arrayArea, double efficiency) {
		super(name, busResourceSpecification);
		this.arrayArea = arrayArea;
		this.efficiency = efficiency;
		this.maxPower = arrayArea*efficiency*10.0*Unit.kW.value();
	}
	
	
	@Override
	public TypeInfo getType() {
		return type();
	}
	
	
	@Override
	public double getCurrentPower() {
		return getNominalPower();
	}
	
	
	public double getArrayArea() {
		return arrayArea;
	}

	public void setArrayArea(double arrayArea) {
		this.arrayArea = arrayArea;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
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
				+ getMaximumPowerOutput() + "]";
	}
	
	
	@Override
	public double getPowerOutput() {
		return 0;
	}

}
