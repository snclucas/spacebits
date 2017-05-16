package org.spacebits.universe.celestialobjects;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.Coordinates;

public abstract class Asteroid extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("Asteroid");
	}

	public Asteroid(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, sensorSignalResponseProfile);
	}
	
	public Asteroid(String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, relativeTo, sensorSignalResponseProfile);
	}

}
