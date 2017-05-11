package org.spacebits.components.energygeneration;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.software.Message;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public class SimpleSolarArray extends AbstractPowerGenerator {
	
	public static TypeInfo typeID = new TypeInfo("SimpleSolarArray");

	private double arrayArea;
	private double efficiency;
	private double lightFlux;
	
	
	
	public SimpleSolarArray(String name, BusComponentSpecification busResourceSpecification, double arrayArea, double efficiency) {
		super(name, busResourceSpecification);
	}
	

	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}



	@Override
	public double getCurrentPower() {
		// Nominal and operation power are the same for this hull
		return getNominalPower();
	}
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		return systemStatus; 
	}
	

	@Override
	public double getCurrentCPUThroughput() {
		// Nominal and operation CPU are the same for this hull
		return getNominalCPUThroughput();
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


	public void setLightFlux(double lightFlux) {
		this.lightFlux = lightFlux;
	}
	
	
	@Override
	public double getMaximumPowerOutput() {
		return arrayArea*efficiency*lightFlux;
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
		return "SimpleSolarArray [arrayArea=" + arrayArea + ", efficiency="
				+ efficiency + ", lightFlux=" + lightFlux + "]";
	}

	

}
