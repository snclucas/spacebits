package org.braincycles.spacebits.components.sensors;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;

public class FractalSensorArray extends BasicSensorArray {
	
	public static TypeInfo typeID = new TypeInfo("FractalSensorArray");

	public FractalSensorArray(String name,
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
		return "Fractal sensor array.";
	}

}
