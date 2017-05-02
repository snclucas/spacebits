package org.spacebits.components.propulsion.thrust;

import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.spacecraft.BusComponentSpecification;

public abstract class AbstractThruster extends AbstractThrustingFuelConsumingEngine {

	public AbstractThruster(String name,
			BusComponentSpecification busResourceSpecification,
			double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, engineVector, vectored);
	}


	
	
	
	

}
