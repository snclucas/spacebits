package org.spacebits.components.sensors;

import org.spacebits.Configuration;
import org.spacebits.components.TypeInfo;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.data.SpacecraftDataProvider;

public class SensorFactory {

	public static Sensor getSensor(String sensorType, TypeInfo sensorSensingType, int numberOfSensorElements){
		SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();
		
		if(sensorType.equalsIgnoreCase(LinearSensorArray.typeID.toString())){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(sensorType);
			
			double gainPerElement = 10.0;
			double signalDetectionThreshold = -9;	
			SensorProfile sensorProfile = new SensorProfile(sensorSensingType, signalDetectionThreshold, gainPerElement);
			
			return new LinearSensorArray(
					LinearSensorArray.typeID.toString(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);
		}
		else if(sensorType.equalsIgnoreCase(FractalSensorArray.typeID.toString())){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(sensorType);
			
			double gainPerElement = 100.0;
			double signalDetectionThreshold = -9;
			SensorProfile sensorProfile = new SensorProfile(sensorSensingType, signalDetectionThreshold, gainPerElement);

			return new FractalSensorArray(
					FractalSensorArray.typeID.toString(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);
		}
		return null;
	}

}