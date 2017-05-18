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
import org.spacebits.components.energygeneration.SimpleSolarArray;
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
	public final static String SIMPLE_SATELITE="Simple satelite"; 
	
	public static Spacecraft getSpacecraft(String spacecraftType) throws InvalidParameterException{
		SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();
		 
		switch (spacecraftType) {
			
		case SHUTTLE:
			Hull hull = HullFactory.getHull(SHUTTLE);
			
			Spacecraft spacecraft = new SimpleSpacecraft(SHUTTLE, hull);
			
			SystemComputer systemComputer = ComputerFactory.getComputer(BasicSystemComputer.type());	
			spacecraft.addComponent(systemComputer);
			
			//PropulsionManagementSoftware engineManagementSoftware = new PropulsionManagementSoftware("Test EngineManagementSoftware", systemComputer);
			//systemComputer.loadSoftware(engineManagementSoftware);
			
			PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.type());
			spacecraft.addComponent(powerGenerator);
			
			Sensor sensor = SensorFactory.getSensor(LinearSensorArray.type(), Sensor.RADAR, 1);
			spacecraft.addComponent(sensor);
			
			
			double tankCapacity = 100 * Unit.l.value();
			Fuel fuel = spacecraftDataProvider.getFuel(Fuel.HYDRAZINE);
			FuelStorageTank tank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.type(), tankCapacity);
			tank.setFuel(fuel, tankCapacity);
			
			FuelSubSystem fuelDeliverySystem = FuelSubSystemFactory.getFuelSubsystem(
					FuelSubSystem.BASIC_FUEL_SUBSYSTEM, FuelSubSystem.PROPULSION_FUEL_SUBSYSTEM);
			fuelDeliverySystem.addFuelTank(tank);
			spacecraft.addComponent(fuelDeliverySystem);
			spacecraft.addComponent(tank);
			
			FuelConsumingEngine engine = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.type(), false);
			engine.setFuelSubSystem(fuelDeliverySystem);
			spacecraft.addComponent((SpacecraftBusComponent)engine);
			
			CommunicationComponent commDevice = CommunicatorDeviceFactory.getCommunicator(RadioCommunicator.type());
			spacecraft.addComponent(commDevice);
			
			return spacecraft;
		case SIMPLE_SATELITE:
			Hull satHull = HullFactory.getHull("SimpleSatelite");
			
			Spacecraft sat = new SimpleSpacecraft("SimpleSatelite", satHull);
			
			SystemComputer satSystemComputer = ComputerFactory.getComputer(BasicSystemComputer.type());	
			sat.addComponent(satSystemComputer);
			
			PowerGenerator simpleSolarCell = PowerGenerationFactory.getPowerGenerator(SimpleSolarArray.type());
			sat.addComponent(simpleSolarCell);
			
			return sat;
		default:
			throw new InvalidParameterException("No such spacecraft.");
			
		}
		
		
	}

}
