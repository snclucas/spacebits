package org.spacebits.components.propulsion.thrust;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.profiles.FuelConsumptionProfile;
import org.spacebits.profiles.ThrustProfile;
import org.spacebits.spacecraft.BusComponentSpecification;


public class SimpleIonEngine extends AbstractThrustingFuelConsumingEngine implements ThrustingEngine {
	
	public static TypeInfo typeID = new TypeInfo("SimpleIonEngine");

	public SimpleIonEngine(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, ThrustProfile thrustModel, FuelConsumptionProfile fuelConsumptionModel, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel, fuelConsumptionModel, engineVector, vectored);
	}
	
	
	public SimpleIonEngine(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, engineVector, vectored);
	}
	
	

	
}
