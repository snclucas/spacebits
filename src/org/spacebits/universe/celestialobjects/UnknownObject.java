package org.spacebits.universe.celestialobjects;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.Coordinates;

public class UnknownObject extends AbstractCelestialObject {
	
	public static TypeInfo typeID = new TypeInfo("UnknownObject");

	public UnknownObject(int id, String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(id, name, coordinates, sensorSignalResponseProfile);
	}
	
	public UnknownObject(int id, String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(id, name, coordinates, relativeTo, sensorSignalResponseProfile);
	}
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}

}
