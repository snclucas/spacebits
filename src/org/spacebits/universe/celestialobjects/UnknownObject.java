package org.spacebits.universe.celestialobjects;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.Coordinates;

public class UnknownObject extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("UnkownObject");
	}

	public UnknownObject(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, sensorSignalResponseProfile);
	}
	
	public UnknownObject(int id, String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, relativeTo, sensorSignalResponseProfile);
	}
	
	@Override
	public TypeInfo getTypeId() {
		return type();
	}

	@Override
	public String describe() {
		return "An unknown object.";
	}

}
