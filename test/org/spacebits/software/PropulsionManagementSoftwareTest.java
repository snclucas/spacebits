package org.spacebits.software;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.Configuration;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.comms.Status;
import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.energygeneration.PowerGenerationFactory;
import org.spacebits.components.energygeneration.PowerGenerator;
import org.spacebits.components.energygeneration.SubspacePowerExtractor;
import org.spacebits.components.propulsion.EngineFactory;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.components.propulsion.thrust.FuelConsumingEngine;
import org.spacebits.components.propulsion.thrust.SimpleThruster;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.data.SpacecraftDataProvider;
import org.spacebits.physics.Unit;
import org.spacebits.software.PropulsionManagementSoftware;
import org.spacebits.spacecraft.Bus;
import org.spacebits.spacecraft.SimpleSpacecraft;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftBus;
import org.spacebits.status.SystemStatusMessage;
import org.spacebits.structures.hulls.Hull;
import org.spacebits.structures.hulls.HullFactory;

public class PropulsionManagementSoftwareTest {
	SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();


	// Setup spacecraft bus
	Hull hull = HullFactory.getHull("Shuttle");
	Spacecraft spacecraft = new SimpleSpacecraft("Shuttle", hull);
	Bus spacecraftBus = new SpacecraftBus("Spacecraft bus",spacecraft); 
	



	@Test
	public void testEngineManagementSoftwareNoEngine() {
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.type());

		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.type());
		spacecraft.addComponent(powerGenerator);

		// Simple computer
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOP.value());
		spacecraft.addComponent(computer);
		
		
		PropulsionManagementSoftware engineManagementSoftware = 
				new PropulsionManagementSoftware("Test EngineManagementSoftware");
		
		computer.loadSoftware(engineManagementSoftware);

		
		
		assertEquals("Engine Management Software category incorrect", PropulsionManagementSoftware.categoryID, engineManagementSoftware.getCategory());
		assertEquals("Engine Management Software type incorrect", PropulsionManagementSoftware.typeID, engineManagementSoftware.getType());

		SystemStatusMessage systemMsg = engineManagementSoftware.callDrive(34, 23);
		assertEquals("No critical error status returned for drive", Status.CRITICAL, systemMsg.getStatus());

		SystemStatusMessage systemMsg2 = engineManagementSoftware.callStop(34);
		assertEquals("No critical error status returned for stop", Status.CRITICAL, systemMsg2.getStatus());

		SystemStatusMessage systemMsg3 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), 34);
		assertEquals("No critical error status returned for vector", Status.CRITICAL, systemMsg3.getStatus());

	}



	@Test
	public void testEngineManagementSoftware() {
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.type());

		// Setup spacecraft bus
		Hull hull = HullFactory.getHull("Shuttle");
		Spacecraft spacecraft = new SimpleSpacecraft("Shuttle", hull);

		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.type());
		spacecraft.addComponent(powerGenerator);

		// Simple computer
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOP.value());
		spacecraft.addComponent(computer);
		
		for(SpacecraftBusComponent component : computer.getSpacecraftBus().getComponents()) {
			System.out.println(component.getName() + " " +  component.getNominalPower() + " " + component.getNominalCPUThroughput());
		}
		
		
		System.out.println("No drive " + computer.getTotalCurrentPower() + " " + computer.getTotalPowerAvailable()
			+ " : "	+ computer.getCurrentCPUThroughput() + " " + computer.getTotalCPUThroughputAvailable());

		PropulsionManagementSoftware engineManagementSoftware = 
				new PropulsionManagementSoftware("Test EngineManagementSoftware");

		computer.loadSoftware(engineManagementSoftware);

		FuelConsumingEngine engine = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.type(), false);
		spacecraft.addComponent((SpacecraftBusComponent)engine);

		double powerLevel = 34.45 * Unit.percent.value();
		SystemStatusMessage systemMsg4 = engineManagementSoftware.callDrive(powerLevel, engine.getId());
		assertEquals("Critical error status returned for drive", Status.SUCCESS, systemMsg4.getStatus());
		assertEquals("Engine power level incorrect", powerLevel, engine.getPowerLevel(), 0.0001);
		
		System.out.println("Drive [" + engine.getPowerLevel() +  "] " + computer.getCurrentPower() + " " + computer.getTotalPowerAvailable());


		SystemStatusMessage systemMsg5 = engineManagementSoftware.callStop(engine.getId());
		assertEquals("Critical error status returned for stop", Status.SUCCESS, systemMsg5.getStatus());
		assertEquals("Engine power level incorrect", 0.0, engine.getPowerLevel(), 0.0001);
		
		System.out.println("Stop [" + engine.getPowerLevel() +  "] " + computer.getCurrentPower() + " " + computer.getTotalPowerAvailable());


		SystemStatusMessage systemMsg6 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine.getId());
		assertEquals("Critical error status returned for vector", Status.NOT_PERMITTED, systemMsg6.getStatus()); // passed false to engine factory

		FuelConsumingEngine engine2 = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.type(), true);
		spacecraft.addComponent((SpacecraftBusComponent)engine2);

		SystemStatusMessage systemMsg7 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine2.getId());
		assertEquals("Critical error status returned for vector", Status.SUCCESS, systemMsg7.getStatus()); // passed true to engine factory



	}

}
