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
import org.spacebits.structures.hulls.Hull;
import org.spacebits.structures.hulls.HullFactory;

public class SpacecraftFirmwareTest {

	
	@Test
	public void testSpacecraftFirmwar() {
		
		Hull hull = HullFactory.getHull("Shuttle");
		
		
		Spacecraft spacecraft = new SimpleSpacecraft("Test spacecraft", hull);
		Bus spacecraftBus = spacecraft.getSpacecraftBus();
		
		boolean hasComputer = SpacecraftFirmware.bootstrapSystemComputer(spacecraftBus);
		
		assertEquals(false, hasComputer);
		
		List<CommunicationComponent> commComponents = SpacecraftFirmware.getCommunicationDevices(spacecraftBus);
		assertEquals("Number of communication components should be 0", 0, commComponents.size());
		
		List<Engine> engines = SpacecraftFirmware.getEngines(spacecraftBus);
		assertEquals("Number of engines should be 0", 0, engines.size());
		
		List<SystemComputer> computers = SpacecraftFirmware.getComputers(spacecraftBus);
		assertEquals("Number of computers should be 0", 0, computers.size());
		
		
		SystemComputer systemComputer = ComputerFactory.getComputer(BasicSystemComputer.type());	
		spacecraftBus.addComponent(systemComputer);
		
		hasComputer = SpacecraftFirmware.bootstrapSystemComputer(spacecraftBus);
		
		assertEquals(true, hasComputer); 
		
		computers = SpacecraftFirmware.getComputers(spacecraftBus);
		assertEquals("Number of computers should be 1", 1, computers.size());
		
		
		List<SpacecraftBusComponent> components = SpacecraftFirmware.findBusComponentByType(spacecraftBus, SystemComputer.type());
		SpacecraftBusComponent component = components.get(0);
		assertEquals("", component, systemComputer);
		
		
		
		// New spacecraft
		spacecraft = SpacecraftFactory.getSpacecraft("Shuttle");
		spacecraftBus = spacecraft.getSpacecraftBus();
		
		engines = SpacecraftFirmware.getEngines(spacecraftBus);
		assertEquals("Number of engines should be 1", 1, engines.size());
		
		commComponents = SpacecraftFirmware.getCommunicationDevices(spacecraftBus);
		assertEquals("Number of communication components should be 1", 1, commComponents.size());
		
		
		double totalCPUAvailable = SpacecraftFirmware.getTotalCPUThroughputAvailable(spacecraftBus);
		 
		assertEquals("Total CPU throughput available not correct", systemComputer.getMaxCPUThroughput(), totalCPUAvailable, 0.00001);
		
		double expectedTotalPowerAvailable = 0.0;
		for(SpacecraftBusComponent scComponent : SpacecraftFirmware.getPowerGenerators(spacecraftBus))
			expectedTotalPowerAvailable += ((PowerGenerator)scComponent).getMaximumPowerOutput();
			
		
		double totalPower = SpacecraftFirmware.getTotalPowerAvailable(spacecraftBus);
		
		assertEquals("Total power available not correct", expectedTotalPowerAvailable, totalPower, 0.00001);
		
		
		double currentPowerRequirement = SpacecraftFirmware.getTotalCurrentPower(spacecraftBus);
		double expectedPowerRequirement = 0.0;
		for(SpacecraftBusComponent scComponent : spacecraftBus.getComponents())
			expectedPowerRequirement += scComponent.getCurrentPower();
		
		assertEquals("Current power available not correct", expectedPowerRequirement, currentPowerRequirement, 0.00001);
		
		
		double currentCPURequirement = SpacecraftFirmware.getTotalCurrentCPUThroughput(spacecraftBus);
		double expectedCPURequirement = 0.0;
		for(SpacecraftBusComponent scComponent : spacecraftBus.getComponents())
			expectedCPURequirement += scComponent.getCurrentCPUThroughput();
		
		assertEquals("Current CPU available not correct", expectedCPURequirement, currentCPURequirement, 0.00001);
		
		
				
		
	}
	

}
