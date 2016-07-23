package org.braincycles.spacebits.software;

import static org.junit.Assert.assertEquals;

import org.braincycles.spacebits.Configuration;
import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.comms.Status;
import org.braincycles.spacebits.components.computers.BasicSystemComputer;
import org.braincycles.spacebits.components.computers.SystemComputer;
import org.braincycles.spacebits.components.energygeneration.PowerGenerationFactory;
import org.braincycles.spacebits.components.energygeneration.PowerGenerator;
import org.braincycles.spacebits.components.energygeneration.SubspacePowerExtractor;
import org.braincycles.spacebits.components.propulsion.EngineFactory;
import org.braincycles.spacebits.components.propulsion.EngineVector;
import org.braincycles.spacebits.components.propulsion.thrust.FuelConsumingEngine;
import org.braincycles.spacebits.components.propulsion.thrust.SimpleThruster;
import org.braincycles.spacebits.data.SpacecraftComponentData;
import org.braincycles.spacebits.data.SpacecraftDataProvider;
import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.spacecraft.SimpleSpacecraft;
import org.braincycles.spacebits.spacecraft.Spacecraft;
import org.braincycles.spacebits.status.SystemStatusMessage;
import org.braincycles.spacebits.structures.hulls.Hull;
import org.braincycles.spacebits.structures.hulls.HullFactory;
import org.junit.Test;

public class PropulsionManagementSoftwareTest {
	SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();


	// Setup spacecraft bus
	Hull hull = HullFactory.getHull("Shuttle");
	Spacecraft spacecraft = new SimpleSpacecraft("Shuttle", hull);



	@Test
	public void testEngineManagementSoftwareNoEngine() {
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.typeID.toString());

		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.typeID.toString());
		spacecraft.addComponent(powerGenerator);

		// Simple computer
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOP, spacecraft);

		PropulsionManagementSoftware engineManagementSoftware = new PropulsionManagementSoftware("Test EngineManagementSoftware", computer);

		assertEquals("Engine Management Software category incorrect", PropulsionManagementSoftware.categoryID, engineManagementSoftware.getCategoryId());
		assertEquals("Engine Management Software type incorrect", PropulsionManagementSoftware.typeID, engineManagementSoftware.getTypeId());

		SystemStatusMessage systemMsg = engineManagementSoftware.callDrive(34, 23);
		assertEquals("No critical error status returned for drive", Status.CRITICAL, systemMsg.getStatus());

		SystemStatusMessage systemMsg2 = engineManagementSoftware.callStop(34);
		assertEquals("No critical error status returned for stop", Status.CRITICAL, systemMsg2.getStatus());

		SystemStatusMessage systemMsg3 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), 34);
		assertEquals("No critical error status returned for vector", Status.CRITICAL, systemMsg3.getStatus());

	}



	@Test
	public void testEngineManagementSoftware() {
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.typeID.toString());

		// Setup spacecraft bus
		Hull hull = HullFactory.getHull("Shuttle");
		Spacecraft spacecraft = new SimpleSpacecraft("Shuttle", hull);

		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.typeID.toString());
		spacecraft.addComponent(powerGenerator);

		// Simple computer
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOP, spacecraft);
		spacecraft.addComponent(computer);
		
		for(SpacecraftBusComponent component : computer.getSpacecraftBus().getComponents()) {
			System.out.println(component.getName() + " " +  component.getNominalPower() + " " + component.getNominalCPUThroughput());
		}
		
		
		System.out.println("No drive " + computer.getCurrentPowerRequirement() + " " + computer.getTotalPowerAvailable()
			+ " : "	+ computer.getCurrentCPUThroughputRequirement() + " " + computer.getTotalCPUThroughputAvailable());

		PropulsionManagementSoftware engineManagementSoftware = new PropulsionManagementSoftware("Test EngineManagementSoftware", computer);



		FuelConsumingEngine engine = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.typeID.toString(), false);
		spacecraft.addComponent((SpacecraftBusComponent)engine);

		double powerLevel = 34.45 * Unit.percent;
		SystemStatusMessage systemMsg4 = engineManagementSoftware.callDrive(powerLevel, engine.getId());
		assertEquals("Critical error status returned for drive", Status.SUCCESS, systemMsg4.getStatus());
		assertEquals("Engine power level incorrect", powerLevel, engine.getPowerLevel(), 0.0001);
		
		System.out.println("Drive [" + engine.getPowerLevel() +  "] " + computer.getCurrentPowerRequirement() + " " + computer.getTotalPowerAvailable());


		SystemStatusMessage systemMsg5 = engineManagementSoftware.callStop(engine.getId());
		assertEquals("Critical error status returned for stop", Status.SUCCESS, systemMsg5.getStatus());
		assertEquals("Engine power level incorrect", 0.0, engine.getPowerLevel(), 0.0001);
		
		System.out.println("Stop [" + engine.getPowerLevel() +  "] " + computer.getCurrentPowerRequirement() + " " + computer.getTotalPowerAvailable());


		SystemStatusMessage systemMsg6 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine.getId());
		assertEquals("Critical error status returned for vector", Status.NOT_PERMITTED, systemMsg6.getStatus()); // passed false to engine factory

		FuelConsumingEngine engine2 = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.typeID.toString(), true);
		spacecraft.addComponent((SpacecraftBusComponent)engine2);

		SystemStatusMessage systemMsg7 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine2.getId());
		assertEquals("Critical error status returned for vector", Status.SUCCESS, systemMsg7.getStatus()); // passed true to engine factory



	}

}
