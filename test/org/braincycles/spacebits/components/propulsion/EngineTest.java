package org.braincycles.spacebits.components.propulsion;

import static org.junit.Assert.assertEquals;

import org.braincycles.spacebits.algorithm.thrust.ThrustAlgorithm;
import org.braincycles.spacebits.algorithm.thrust.ThrustAlgorithmFactory;
import org.braincycles.spacebits.components.propulsion.thrust.SimpleIonEngine;
import org.braincycles.spacebits.components.propulsion.thrust.SimpleThruster;
import org.braincycles.spacebits.data.SpacecraftComponentData;
import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.OperationalSpecification;
import org.braincycles.spacebits.spacecraft.PhysicalSpecification;
import org.junit.Test;

public class EngineTest {

	//Align along axis of spacecraft
	EngineVector engineVector1 = new EngineVector(1,0,0);
	EngineVector engineVector2 = new EngineVector(1,1,1);


	@Test
	public void testEngineConstruction() {
		Engine engine = getTestEngine(true);
		engine.callVector(new EngineVector(0.3,0.1, 0.5));

		assertEquals("Engine category incorrect", Engine.categoryID, engine.getCategoryId());
		assertEquals("Engine type ["+ engine.describe() +"] incorrect", SimpleThruster.typeID, engine.getTypeId());

		//Check the engine set up
		assertEquals("Engine power not set correctly", nominalPower, engine.getNominalPower(), 0.001);
		assertEquals("Engine CPU not set correctly", nominalCPU, engine.getNominalCPUThroughput(), 0.001);
		assertEquals("Engine mass not set correctly", mass, engine.getMass(), 0.001);
		assertEquals("Engine volume not set correctly", volume, engine.getVolume(), 0.001);
		assertEquals("Engine power level not set correctly", 0.0, engine.getPowerLevel(), 0.001);
		assertEquals("Engine maximum thrust not set correctly", maximumThrust, engine.getMaximumThrust(), 0.001);
		
		double newMaximumThrust = 10 * Unit.kN;
		engine.setMaximumThrust(newMaximumThrust);
		assertEquals("Engine maximum thrust not set correctly", newMaximumThrust, engine.getMaximumThrust(), 0.001);
	}



	@Test
	public void testEngineVectoring() {
		// Engines can vector only if true is passed to their constructor.
		// Setting the engine vectoring (directly on the engine) will do nothing if the engine cannot be vectored.

		Engine engine = getTestEngine(false);

		//Try to vector engine - should fail, and original engine vector in place.
		engine.callVector(engineVector2);
		assertEquals("Engine should not be able to be vectored", engineVector1, engine.getEngineVector());

		//New vectorable engine
		engine = getTestEngine(true);
		engine.callVector(engineVector2);
		engine.execute();

		//Try to vector engine - should work.
		assertEquals("Engine should be able to be vectored", engineVector2, engine.getEngineVector());

		//Test components
		assertEquals("Engine vectored incorrectly, component 1", 1, engine.getEngineVector().getVectorComponents()[0], 0.001);
		assertEquals("Engine vectored incorrectly, component 2", 1, engine.getEngineVector().getVectorComponents()[1], 0.001);
		assertEquals("Engine vectored incorrectly, component 3", 1, engine.getEngineVector().getVectorComponents()[2], 0.001);
		//Test object
		assertEquals("Engine vectored incorrectly", engineVector2, engine.getEngineVector());
	}




	@Test
	public void testEnginePowerLevels() {
		Engine engine = EngineFactory.getEngine(SimpleIonEngine.typeID.toString(), true);
		/* Power level 0, expected zero thrust and nominal power use */
		double powerLevel = 0.0;
		
		assertEquals("Engine nominal power incorrect", nominalPower, engine.getNominalPower(), 0.001);
		assertEquals("Engine max power incorrect", maxPower, engine.getMaximumOperationalPower(), 0.001);
		assertEquals("Engine operating power incorrect", nominalPower, engine.getOperatingPower(), 0.001);
		assertEquals("Engine required power incorrect", nominalPower, engine.getRequiredPower(powerLevel), 0.001);

		
		// Call drive with power level = 0;
		engine.callDrive(powerLevel);

		assertEquals("Engine nominal power incorrect", nominalPower, engine.getNominalPower(), 0.001);
		assertEquals("Engine max power incorrect", maxPower, engine.getMaximumOperationalPower(), 0.001);
		assertEquals("Engine operating power incorrect", nominalPower, engine.getOperatingPower(), 0.001);
		assertEquals("Engine required power incorrect", nominalPower, engine.getRequiredPower(powerLevel), 0.001);

		// Call drive with power level = 50% WITHOUT calling execute;
		powerLevel = 50 * Unit.percent;

		engine.callDrive(powerLevel);

		assertEquals("Engine nominal power incorrect", nominalPower, engine.getNominalPower(), 0.001);
		assertEquals("Engine max power incorrect", maxPower, engine.getMaximumOperationalPower(), 0.001);
		assertEquals("Engine operating power incorrect", nominalPower, engine.getOperatingPower(), 0.001);
		assertEquals("Engine required power incorrect", nominalPower + (maxPower-nominalPower)*0.5, engine.getRequiredPower(powerLevel), 0.001);

		//Call execute
		
		engine.execute();

		//Required and operating power should now be equal as the engine is operating at the set power level
		assertEquals("Engine nominal power incorrect", nominalPower, engine.getNominalPower(), 0.001);
		assertEquals("Engine max power incorrect", maxPower, engine.getMaximumOperationalPower(), 0.001);
		assertEquals("Engine operating power incorrect", nominalPower + (maxPower-nominalPower)*0.5, engine.getOperatingPower(), 0.001);
		assertEquals("Engine required power incorrect", nominalPower + (maxPower-nominalPower)*0.5, engine.getRequiredPower(powerLevel), 0.001);

	}


	@Test
	public void testEnginePowerAndThrust() {
		double maximumThrust = 1 * Unit.kN; // N	

		Engine engine = getTestEngine(true);
		EngineVector engineVector = new EngineVector(0.3,0.1, 0.5);
		engine.callVector(engineVector);

		// Power level 0, expected zero thrust and nominal power use
		double powerLevel = 0.0;

		engine.callDrive(powerLevel);
		engine.execute();
		double[] velocity = new double[]{0.0, 0.0, 0.0};
		double[] thrust = engine.getThrust(velocity);

		// Simple linear model
		double expectedThrust = maximumThrust * powerLevel;
		double expectedPower = engine.getNominalPower() + powerLevel*maxPower;

		assertEquals("Engine power not set correctly", expectedPower, engine.getOperatingPower(), 0.001);
		assertEquals("Engine thrust not set correctly", expectedThrust, thrust[0], 0.001);

		powerLevel = 50.0 * Unit.percent; 
		engine.callDrive(powerLevel); //50% power
		engine.execute();
		thrust = engine.getThrust(velocity);
		double[] expectedThrustVector = new double[]{
				engineVector.getVectorComponent(EngineVector.ROLL_AXIS) * (1 * Unit.kN) * powerLevel,
				engineVector.getVectorComponent(EngineVector.PITCH_AXIS) * (1 * Unit.kN) * powerLevel,
				engineVector.getVectorComponent(EngineVector.YAW_AXIS) * (1 * Unit.kN) * powerLevel
		};

		expectedPower = engine.getNominalPower() + (engine.getMaximumOperationalPower() - engine.getNominalPower()) * powerLevel;
		
		assertEquals("Engine power not set correctly", expectedPower, engine.getOperatingPower(), 0.001);
		assertEquals("Engine thrust not set correctly ROLL_AXIS", expectedThrustVector[EngineVector.ROLL_AXIS], thrust[EngineVector.ROLL_AXIS], 0.001);
		assertEquals("Engine thrust not set correctly PITCH_AXIS", expectedThrustVector[EngineVector.PITCH_AXIS], thrust[EngineVector.PITCH_AXIS], 0.001);
		assertEquals("Engine thrust not set correctly YAW_AXIS", expectedThrustVector[EngineVector.YAW_AXIS], thrust[EngineVector.YAW_AXIS], 0.001);

	}
	
	
	
	
	
	double mass = 100 * Unit.kg;
	double volume = 1.0 * Unit.m3;
	double nominalPower = 1 * Unit.kW;
	double nominalCPU = 1 * Unit.kFLOP;
	
	double maxPower = 1000 * Unit.kW;
	double maxCPU = 1 * Unit.kFLOP;
	
	double maximumThrust = 1 * Unit.kN; // N	
	
	
	private Engine getTestEngine(boolean vectored) {
		
		SpacecraftComponentData spacecraftComponentData = new SpacecraftComponentData(new BusComponentSpecification(
				new PhysicalSpecification(mass, volume), new OperationalSpecification(nominalPower, nominalCPU, maxPower, maxCPU)));

		ThrustAlgorithm thrustAlgorithm = ThrustAlgorithmFactory.getThrustAlgorithm(
				ThrustAlgorithmFactory.SIMPLE_LINEAR);	

		//Align along axis of spacecraft
		EngineVector engineVector = new EngineVector(1,0,0);

		return new SimpleThruster(
				SimpleThruster.typeID.typeIdString, spacecraftComponentData.getBusComponentSpecification(), 
				maximumThrust, 
				thrustAlgorithm, engineVector, vectored);
	}


}
