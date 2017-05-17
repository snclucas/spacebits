package org.spacebits.components.sensors;

import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;

public class LinearSensorArray extends BasicSensorArray {
	
	
	public LinearSensorArray(String name,
			BusComponentSpecification busResourceSpecification,
			SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile,
				numberOfSensorElements);
	}
	
	
	@Override
	public String describe() {
		return "Linear sensor array.";
	}

	
	
	public static TypeInfo type() {
		return new TypeInfo("LinearSensorArray");
	}
	
	@Override
	public final TypeInfo getType() {
		return type();
	}

	

}
