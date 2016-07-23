package org.braincycles.spacebits.components.computers;

import static org.junit.Assert.assertEquals;

import org.braincycles.spacebits.components.comms.Status;
import org.braincycles.spacebits.components.propulsion.Engine;
import org.braincycles.spacebits.components.propulsion.EngineVector;
import org.braincycles.spacebits.components.propulsion.thrust.SimpleIonEngine;
import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.software.PropulsionManagementSoftware;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.BusRequirement;
import org.braincycles.spacebits.spacecraft.OperationalSpecification;
import org.braincycles.spacebits.spacecraft.PhysicalSpecification;
import org.braincycles.spacebits.spacecraft.SimpleSpacecraft;
import org.braincycles.spacebits.spacecraft.Spacecraft;
import org.braincycles.spacebits.spacecraft.SpacecraftFactory;
import org.braincycles.spacebits.status.SystemStatusMessage;
import org.braincycles.spacebits.structures.hulls.Hull;
import org.braincycles.spacebits.structures.hulls.HullFactory;
import org.junit.Test;


public class SystemComputerTest {

	double mass = 25.0 * Unit.kg;
	double volume = 1.0 * Unit.m3;
	double nominalPower = 1 * Unit.kW; 
	double nominalCpu = 10 * Unit.kFLOP;
	double maximumThrust = 1 * Unit.N;
	double powerAtMaximumThrust = 1 * Unit.MW;
	double cpuThroughputAtMaximumThrust = nominalCpu;
	boolean vectored = false;

	BusComponentSpecification busSpecs = new BusComponentSpecification(
			new PhysicalSpecification(mass, volume),
			new OperationalSpecification(nominalPower, nominalCpu, powerAtMaximumThrust, cpuThroughputAtMaximumThrust));

	Hull hull = HullFactory.getHull("Shuttle");

	Spacecraft spacecraft = new SimpleSpacecraft(SpacecraftFactory.SHUTTLE, hull);

	//Align along axis of spacecraft
	EngineVector engineVector = new EngineVector(1,0,0);

	Engine engine =  new SimpleIonEngine(
			"Test engine", busSpecs,
			maximumThrust, engineVector, vectored);




	@Test
	public void testRequestOperation() {

		MockSystemComputer computer = new MockSystemComputer("Test computer", busSpecs, 
				10 * Unit.GFLOP, spacecraft);

		computer.totalPowerAvailable = 1 * Unit.kW;
		computer.totalCPUAvailable = 1 * Unit.GFLOP;

		BusRequirement busRequirement = new BusRequirement(100 * Unit.W, 100 * Unit.MFLOP);
		SystemStatusMessage message = computer.requestOperation(computer, busRequirement);
		assertEquals("Should be permitted", Status.PERMITTED, message.getStatus());

		busRequirement = new BusRequirement(1100 * Unit.W, 100 * Unit.MFLOP);
		message = computer.requestOperation(computer, busRequirement);
		assertEquals("Should be NOT_ENOUGH_POWER", Status.NOT_ENOUGH_POWER, message.getStatus());
		
		busRequirement = new BusRequirement(110 * Unit.W, 10 * Unit.GFLOP);
		message = computer.requestOperation(computer, busRequirement);
		assertEquals("Should be NOT_ENOUGH_POWER", Status.NOT_ENOUGH_CPU, message.getStatus());
	}



	@Test
	public void testSystemComputer() {


		SystemComputer computer = new BasicSystemComputer("Test computer", busSpecs, 
				10 * Unit.GFLOP, spacecraft);



		assertEquals("Computer category incorrect", SystemComputer.categoryID, computer.getCategoryId());
		assertEquals("Computer type ["+ computer.describe() +"] incorrect", BasicSystemComputer.typeID, computer.getTypeId());


		assertEquals("Incorrect spacecraft bus", spacecraft, computer.getSpacecraftBus());

		//computer.checkSystems()

	}




	@Test
	public void testEngineControl() {





		SystemComputer systemComputer =
				(SystemComputer) ComputerFactory.getComputer(BasicSystemComputer.typeID.toString());

		spacecraft.addComponent(systemComputer);

		spacecraft.addComponent(engine);

		spacecraft.online();

		((PropulsionManagementSoftware)spacecraft.getSystemComputer().getSoftware(PropulsionManagementSoftware.typeID)).callDrive(70, engine.getId());



	}






}
