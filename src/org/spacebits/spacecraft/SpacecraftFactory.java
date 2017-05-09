package org.spacebits.spacecraft;

import java.security.InvalidParameterException;

import org.spacebits.Configuration;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.comms.CommunicationComponent;
import org.spacebits.components.comms.CommunicatorDeviceFactory;
import org.spacebits.components.comms.RadioCommunicator;
import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.components.computers.ComputerFactory;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.energygeneration.PowerGenerationFactory;
import org.spacebits.components.energygeneration.PowerGenerator;
import org.spacebits.components.energygeneration.SubspacePowerExtractor;
import org.spacebits.components.propulsion.EngineFactory;
import org.spacebits.components.propulsion.thrust.FuelConsumingEngine;
import org.spacebits.components.propulsion.thrust.FuelSubSystem;
import org.spacebits.components.propulsion.thrust.FuelSubSystemFactory;
import org.spacebits.components.propulsion.thrust.SimpleThruster;
import org.spacebits.components.sensors.LinearSensorArray;
import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SensorFactory;
import org.spacebits.consumables.Fuel;
import org.spacebits.data.SpacecraftDataProvider;
import org.spacebits.physics.Unit;
import org.spacebits.structures.hulls.Hull;
import org.spacebits.structures.hulls.HullFactory;
import org.spacebits.structures.storage.fuel.CryogenicLiquidStorageTank;
import org.spacebits.structures.storage.fuel.FuelStorageTank;
import org.spacebits.structures.storage.fuel.FuelStorageTankFactory;

public class SpacecraftFactory {
	
	public final static String SHUTTLE="Shuttle"; 
	
	public static Spacecraft getSpacecraft(String spacecraftType) throws InvalidParameterException{
		SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();
		 
		switch (spacecraftType) {
			
		case SHUTTLE:
			Hull hull = HullFactory.getHull(SHUTTLE);
			
			Spacecraft spacecraft = new SimpleSpacecraft(SHUTTLE, hull);
			
			SystemComputer systemComputer = ComputerFactory.getComputer(BasicSystemComputer.typeID.toString());	
			spacecraft.addComponent(systemComputer);
			
			//PropulsionManagementSoftware engineManagementSoftware = new PropulsionManagementSoftware("Test EngineManagementSoftware", systemComputer);
			//systemComputer.loadSoftware(engineManagementSoftware);
			
			PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.typeID.toString());
			spacecraft.addComponent(powerGenerator);
			
			Sensor sensor = SensorFactory.getSensor(LinearSensorArray.typeID.toString(), Sensor.RADAR, 1);
			spacecraft.addComponent(sensor);
			
			
			double tankCapacity = 100 * Unit.l;
			Fuel fuel = spacecraftDataProvider.getFuel(Fuel.HYDRAZINE);
			FuelStorageTank tank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.typeID.toString(), tankCapacity);
			tank.setFuel(fuel, tankCapacity);
			
			FuelSubSystem fuelDeliverySystem = FuelSubSystemFactory.getFuelSubsystem(
					FuelSubSystem.BASIC_FUEL_SUBSYSTEM, FuelSubSystem.PROPULSION_FUEL_SUBSYSTEM);
			fuelDeliverySystem.addFuelTank(tank);
			spacecraft.addComponent(fuelDeliverySystem);
			
			FuelConsumingEngine engine = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.typeID.toString(), false);
			engine.setFuelSubSystem(fuelDeliverySystem);
			spacecraft.addComponent((SpacecraftBusComponent)engine);
			
			CommunicationComponent commDevice = CommunicatorDeviceFactory.getCommunicator(RadioCommunicator.typeID.toString());
			spacecraft.addComponent(commDevice);
			
			return spacecraft;
		default:
			throw new InvalidParameterException("No such spacecraft.");
			
		}
		
		
	}

}
