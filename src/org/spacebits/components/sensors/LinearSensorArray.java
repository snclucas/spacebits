package org.spacebits.components.sensors;

import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;

public class LinearSensorArray extends BasicSensorArray {
	
	public static TypeInfo type() {
		return new TypeInfo("LinearSensorArray");
	}

	public LinearSensorArray(String name,
			BusComponentSpecification busResourceSpecification,
			SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile,
				numberOfSensorElements);
	}
	
	public TypeInfo getType() {
		return type();
	}
	
	@Override
	public String describe() {
		return "Linear sensor array.";
	}

	
	
	
	

}
