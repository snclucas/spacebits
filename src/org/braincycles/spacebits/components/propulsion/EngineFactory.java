package org.braincycles.spacebits.components.propulsion;

import org.braincycles.spacebits.algorithm.thrust.ThrustAlgorithm;
import org.braincycles.spacebits.algorithm.thrust.ThrustAlgorithmFactory;
import org.braincycles.spacebits.components.propulsion.thrust.SimpleIonEngine;
import org.braincycles.spacebits.components.propulsion.thrust.SimpleThruster;
import org.braincycles.spacebits.data.DataFactory;
import org.braincycles.spacebits.data.SpacecraftComponentData;
import org.braincycles.spacebits.physics.Unit;

public class EngineFactory extends DataFactory {

	public static Engine getEngine(String engineType, boolean vectored){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(engineType);

		if(engineType.equals(SimpleIonEngine.typeID.toString())){
			double maximumThrust = 1 * Unit.N;			

			ThrustAlgorithm thrustAlgorithm = ThrustAlgorithmFactory.getThrustAlgorithm(
					ThrustAlgorithmFactory.SIMPLE_LINEAR);	

			//Align along axis of spacecraft
			EngineVector engineVector = new EngineVector(1,0,0);

			return new SimpleIonEngine(
					SimpleIonEngine.typeID.typeIdString, data.getBusComponentSpecification(), 
					maximumThrust, 
					thrustAlgorithm, engineVector, vectored);
		}
		else if(engineType.equals(SimpleThruster.typeID.toString())){
			double maximumThrust = 1 * Unit.kN; // N	

			ThrustAlgorithm thrustAlgorithm = ThrustAlgorithmFactory.getThrustAlgorithm(
					ThrustAlgorithmFactory.SIMPLE_LINEAR);	

			//Align along axis of spacecraft
			EngineVector engineVector = new EngineVector(1,0,0);

			return new SimpleThruster(
					SimpleThruster.typeID.typeIdString, data.getBusComponentSpecification(), 
					maximumThrust, 
					thrustAlgorithm, engineVector, vectored);
		}
		return null;
	}

}
