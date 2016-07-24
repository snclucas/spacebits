package org.spacebits.components.sensors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.Configuration;
import org.spacebits.components.sensors.FractalSensorArray;
import org.spacebits.components.sensors.LinearSensorArray;
import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SensorFactory;
import org.spacebits.components.sensors.SensorProfile;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.data.SpacecraftDataProvider;

public class SensorArrayTest {
	
	SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();

	@Test
	public void testLinearSensorArray() {
		
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(LinearSensorArray.typeID.toString());
		
		double gainPerElement = 10.0;
		double signalDetectionThreshold = -9;	
		int numberOfSensorElements = 23;
		
		SensorProfile sensorProfile = new SensorProfile(Sensor.OPTICAL, signalDetectionThreshold, gainPerElement);
		
		Sensor linearSensor = new LinearSensorArray(
				LinearSensorArray.typeID.toString(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);
		
		assertEquals("Sensor category incorrect", Sensor.categoryID, linearSensor.getCategoryId());
		assertEquals("Sensor type " + linearSensor.describe() + " incorrect", LinearSensorArray.typeID, linearSensor.getTypeId());
		assertEquals("Sensor gain incorrect (linear array)", gainPerElement*numberOfSensorElements, linearSensor.getSensorGain(), 0.0001);
		assertEquals("Sensor detection threshol incorrect (linear array)", signalDetectionThreshold, linearSensor.getSensorThreshold(), 0.0001);
		
		
		assertEquals("Sensor mass incorrect (linear array)", data.getBusComponentSpecification().getMass()*numberOfSensorElements, linearSensor.getMass(), 0.0001);
		assertEquals("Sensor volume incorrect (linear array)", data.getBusComponentSpecification().getVolume()*numberOfSensorElements, linearSensor.getVolume(), 0.0001);
		
		
	}
	
	
	@Test
	public void testFractalSensorArray() {
		
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(FractalSensorArray.typeID.toString());
		
		double gainPerElement = 100.0;
		double signalDetectionThreshold = -9;	
		int numberOfSensorElements = 23;
		
		SensorProfile sensorProfile = new SensorProfile(Sensor.OPTICAL, signalDetectionThreshold, gainPerElement);
		
		Sensor fractalSensor = SensorFactory.getSensor(FractalSensorArray.typeID.toString(), Sensor.OPTICAL, numberOfSensorElements);
		
		assertEquals("Sensor category incorrect", Sensor.categoryID, fractalSensor.getCategoryId());
		assertEquals("Sensor type " + fractalSensor.describe() + " incorrect", FractalSensorArray.typeID, fractalSensor.getTypeId());
		assertEquals("Sensor gain incorrect (fractal array)", gainPerElement*numberOfSensorElements, fractalSensor.getSensorGain(), 0.0001);
		assertEquals("Sensor detection threshol incorrect (fractal array)", signalDetectionThreshold, fractalSensor.getSensorThreshold(), 0.0001);
		
		
		assertEquals("Sensor mass incorrect (fractal array)", data.getBusComponentSpecification().getMass()*numberOfSensorElements, fractalSensor.getMass(), 0.0001);
		assertEquals("Sensor volume incorrect (fractal array)", data.getBusComponentSpecification().getVolume()*numberOfSensorElements, fractalSensor.getVolume(), 0.0001);
		
		
	}
	
	
}
