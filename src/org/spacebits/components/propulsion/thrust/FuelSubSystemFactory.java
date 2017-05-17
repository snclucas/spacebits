package org.spacebits.components.propulsion.thrust;

import java.security.InvalidParameterException;

import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.OperationalSpecification;
import org.spacebits.spacecraft.PhysicalSpecification;

public class FuelSubSystemFactory {


	public static FuelSubSystem getFuelSubsystem(String fuelSubsystem, int fuelSubsystemType) throws InvalidParameterException{

		switch (fuelSubsystem) {

		case FuelSubSystem.BASIC_FUEL_SUBSYSTEM:

			double mass = 250 * Unit.kg.value();
			double volume = 10.0 * Unit.m3.value();
			double nominalPower = 100 * Unit.W.value(); 
			double nominalCPUThroughput = 10 * Unit.kFLOP.value();

			BusComponentSpecification busSpecs = new BusComponentSpecification(
					new PhysicalSpecification(mass, volume),
					new OperationalSpecification(nominalPower, nominalCPUThroughput));


			FuelSubSystem fuelDeliverySubSystem = new FuelSubSystem(FuelSubSystem.BASIC_FUEL_SUBSYSTEM, 
					busSpecs, fuelSubsystemType);

			return fuelDeliverySubSystem;
		default:
			throw new InvalidParameterException("No such fuel subsystem.");

		}


	}
	
	
	
}
