package org.braincycles.spacebits.data;

import org.braincycles.spacebits.components.comms.RadioCommunicator;
import org.braincycles.spacebits.components.comms.SubSpaceCommunicator;
import org.braincycles.spacebits.components.computers.BasicSystemComputer;
import org.braincycles.spacebits.components.energygeneration.SimpleSolarArray;
import org.braincycles.spacebits.components.energygeneration.SubspacePowerExtractor;
import org.braincycles.spacebits.components.propulsion.thrust.SimpleIonEngine;
import org.braincycles.spacebits.components.propulsion.thrust.SimpleThruster;
import org.braincycles.spacebits.components.sensors.FractalSensorArray;
import org.braincycles.spacebits.components.sensors.LinearSensorArray;
import org.braincycles.spacebits.consumables.Fuel;
import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.OperationalSpecification;
import org.braincycles.spacebits.spacecraft.PhysicalSpecification;
import org.braincycles.spacebits.structures.storage.fuel.CryogenicLiquidStorageTank;
import org.braincycles.spacebits.structures.storage.fuel.LiquidStorageTank;

public class LocalSpacecraftDataProvider implements SpacecraftDataProvider {

	@Override
	public SpacecraftComponentData getComponentParameters(String componentType) {

		// mass volume power cpu 

		// computers 
		
		if(componentType.equals(BasicSystemComputer.typeID.toString()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg, 20 * Unit.m3), new OperationalSpecification(10 * Unit.kW, 0 * Unit.MFLOP)));


		if(componentType.equals(LiquidStorageTank.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg, 1.1 * Unit.l), new OperationalSpecification(1 * Unit.kW, 1 * Unit.kFLOP)));

		if(componentType.equals(CryogenicLiquidStorageTank.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg, 1.3 * Unit.l), new OperationalSpecification(1 * Unit.kW, 1 * Unit.kFLOP)));


		// Energy generators

		if(componentType.equals(SimpleSolarArray.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(1000 * Unit.kg, 100 * Unit.m3), new OperationalSpecification(1 * Unit.kW, 1 * Unit.kFLOP)));


		if(componentType.equals(SubspacePowerExtractor.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(1000 * Unit.kg, 100 * Unit.m3), new OperationalSpecification(1 * Unit.kW, 1 * Unit.kFLOP)));

		
		// Engines
		
		if(componentType.equals(SimpleIonEngine.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg, 1.0 * Unit.m3), new OperationalSpecification(1 * Unit.kW, 1 * Unit.kFLOP, 1000 * Unit.kW, 1 * Unit.kFLOP)));

		if(componentType.equals(SimpleThruster.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg, 1.0 * Unit.m3), new OperationalSpecification(100 * Unit.W, 1 * Unit.kFLOP, 1 * Unit.kW, 1 * Unit.kFLOP)));



		// Communication devices

		if(componentType.equals(RadioCommunicator.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg, 1.1 * Unit.l), new OperationalSpecification(1 * Unit.kW, 1 * Unit.kFLOP)));

		if(componentType.equals(SubSpaceCommunicator.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg, 1.3 * Unit.l), new OperationalSpecification(1 * Unit.kW, 1 * Unit.kFLOP)));


		//Sensors

		if(componentType.equals(LinearSensorArray.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg, 1.1 * Unit.l), new OperationalSpecification(1 * Unit.kW, 1 * Unit.kFLOP)));

		if(componentType.equals(FractalSensorArray.typeID.toString()))	
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg, 1.3 * Unit.l), new OperationalSpecification(1 * Unit.kW, 1 * Unit.kFLOP)));

	
		
		
		
		return null;
	}


	@Override
	public Fuel getFuel(int fuelType) {
		if(Fuel.LIQUID_HYDROGEN == fuelType)
			return new Fuel(70.8);
		else if(Fuel.LIQUID_OXYGEN == fuelType)
			return new Fuel(1141);
		else
			return new Fuel(1852.45);
	}




}
