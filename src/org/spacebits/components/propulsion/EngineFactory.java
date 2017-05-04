package org.spacebits.components.propulsion;

import org.spacebits.components.propulsion.thrust.SimpleIonEngine;
import org.spacebits.components.propulsion.thrust.SimpleThruster;
import org.spacebits.components.propulsion.thrust.ThrustingEngine;
import org.spacebits.data.DataFactory;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.physics.Unit;
import org.spacebits.profiles.FuelConsumptionProfile;
import org.spacebits.profiles.FuelConsumptionProfileFactory;
import org.spacebits.profiles.ThrustProfile;
import org.spacebits.profiles.ThrustProfileFactory;

public class EngineFactory extends DataFactory {

	public static ThrustingEngine getEngine(String engineType, boolean vectored){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(engineType);

		if(engineType.equals(SimpleIonEngine.typeID.toString())){
			double maximumThrust = 1 * Unit.N;			

			ThrustProfile thrustAlgorithm = ThrustProfileFactory.getThrustAlgorithm(
					ThrustProfileFactory.SIMPLE_LINEAR);	
			
			FuelConsumptionProfile fuelConsumptionProfile = FuelConsumptionProfileFactory.getFuelConsumptionProfile(
					FuelConsumptionProfileFactory.SIMPLE_LINEAR);

			//Align along axis of spacecraft
			EngineVector engineVector = new EngineVector(1,0,0);

			return new SimpleIonEngine(
					SimpleIonEngine.typeID.typeIdString, data.getBusComponentSpecification(), 
					maximumThrust, 
					thrustAlgorithm, fuelConsumptionProfile, engineVector, vectored);
		}
		else if(engineType.equals(SimpleThruster.typeID.toString())){
			double maximumThrust = 1 * Unit.kN; // N	

			ThrustProfile thrustAlgorithm = ThrustProfileFactory.getThrustAlgorithm(
					ThrustProfileFactory.SIMPLE_LINEAR);	
			
			FuelConsumptionProfile fuelConsumptionProfile = FuelConsumptionProfileFactory.getFuelConsumptionProfile(
					FuelConsumptionProfileFactory.SIMPLE_LINEAR);

			//Align along axis of spacecraft
			EngineVector engineVector = new EngineVector(1,0,0);

			return new SimpleThruster(
					SimpleThruster.typeID.typeIdString, data.getBusComponentSpecification(), 
					maximumThrust, 
					thrustAlgorithm, fuelConsumptionProfile, engineVector, vectored);
		}
		return null;
	}

}