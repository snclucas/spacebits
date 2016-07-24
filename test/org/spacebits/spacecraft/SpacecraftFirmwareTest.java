package org.spacebits.spacecraft;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.comms.CommunicationComponent;
import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.components.computers.ComputerFactory;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.energygeneration.PowerGenerator;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.spacecraft.BasicSpacecraftFirmware;
import org.spacebits.spacecraft.SimpleSpacecraft;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.structures.hulls.Hull;
import org.spacebits.structures.hulls.HullFactory;

public class SpacecraftFirmwareTest {

	
	@Test
	public void testSpacecraftFirmwar() {
		
		Hull hull = HullFactory.getHull("Shuttle");
		
		Spacecraft spacecraftBus = new SimpleSpacecraft("Test spacecraft", hull);
		
		boolean hasComputer = BasicSpacecraftFirmware.bootstrapSystemComputer(spacecraftBus);
		
		assertEquals(false, hasComputer);
		
		List<CommunicationComponent> commComponents = BasicSpacecraftFirmware.getCommunicationDevices(spacecraftBus);
		assertEquals("Number of communication components should be 0", 0, commComponents.size());
		
		List<Engine> engines = BasicSpacecraftFirmware.getEngines(spacecraftBus);
		assertEquals("Number of engines should be 0", 0, engines.size());
		
		List<SystemComputer> computers = BasicSpacecraftFirmware.getComputers(spacecraftBus);
		assertEquals("Number of computers should be 0", 0, computers.size());
		
		
		SystemComputer systemComputer = ComputerFactory.getComputer(BasicSystemComputer.typeID.toString());	
		spacecraftBus.addComponent(systemComputer);
		
		hasComputer = BasicSpacecraftFirmware.bootstrapSystemComputer(spacecraftBus);
		
		assertEquals(true, hasComputer); 
		
		computers = BasicSpacecraftFirmware.getComputers(spacecraftBus);
		assertEquals("Number of computers should be 1", 1, computers.size());
		
		
		
		SpacecraftBusComponent component = BasicSpacecraftFirmware.findBusComponent(spacecraftBus, SystemComputer.categoryID).get(0);
		assertEquals("", component, systemComputer);
		
		
		
		// New spacecraft
		spacecraftBus = SpacecraftFactory.getSpacecraft("Shuttle");
		
		engines = BasicSpacecraftFirmware.getEngines(spacecraftBus);
		assertEquals("Number of engines should be 1", 1, engines.size());
		
		commComponents = BasicSpacecraftFirmware.getCommunicationDevices(spacecraftBus);
		assertEquals("Number of communication components should be 1", 1, commComponents.size());
		
		
		double totalCPUAvailable = BasicSpacecraftFirmware.getTotalCPUThroughputAvailable(spacecraftBus);
		 
		assertEquals("Total CPU throughput available not correct", systemComputer.getMaxCPUThroughput(), totalCPUAvailable, 0.00001);
		
		double expectedTotalPowerAvailable = 0.0;
		for(SpacecraftBusComponent scComponent : BasicSpacecraftFirmware.getPowerGenerators(spacecraftBus))
			expectedTotalPowerAvailable += ((PowerGenerator)scComponent).getMaximumPowerOutput();
			
		
		double totalPower = BasicSpacecraftFirmware.getTotalPowerAvailable(spacecraftBus);
		
		assertEquals("Total power available not correct", expectedTotalPowerAvailable, totalPower, 0.00001);
		
		
		double currentPowerRequirement = BasicSpacecraftFirmware.getCurrentSpacecraftBusPowerRequirement(spacecraftBus);
		double expectedPowerRequirement = 0.0;
		for(SpacecraftBusComponent scComponent : spacecraftBus.getComponents())
			expectedPowerRequirement += scComponent.getOperatingPower();
		
		assertEquals("Current power available not correct", expectedPowerRequirement, currentPowerRequirement, 0.00001);
		
		
		double currentCPURequirement = BasicSpacecraftFirmware.getCurrentSpacecraftBusCPUThroughputRequirement(spacecraftBus);
		double expectedCPURequirement = 0.0;
		for(SpacecraftBusComponent scComponent : spacecraftBus.getComponents())
			expectedCPURequirement += scComponent.getOperatingCPUThroughput();
		
		assertEquals("Current CPU available not correct", expectedCPURequirement, currentCPURequirement, 0.00001);
		
		
				
		
	}
	

}
