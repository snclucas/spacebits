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

public class BasicSpacecraftFirmware {

	public static boolean bootstrapSystemComputer(Spacecraft spacecraftBus) { 
		int systemComputerIndex = findSystemComputerIndex(spacecraftBus);
		boolean hasSystemComputer = systemComputerIndex >= 0;
		if(hasSystemComputer) {
			SystemComputer systemComputer = (SystemComputer) spacecraftBus.getComponents().get(systemComputerIndex);
			spacecraftBus.setSystemComputer(systemComputer);
			systemComputer.registerSpacecraftBus(spacecraftBus);
		}
		return hasSystemComputer;
	}
	
	
	public static List<SpacecraftBusComponent> findBusComponent(Spacecraft spacecraftBus, TypeInfo componentType) {
		List<SpacecraftBusComponent> componentResults = new ArrayList<SpacecraftBusComponent>();
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			if(component.getCategoryId() == componentType)
				componentResults.add(component);
		return componentResults;
	}

	
	private static int findSystemComputerIndex(Spacecraft spacecraftBus) {
		List<SpacecraftBusComponent> components = spacecraftBus.getComponents();
		for(int i = 0; i<components.size();i++ ) {
			if(components.get(i) instanceof SystemComputer)
				return i;
		}
		return -1;
	}


	public static List<SystemStatusMessage> scanSpacecraftComponents(Spacecraft spacecraftBus) {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			component.accept(spacecraftBus);
		return systemStatusMessages;
	}


	public static List<SystemComputer> getComputers(Spacecraft spacecraftBus) {
		List<SystemComputer> computers = new ArrayList<SystemComputer>();		
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			if(component instanceof SystemComputer)
				computers.add((SystemComputer)component);
		return computers;
	}

	
	public static List<PowerGenerator> getPowerGenerators(Spacecraft spacecraftBus) {
		List<PowerGenerator> powerGenerators = new ArrayList<PowerGenerator>();		
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			if(component instanceof PowerGenerator)
				powerGenerators.add((PowerGenerator)component);
		return powerGenerators;
	}
	

	public static List<CommunicationComponent> getCommunicationDevices(Spacecraft spacecraftBus) {
		List<CommunicationComponent> communicationComponents = new ArrayList<CommunicationComponent>();		
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			if(component instanceof CommunicationComponent)
				communicationComponents.add((CommunicationComponent)component);
		return communicationComponents;
	}


	public static List<Engine> getEngines(Spacecraft spacecraftBus) {
		List<Engine> engines = new ArrayList<Engine>();		
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			if(component instanceof Engine)
				engines.add((Engine)component);
		return engines;
	}


	public static double getTotalPowerAvailable(Spacecraft spacecraftBus) {
		double sumOfAvailablePowerFromGenerators = 0.0;
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			if(component instanceof PowerGenerator)
				sumOfAvailablePowerFromGenerators += ((PowerGenerator)component).getMaximumPowerOutput();
		return sumOfAvailablePowerFromGenerators;
	}


	public static double getTotalCPUThroughputAvailable(Spacecraft spacecraftBus) {
		double sumOfAvailableCPUThroughputFromComputers = 0.0;
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			if(component instanceof SystemComputer)
				sumOfAvailableCPUThroughputFromComputers += ((SystemComputer)component).getMaxCPUThroughput();
		return sumOfAvailableCPUThroughputFromComputers;
	}
	
	
	public static double getCurrentSpacecraftBusPowerRequirement(Spacecraft spacecraftBus) {
		double currentSystemPowerRequirement = 0.0;
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			currentSystemPowerRequirement += component.getOperatingPower();
		return currentSystemPowerRequirement;
	}

	
	public static double getCurrentSpacecraftBusCPUThroughputRequirement(Spacecraft spacecraftBus) {
		double currentSystemCPUThroughputRequirement = 0.0;
		for(SpacecraftBusComponent component : spacecraftBus.getComponents())
			currentSystemCPUThroughputRequirement += component.getOperatingCPUThroughput();
		return currentSystemCPUThroughputRequirement;
	}

	 
	class SumUtil {
		public double amount = 0;
	}
	
}


