package org.spacebits.components.propulsion.thrust;

import org.spacebits.algorithm.thrust.ThrustAlgorithm;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.spacecraft.BusComponentSpecification;

public class SimpleIonEngine extends AbstractThrustEngine implements ThrustEngine {
	
	public static TypeInfo typeID = new TypeInfo("SimpleIonEngine");

	public SimpleIonEngine(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, ThrustAlgorithm thrustModel, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel, engineVector, vectored);
	}
	
	
	public SimpleIonEngine(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, engineVector, vectored);
	}
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}

	


	
}
