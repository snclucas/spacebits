package org.braincycles.spacebits.components.energygeneration;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.comms.Status;
import org.braincycles.spacebits.software.Message;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.status.SystemStatus;

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
	public double getOperatingPower() {
		// Nominal and operation power are the same for this hull
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
	public void recieveMessage(Message message) {
		// TODO Auto-generated method stub
		
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
