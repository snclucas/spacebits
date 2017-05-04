package org.spacebits.components;

import org.spacebits.components.comms.Status;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.spacecraft.Bus;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;
import org.spacebits.status.SystemStatusMessage;


public abstract class AbstractBusComponent implements SpacecraftBusComponent, PhysicalComponent {

	protected boolean online;
	
	protected String name;

	protected Bus bus;
	
	protected BusComponentSpecification busResourceSpecification;
	

	public AbstractBusComponent(String name, BusComponentSpecification busResourceSpecification) {
		super();
		this.name = name;
		this.busResourceSpecification = busResourceSpecification;
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

	
	public double getMass() {
		return busResourceSpecification.getMass();
	}

	
	public void setMass(double mass) {
		busResourceSpecification.setMass(mass);
	}

	
	public double getVolume() {
		return busResourceSpecification.getVolume();
	}

	
	public void setVolume(double volume) {
		busResourceSpecification.setVolume(volume);
	}

	
	public double getNominalPower() {
		return busResourceSpecification.getNominalPower();
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
	

	public SystemComputer getSystemComputer() {
		return bus.getSystemComputer();
	}

	//@Override
	//public SystemStatusMessage registerWithSystemComputer(SystemComputer systemComputer) {
	//	getSystemComputer().registerSpacecraftComponents()
	//	this.systemComputer = systemComputer;
	//	return new SystemStatusMessage(this,getName() + " registering with systemcomputer", systemComputer.getUniversalTime(), Status.INFO);
	//}
	
	
	public boolean isRegisteredWithSystemComputer() {
		return getSystemComputer() != null;
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