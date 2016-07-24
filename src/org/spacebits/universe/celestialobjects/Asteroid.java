package org.spacebits.universe.celestialobjects;

import org.spacebits.universe.Coordinates;

public abstract class Asteroid extends AbstractCelestialObject {

	public Asteroid(int id, String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(id, name, coordinates, sensorSignalResponseProfile);
	}
	
	public Asteroid(int id, String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(id, name, coordinates, relativeTo, sensorSignalResponseProfile);
	}

}
