package org.spacebits.components.propulsion.thrust;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.profiles.ThrustProfile;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.BusRequirement;

public class SimpleIonEngine extends AbstractThrustingFuelConsumingEngine implements ThrustingEngine {
	
	public static TypeInfo typeID = new TypeInfo("SimpleIonEngine");

	public SimpleIonEngine(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, ThrustProfile thrustModel, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel, engineVector, vectored);
	}
	
	
	public SimpleIonEngine(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, engineVector, vectored);
	}
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}


	@Override
	public double setPowerLevel() {
		// TODO Auto-generated method stub
		return 0;
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


	@Override
	public ThrustProfile getThrustProfile() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setThrustProfile(ThrustProfile thrustAlgorithm) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public double[] getThrust(double[] velocity) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
