package org.braincycles.spacebits.universe.structures;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.universe.Coordinates;
import org.braincycles.spacebits.universe.celestialobjects.AbstractCelestialObject;
import org.braincycles.spacebits.universe.celestialobjects.CelestialObject;
import org.braincycles.spacebits.universe.celestialobjects.SensorSignalResponseProfile;

public class SubspaceBeacon extends AbstractCelestialObject {
	
	public static TypeInfo typeInfo = new TypeInfo("SubspaceBeacon");

	public SubspaceBeacon(int id, String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(id, name, coordinates, sensorSignalResponseProfile);
	}
	
	public SubspaceBeacon(int id, String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(id, name, coordinates, relativeTo, sensorSignalResponseProfile);
	}

	@Override
	public TypeInfo getTypeId() {
		return typeInfo;
	}
}
