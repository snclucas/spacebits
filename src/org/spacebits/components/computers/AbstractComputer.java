package org.spacebits.components.computers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.software.Message;
import org.spacebits.software.Software;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.Bus;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.LocalBus;
import org.spacebits.status.SystemStatus;
import org.spacebits.status.SystemStatusMessage;

public abstract class AbstractComputer extends AbstractBusComponent implements SystemComputer {
	
	protected Map<TypeInfo, Software> loadedSoftware;
	
	protected double maxCPUThroughput;
	protected Bus spacecraftBus;
	protected boolean isOnSpacecraftbus = false;
	
	public AbstractComputer(String name, BusComponentSpecification busResourceSpecification, double maxCPUThroughput, Bus spacecraftBus) {
		super(name, busResourceSpecification);
		this.maxCPUThroughput = maxCPUThroughput;
		this.spacecraftBus = spacecraftBus;
		loadedSoftware = new HashMap<TypeInfo, Software>();
	}
	
	public AbstractComputer(String name, BusComponentSpecification busResourceSpecification, double maxCPUThroughput) {
		super(name, busResourceSpecification);
		this.maxCPUThroughput = maxCPUThroughput;
		this.spacecraftBus = new LocalBus("LocalBus");
		loadedSoftware = new HashMap<TypeInfo, Software>();
	}
	
	
	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}
	
	
	public Bus getSpacecraftBus() {
		return spacecraftBus;
	}





	public void setSpacecraftBus(Bus spacecraftBus) {
		this.spacecraftBus = spacecraftBus;
		this.isOnSpacecraftbus = true;
	}
	
	

	
	
	@Override
	public boolean isOnSpacecraftBus() {
		return isOnSpacecraftbus;
	}


	@Override
	public SystemStatusMessage loadSoftware(Software software) {
		software.setComputer(this);
		if(loadedSoftware.put(software.getTypeId(), software) != null)
			return new SystemStatusMessage(this, software.getDescription() + " software loaded", getUniversalTime(), Status.OK);
		else 
			return new SystemStatusMessage(this, software.getDescription() + " software replaced exisiting software", getUniversalTime(), Status.OK);
	}

	
	@Override
	public Software getSoftware(TypeInfo softwareType) {
		return loadedSoftware.get(softwareType);
	}

	
	@Override
	public boolean hasSoftware(TypeInfo softwareType) {
		return loadedSoftware.get(softwareType) != null;
	}

	
	


	@Override
	public SystemStatus online() {
		//SystemStatus systemStatus = super.online();
		SystemStatus systemStatus = new SystemStatus(this);
		
		if(spacecraftBus == null)
			systemStatus.addSystemMessage("No spacecraft bus found.", getUniversalTime(), Status.CRITICAL);
		
		if(loadedSoftware.size() == 0)
			systemStatus.addSystemMessage("No interface software loaded", getUniversalTime(), Status.WARNING);
		
		return systemStatus;
	}





	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by computer: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}
	
	
	

	public double getMaxCPUThroughput() {
		return maxCPUThroughput;
	}


	public void setMaxCPUThroughput(double maxCPUThroughput) {
		this.maxCPUThroughput = maxCPUThroughput;
	}

	@Override
	public void registerSpacecraftBus(Bus spacecraftBus) {
		this.spacecraftBus = spacecraftBus;
	}
	
	
	
	@Override
	public double getUniversalTime() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int millisecond = cal.get(Calendar.MILLISECOND);
		
		//Change this;
		return ((year+0) + day/365.0 + 
				(hour/(365*24.0)) + 
				(minute/(365.0*24.0*60.0)) + 
				(second/(365.0*86400.0)) + 
				(millisecond/(365.0*86400000.0)));
	}

	
	
	@Override
	public double getTotalPowerAvailable() {
		return spacecraftBus.getTotalPowerAvailable();
	}
	
	
	@Override
	public double getTotalCPUThroughputAvailable() {
		return spacecraftBus.getTotalCPUThroughputAvailable();
	}
	
	
	@Override
	public double getCurrentPowerRequirement() {
		return spacecraftBus.getCurrentPowerRequirement();
	}

	
	@Override
	public double getCurrentCPUThroughputRequirement() {
		return spacecraftBus.getCurrentCPUThroughputRequirement();
	}

}
