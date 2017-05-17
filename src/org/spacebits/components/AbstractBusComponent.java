package org.spacebits.components;

import org.spacebits.components.comms.Status;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.exceptions.ComponentConfigurationException;
import org.spacebits.physics.Unit;
import org.spacebits.software.Message;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.Bus;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;
import org.spacebits.status.SystemStatusMessage;
import org.spacebits.universe.UniverseAware;


public abstract class AbstractBusComponent extends UniverseAware implements SpacecraftBusComponent {

	protected boolean online;
	
	protected String name;

	protected Bus spacecraftBus;
	
	protected SystemComputer systemComputer;
	
	protected BusComponentSpecification busResourceSpecification;
	
	private double currentPower;
	private double currentCPUThroughput;
	

	public AbstractBusComponent(String name, BusComponentSpecification busResourceSpecification) {
		super();
		this.name = name;
		this.busResourceSpecification = busResourceSpecification;
		this.currentPower = busResourceSpecification.getNominalPower();
		this.currentCPUThroughput = busResourceSpecification.getNominalCPUThroughout();
		this.online = false;
	}
	
	
	@Override
	public boolean isOnSpacecraftBus() {
		return spacecraftBus != null;
	}
	

	@Override
	public void registerWithBus(Bus bus) {
		this.spacecraftBus = bus;
	}
	
	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + ":\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}
	

	@Override
	public SystemStatusMessage registerSystemComputer(SystemComputer systemComputer) {
		this.systemComputer = systemComputer;
		return new SystemStatusMessage(this, this.name + " registered with " + systemComputer.getName(), getUniversalTime(), Status.INFO);
	}



	@Override
	public int getId() {
		return this.hashCode();
	}


	@Override
	public boolean isOnline() {
		return this.online;
	}
	

	@Override
	public void accept(ComponentVisitor componentVisitor) {
		componentVisitor.visit(this);
	}


	public String getName() {
		return this.name;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double getMass() {
		return busResourceSpecification.getMass();
	}
	
	@Override
	public double getMass(Unit unit) {
		return busResourceSpecification.getMass(unit);
	}

	
	public void setMass(double mass) {
		busResourceSpecification.setMass(mass);
	}

	@Override
	public double getVolume() {
		return busResourceSpecification.getVolume();
	}
	
	@Override
	public double getVolume(Unit unit) {
		return busResourceSpecification.getVolume(unit);
	}

	
	public void setVolume(double volume) {
		busResourceSpecification.setVolume(volume);
	}

	
	public double getNominalPower() {
		return busResourceSpecification.getNominalPower();
	}
	
	
	public double getNominalPower(double unit) {
		return busResourceSpecification.getNominalPower() / unit;
	}

	
	public void setNominalPower(double nominalPower) {
		busResourceSpecification.setNominalPower(nominalPower);
	}

	
	public double getNominalCPUThroughput() {
		return busResourceSpecification.getNominalCPUThroughout();
	}

	
	public void setNominalCPUThroughput(double nominalCPUThroughput) {
		busResourceSpecification.setNominalCPUThroughout(nominalCPUThroughput);
	}
	
	
	public double getMaximumOperationalPower() {
		return busResourceSpecification.getMaximumOperationalPower();
	}

	
	public void setMaximumOperationalPower(double maximumOperationalPower) {
		busResourceSpecification.setMaximumOperationalPower(maximumOperationalPower);
	}
	
	
	public double getMaximumOperationalCPUThroughput() {
		return busResourceSpecification.getMaximumOperationalCPUThroughput();
	}

	
	public void setMaximumOperationalCPUThroughput(double maximumOperationalCPUThroughput) {
		busResourceSpecification.setMaximumOperationalCPUThroughput(maximumOperationalCPUThroughput);
	}
	
	
	

	@Override
	public double getCurrentPower() {
		return currentPower * (isOnline()?1:0);
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return getCurrentPower() / unit.value();
	}


	@Override
	public double getCurrentCPUThroughput() {
		return currentCPUThroughput * (isOnline()?1:0);
	}




	public SystemComputer getSystemComputer() {
		if(isRegisteredWithSystemComputer() == false)
			throw new ComponentConfigurationException(this.name + " is not registered with the computer");
		return spacecraftBus.getSystemComputer();
	}

	//@Override
	//public SystemStatusMessage registerWithSystemComputer(SystemComputer systemComputer) {
	//	getSystemComputer().registerSpacecraftComponents()
	//	this.systemComputer = systemComputer;
	//	return new SystemStatusMessage(this,getName() + " registering with systemcomputer", systemComputer.getUniversalTime(), Status.INFO);
	//}
	
	
	public boolean isRegisteredWithSystemComputer() {
		return this.systemComputer != null;
	}


	@Override
	public String toString() {
		return getName();
	}

	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		if(this.isRegisteredWithSystemComputer())
			systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		else
			systemStatus.addSystemMessage(getName() + " not registered with system computer.", getUniversalTime(), Status.CRITICAL); 
		return systemStatus; 
	}


	@Override
	public double getUniversalTime() {
		return getSystemComputer().getUniversalTime();
	}
	
	

}
