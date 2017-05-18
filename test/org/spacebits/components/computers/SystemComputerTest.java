package org.spacebits.components.computers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.comms.Status;
import org.spacebits.components.energygeneration.PowerGenerator;
import org.spacebits.components.energygeneration.SubspacePowerExtractor;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Bus;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.BusRequirement;
import org.spacebits.spacecraft.OperationalSpecification;
import org.spacebits.spacecraft.PhysicalSpecification;
import org.spacebits.spacecraft.SpacecraftBus;
import org.spacebits.status.SystemStatus;
import org.spacebits.status.SystemStatusMessage;


public class SystemComputerTest {

	double mass = 25.0 * Unit.kg.value();
	double volume = 1.0 * Unit.m3.value();
	double nominalPower = 1 * Unit.kW.value(); 
	double nominalCPU = 10 * Unit.kFLOP.value();
	double maximumPower = 1 * Unit.MW.value();
	double maximumCPU = nominalCPU;
	boolean vectored = false;
	
	Bus spacecraftBus = new SpacecraftBus("Spacecraft bus", null); 

	
	BusComponentSpecification busSpecs = new BusComponentSpecification(
			new PhysicalSpecification(mass, volume),
			new OperationalSpecification(nominalPower, nominalCPU, maximumPower, maximumCPU));


	@Before
	public void setUp() {
		double arrayArea = 1 * Unit.m.value() * 10 * Unit.m.value();
		double efficiency = 75 * Unit.percent.value();
		
		SystemComputer computer = new BasicSystemComputer("Test computer", busSpecs, 10 * Unit.GFLOP.value());
		spacecraftBus.addComponent(computer);
		
		BusComponentSpecification powerGeneratorBusSpecs = new BusComponentSpecification(
				new PhysicalSpecification(mass, volume),
				new OperationalSpecification(0, nominalCPU, 0, maximumCPU));
		
		PowerGenerator powerGenerator = new SubspacePowerExtractor("Test power generator", powerGeneratorBusSpecs, arrayArea, efficiency);
		spacecraftBus.addComponent((SpacecraftBusComponent)powerGenerator);
	}

	@Test
	public void testRequestOperation() {
		SystemComputer computer = spacecraftBus.getSystemComputer();
		
		// Computer is not online so current power should be 0
		assertEquals("Computer current power should be (not online)", 0.0, computer.getCurrentPower(Unit.kW), 0.00001);
		SystemStatus systemStatus = computer.online();
		assertEquals("System computer should not have critical messages", false, systemStatus.hasCriticalMessages());
		assertEquals("System computer should have OK status", true, systemStatus.isOK());
		assertEquals("Computer current power should be " + busSpecs.getNominalPower(Unit.kW) + " (online)", busSpecs.getNominalPower(Unit.kW), computer.getCurrentPower(Unit.kW), 0.00001);
		
		
		
		
		
		
		BusRequirement busRequirement = new BusRequirement(100 * Unit.W.value(), 100 * Unit.MFLOP.value());
		SystemStatusMessage message = computer.requestOperation(computer, busRequirement);
		assertEquals("Should be permitted", Status.PERMITTED, message.getStatus());

		busRequirement = new BusRequirement(1100 * Unit.W.value(), 100 * Unit.MFLOP.value());
		message = computer.requestOperation(computer, busRequirement);
		assertEquals("Should be NOT_ENOUGH_POWER", Status.NOT_ENOUGH_POWER, message.getStatus());
		
		busRequirement = new BusRequirement(110 * Unit.W.value(), 10 * Unit.GFLOP.value());
		message = computer.requestOperation(computer, busRequirement);
		assertEquals("Should be NOT_ENOUGH_GPU", Status.NOT_ENOUGH_CPU, message.getStatus());
	}



	@Test
	public void testSystemComputer() {


		SystemComputer computer = new BasicSystemComputer("Test computer", busSpecs, 
				10 * Unit.GFLOP.value());
		computer.registerWithBus(spacecraftBus);


		assertEquals("Computer category incorrect", SystemComputer.category(), computer.getCategory());
		assertEquals("Computer type ["+ computer.describe() +"] incorrect", BasicSystemComputer.type(), computer.getType());


		assertEquals("Incorrect spacecraft bus", spacecraftBus, computer.getSpacecraftBus());

		//computer.checkSystems()

	}





}
