package org.spacebits.spacecraft;

import java.util.ArrayList;
import java.util.List;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.CommunicationComponent;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.energygeneration.PowerGenerator;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.status.SystemStatusMessage;

public class SpacecraftFirmware {

	public boolean bootstrapSystemComputer(Bus bus) { 
		int systemComputerIndex = findSystemComputerIndex(bus);
		boolean hasSystemComputer = systemComputerIndex >= 0;
		if(hasSystemComputer) {
			SystemComputer systemComputer = (SystemComputer) bus.getBusComponents().get(systemComputerIndex);
			bus.setSystemComputer(systemComputer);
			systemComputer.registerSpacecraftBus(bus);
		}
		return hasSystemComputer;
	}
	
	
	public List<SpacecraftBusComponent> findBusComponent(Bus bus, TypeInfo componentType) {
		List<SpacecraftBusComponent> componentResults = new ArrayList<SpacecraftBusComponent>();
		for(SpacecraftBusComponent component : bus.getBusComponents())
			if(component.getCategoryId() == componentType)
				componentResults.add(component);
		return componentResults;
	}

	
	private int findSystemComputerIndex(Bus bus) {
		List<SpacecraftBusComponent> components = bus.getBusComponents();
		for(int i = 0; i<components.size();i++ ) {
			if(components.get(i) instanceof SystemComputer)
				return i;
		}
		return -1;
	}


	public List<SystemStatusMessage> scanSpacecraftComponents(Bus bus) {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();
		for(SpacecraftBusComponent component : bus.getBusComponents())
			component.accept(bus);
		return systemStatusMessages;
	}


	public List<SystemComputer> getComputers(Bus bus) {
		List<SystemComputer> computers = new ArrayList<SystemComputer>();		
		for(SpacecraftBusComponent component : bus.getBusComponents())
			if(component instanceof SystemComputer)
				computers.add((SystemComputer)component);
		return computers;
	}

	
	
	public List<PowerGenerator> getPowerGenerators(Bus bus) {
		List<PowerGenerator> powerGenerators = new ArrayList<PowerGenerator>();		
		for(SpacecraftBusComponent component : bus.getBusComponents())
			if(component instanceof PowerGenerator)
				powerGenerators.add((PowerGenerator)component);
		return powerGenerators;
	}
	

	public List<CommunicationComponent> getCommunicationDevices(Bus bus) {
		List<CommunicationComponent> communicationComponents = new ArrayList<CommunicationComponent>();		
		for(SpacecraftBusComponent component : bus.getBusComponents())
			if(component instanceof CommunicationComponent)
				communicationComponents.add((CommunicationComponent)component);
		return communicationComponents;
	}


	public List<Engine> getEngines(Bus bus) {
		List<Engine> engines = new ArrayList<Engine>();		
		for(SpacecraftBusComponent component : bus.getBusComponents())
			if(component instanceof Engine)
				engines.add((Engine)component);
		return engines;
	}


	public double getTotalPowerAvailable(Bus bus) {
		double sumOfAvailablePowerFromGenerators = 0.0;
		for(SpacecraftBusComponent component : bus.getBusComponents())
			if(component instanceof PowerGenerator)
				sumOfAvailablePowerFromGenerators += ((PowerGenerator)component).getMaximumPowerOutput();
		return sumOfAvailablePowerFromGenerators;
	}

	
	public double getTotalMaximumPowerOfComponents(Bus bus) {
		double sumOfAvailablePowerFromGenerators = 0.0;
		for(SpacecraftBusComponent component : bus.getBusComponents())
			if(component instanceof PowerGenerator)
				sumOfAvailablePowerFromGenerators += ((PowerGenerator)component).getMaximumPowerOutput();
		return sumOfAvailablePowerFromGenerators;
	}
	

	public double getTotalCPUThroughputAvailable(Bus bus) {
		double sumOfAvailableCPUThroughputFromComputers = 0.0;
		for(SpacecraftBusComponent component : bus.getBusComponents())
			if(component instanceof SystemComputer)
				sumOfAvailableCPUThroughputFromComputers += ((SystemComputer)component).getMaxCPUThroughput();
		return sumOfAvailableCPUThroughputFromComputers;
	}
	
	
	public double getCurrentSpacecraftBusPowerRequirement(Bus bus) {
		double currentSystemPowerRequirement = 0.0;
		for(SpacecraftBusComponent component : bus.getBusComponents())
			currentSystemPowerRequirement += component.getOperatingPower();
		return currentSystemPowerRequirement;
	}

	
	public double getCurrentSpacecraftBusCPUThroughputRequirement(Bus bus) {
		double currentSystemCPUThroughputRequirement = 0.0;
		for(SpacecraftBusComponent component : bus.getBusComponents())
			currentSystemCPUThroughputRequirement += component.getOperatingCPUThroughput();
		return currentSystemCPUThroughputRequirement;
	}

}


