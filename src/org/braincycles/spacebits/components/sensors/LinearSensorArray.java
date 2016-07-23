package org.braincycles.spacebits.components.sensors;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;

public class LinearSensorArray extends BasicSensorArray {
	
	public static TypeInfo typeID = new TypeInfo("LinearSensorArray");

	public LinearSensorArray(String name,
			BusComponentSpecification busResourceSpecification,
			SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile,
				numberOfSensorElements);
	}
	
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	@Override
	public String describe() {
		return "Linear sensor array.";
	}

}
