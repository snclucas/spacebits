package org.spacebits.components.computers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.components.comms.Status;
import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.components.computers.ComputerFactory;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.components.propulsion.thrust.SimpleIonEngine;
import org.spacebits.physics.Unit;
import org.spacebits.software.PropulsionManagementSoftware;
import org.spacebits.spacecraft.Bus;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.BusRequirement;
import org.spacebits.spacecraft.OperationalSpecification;
import org.spacebits.spacecraft.PhysicalSpecification;
import org.spacebits.spacecraft.SimpleSpacecraft;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftBus;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.status.SystemStatusMessage;
import org.spacebits.structures.hulls.Hull;
import org.spacebits.structures.hulls.HullFactory;


public class SystemComputerTest {

	double mass = 25.0 * Unit.kg;
	double volume = 1.0 * Unit.m3;
	double nominalPower = 1 * Unit.kW; 
	double nominalCPU = 10 * Unit.kFLOP;
	double maximumPower = 1 * Unit.MW;
	double maximumCPU = nominalCPU;
	boolean vectored = false;
	
	Hull hull = HullFactory.getHull("Shuttle");
	
	Spacecraft spacecraft = new SimpleSpacecraft(SpacecraftFactory.SHUTTLE, hull);
	
	Bus spacecraftBus = new SpacecraftBus("Spacecraft bus", spacecraft); 

	BusComponentSpecification busSpecs = new BusComponentSpecification(
			new PhysicalSpecification(mass, volume),
			new OperationalSpecification(nominalPower, nominalCPU, maximumPower, maximumCPU));




	@Test
	public void testRequestOperation() {

		SystemComputer computer = new BasicSystemComputer("Test computer", busSpecs, 
				10 * Unit.GFLOP);
		computer.registerWithBus(spacecraftBus);

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
				10 * Unit.GFLOP);
		computer.registerWithBus(spacecraftBus);


		assertEquals("Computer category incorrect", SystemComputer.categoryID, computer.getCategoryId());
		assertEquals("Computer type ["+ computer.describe() +"] incorrect", BasicSystemComputer.typeID, computer.getTypeId());


		assertEquals("Incorrect spacecraft bus", spacecraft, computer.getSpacecraftBus());

		//computer.checkSystems()

	}





}
