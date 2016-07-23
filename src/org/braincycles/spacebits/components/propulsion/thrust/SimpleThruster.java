package org.braincycles.spacebits.components.propulsion.thrust;

import org.braincycles.spacebits.algorithm.thrust.ThrustAlgorithm;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.propulsion.EngineVector;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.BusRequirement;

public class SimpleThruster extends AbstractThrustEngine implements ThrustEngine {
	
	public static TypeInfo typeID = new TypeInfo("SimpleThruster");

	public SimpleThruster(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, ThrustAlgorithm thrustModel, EngineVector engineVector, 
			boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel, 
				engineVector, vectored);
	}
	
	
	public SimpleThruster(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, EngineVector engineVector, 
			boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, engineVector, vectored);
	}
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}


	@Override
	public BusRequirement callDrive(double powerLevel) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BusRequirement callStop() {
		// TODO Auto-generated method stub
		return null;
	}


}
