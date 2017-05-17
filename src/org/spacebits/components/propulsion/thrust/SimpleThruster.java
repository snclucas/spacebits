package org.spacebits.components.propulsion.thrust;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.profiles.FuelConsumptionProfile;
import org.spacebits.profiles.ThrustProfile;
import org.spacebits.spacecraft.BusComponentSpecification;

public class SimpleThruster extends AbstractThrustingFuelConsumingEngine {
	
	public static TypeInfo type() {
		return new TypeInfo("SimpleThruster");
	}

	public SimpleThruster(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, 
			ThrustProfile thrustModel, FuelConsumptionProfile fuelConsumptionModel, EngineVector engineVector, 
			boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel, fuelConsumptionModel,
				engineVector, vectored);
	}
	
	
	public SimpleThruster(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, EngineVector engineVector, 
			boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, engineVector, vectored);
	}
	
	
	
	@Override
	public TypeInfo getType() {
		return type();
	}


}
