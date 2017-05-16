package org.spacebits.universe.structures;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.celestialobjects.AbstractCelestialObject;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.SensorSignalResponseProfile;

public class SubspaceBeacon extends AbstractCelestialObject {
	
	public static TypeInfo typeInfo = new TypeInfo("SubspaceBeacon");

	public SubspaceBeacon(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, sensorSignalResponseProfile);
	}
	
	public SubspaceBeacon(String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, relativeTo, sensorSignalResponseProfile);
	}

	@Override
	public TypeInfo getTypeId() {
		return typeInfo;
	}

	@Override
	public String describe() {
		return "A artifical structure designed to emit subspace signals to be used as a beacon.";
	}
}
