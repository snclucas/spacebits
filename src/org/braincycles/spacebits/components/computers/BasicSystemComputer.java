package org.braincycles.spacebits.components.computers;

import java.util.ArrayList;
import java.util.List;

import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.comms.Status;
import org.braincycles.spacebits.software.MessageMediator;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.BusRequirement;
import org.braincycles.spacebits.spacecraft.Spacecraft;
import org.braincycles.spacebits.status.SystemStatus;
import org.braincycles.spacebits.status.SystemStatusMessage;

public class BasicSystemComputer extends AbstractComputer implements SystemComputer {
	
	public static TypeInfo typeID = new TypeInfo("BasicSystemComputer");
	
	private MessageMediator messagingSystem;

	private List<SystemStatusMessage> systemMessages;
	
	
	public BasicSystemComputer(String name, BusComponentSpecification busResourceSpecification, double maxCPUThroughput, Spacecraft spacecraftBus) {
		super(name, busResourceSpecification, maxCPUThroughput, spacecraftBus);
		systemMessages = new ArrayList<SystemStatusMessage>();
	}
	
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	
	@Override
	public double getOperatingPower() {
		return getNominalPower();
	}
	
	@Override
	public double getOperatingCPUThroughput() {
		return getNominalCPUThroughput();
	}
	
	
	@Override
	public SystemStatusMessage requestOperation(SpacecraftBusComponent component, BusRequirement busRequirement) {
	 	//Remove current component power and add back the new requested power
		double newBusPowerRequirement = getCurrentPowerRequirement() - component.getNominalPower() + busRequirement.getPowerRequirement();
		double newBusCPUThroughputRequirement = getCurrentCPUThroughputRequirement() - component.getNominalCPUThroughput() + busRequirement.getCPUThroughputRequirement();
		
		//System.out.println("In request " + newBusPowerRequirement + " " + getTotalPowerAvailable());
		//System.out.println("In request " + newBusCPUThroughputRequirement + " " + getTotalCPUThroughputAvailable());
		
		if((newBusPowerRequirement > getTotalPowerAvailable()))
			return new SystemStatusMessage(this, "Not enough bus power to perform operation, " + newBusPowerRequirement + " needed, " + getTotalPowerAvailable() + " available", getUniversalTime(), Status.NOT_ENOUGH_POWER);
		else if((newBusCPUThroughputRequirement > getTotalCPUThroughputAvailable()))
			return new SystemStatusMessage(this, "Not enough bus CPU throughput to perform operation", getUniversalTime(), Status.NOT_ENOUGH_CPU);
		else
			return new SystemStatusMessage(this, "Operation permitted", getUniversalTime(), Status.PERMITTED);
	}
	
	
	

	@Override
	public List<SpacecraftBusComponent> findBusComponent(TypeInfo componentType) {
		return spacecraftBus.findBusComponent(componentType);
	}


	public SystemStatus online() {
		SystemStatus status = super.online();
		// Scan spacecraft bus components
		status.mergeSystemMessages(scanSpacecraftBusForComponents());
		// Register components with the system computer
		status.mergeSystemMessages(registerSpacecraftComponents());
		// Check all systems
		status.mergeSystemMessages(checkSystems());

		status.addSystemMessage(addSystemMessage(this, "Onlining spacecraft components", Status.OK));
		for(SpacecraftBusComponent component : spacecraftBus.getComponents()) {
			if(component != this) { // Ignore this computer
				SystemStatus busComponentStatus = component.online();
				status.mergeSystemStatus(busComponentStatus);
			}
		}

		if(status.hasCriticalMessages()) {
			status.addSystemMessage(addSystemMessage(this, this.getName() + 
					" cannot be onlined. Critical errors.", Status.CRITICAL));
			online = false;
		}
		else if(status.hasWarningMessages()) {
			status.addSystemMessage(addSystemMessage(this, this.getName() + 
					" online but with warnings.", Status.WARNING));
			online = true;
		}
		else if(status.isOK()) {
			status.addSystemMessage(addSystemMessage(this, this.getName() + 
					" online, no warnings or critical errors.", Status.INFO));
			online = true;
		}
		return status;
	}

	
	public List<SystemStatusMessage> scanSpacecraftBusForComponents() {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();
		systemStatusMessages.add(new SystemStatusMessage(this, "Scanning spacecraft bus for components.", getUniversalTime(), Status.INFO));
		//this.components = spacecraftBus.getComponents();
		return systemStatusMessages;
	}

	

	@Override
	public void registerSpacecraftBus(Spacecraft spacecraftBus) {
		this.spacecraftBus = spacecraftBus;
		addSystemMessage(this, "Spacecraft bus " + spacecraftBus.getName() + " registered with system computer [" + this.getName() + "]", Status.OK);
	}
	


	public List<SystemStatusMessage> registerSpacecraftComponents() {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();
		
		for(SpacecraftBusComponent component : spacecraftBus.getComponents()) {
			//Set this as the registered computer in the component
			systemStatusMessages.add(component.registerSystemComputer(this));
			// Register the component with the messaging system
			messagingSystem.addComponent(component);
		}
		return systemStatusMessages;
	}


	@Override
	public SystemStatus runDiagnostics(int level) {
		//XXX Add something here
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		return systemStatus;
	}



	@Override
	public String describe() {
		return "System computer";
	}




	



	


	@Override
	public List<SystemStatusMessage> checkSystems() {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();

		double maximumAvailableVolume = spacecraftBus.getVolume();
		
		

		systemStatusMessages.add(
				new SystemStatusMessage(this, "Total vessel mass: " + 
						spacecraftBus.getTotalMassOfSpacecraftBusComponents(), getUniversalTime(), Status.INFO));
		
		systemStatusMessages.add(
				new SystemStatusMessage(this, "Available hull volume: " + maximumAvailableVolume, getUniversalTime(), Status.INFO));
		systemStatusMessages.add(
				new SystemStatusMessage(this, "Required hull volume: " + spacecraftBus.getTotalVolumeOfSpacecraftBusComponents(), getUniversalTime(), Status.INFO));
		
		if(maximumAvailableVolume < spacecraftBus.getTotalVolumeOfSpacecraftBusComponents())
			systemStatusMessages.add(
					new SystemStatusMessage(this, "Not enough space", getUniversalTime(), Status.CRITICAL));

		systemStatusMessages.add(
				new SystemStatusMessage(this, "Available CPU throughput: " + getTotalCPUThroughputAvailable(), getUniversalTime(), Status.INFO));
		systemStatusMessages.add(
				new SystemStatusMessage(this, "Required CPU throughput: " + getCurrentCPUThroughputRequirement(), getUniversalTime(), Status.INFO));
		
		if(spacecraftBus.getTotalCPUThroughputAvailable() < getCurrentCPUThroughputRequirement())
			systemStatusMessages.add(new SystemStatusMessage(this, "Not enough CPU", getUniversalTime(), Status.PROBLEM));

		return systemStatusMessages;
	}

	
	
	
	

	@Override
	public SystemStatusMessage addSystemMessage(SpacecraftBusComponent component, String message, Status status) {
		SystemStatusMessage systemStatusMessage = new SystemStatusMessage(component, message, getUniversalTime(), status);
		this.systemMessages.add(systemStatusMessage);
		return systemStatusMessage;
	}



	
	
	
	
	
	// -- Getters and setters -- //
	
	
	

	@Override
	public List<SystemStatusMessage> getSystemMessages() {
		return this.systemMessages;
	}
	
	
	
	@Override
	public MessageMediator getMessagingSystem() {
		return messagingSystem;
	}


	@Override
	public void setMessagingSystem(MessageMediator messagingSystem) {
		this.messagingSystem = messagingSystem;
	}
	

}
