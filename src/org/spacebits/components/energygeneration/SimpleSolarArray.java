package org.spacebits.components.energygeneration;

import org.spacebits.Configuration;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.data.EnvironmentDataProvider;
import org.spacebits.physics.Unit;
import org.spacebits.software.Message;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Universe;

public class SimpleSolarArray extends AbstractPowerGenerator {
	
	private EnvironmentDataProvider environmentDataProvider = Configuration.getEnvironmentDataProvider();
	
	private double arrayArea;
	private final double efficiency;
	
	public SimpleSolarArray(String name, BusComponentSpecification busResourceSpecification, 
			double arrayArea, double efficiency) {
		super(name, busResourceSpecification);
		this.arrayArea = arrayArea;
		this.efficiency = efficiency;
		this.maxPower = arrayArea*efficiency*10.0*Unit.kW.value();
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


	public double getEfficiency() {
		return efficiency;
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
				+ efficiency + ", lightFlux=" + getLightFlux() + "]";
	}


	@Override
	public double getPowerOutput() {
		return arrayArea*efficiency*getLightFlux();
	}

	
	private double getLightFlux() {
		Coordinates coordinates = Universe.getInstance()
				.getSpacecraftLocation(getSystemComputer().getSpacecraftBus().getSpacecraft().getIdent());
		return environmentDataProvider.getEnvironmentData(coordinates).getSolarFlux();
	}
	
	
	public static TypeInfo type() {
		return new TypeInfo("SimpleSolarArray");
	}
	
	@Override
	public TypeInfo getType() {
		return type();
	}
	

}
