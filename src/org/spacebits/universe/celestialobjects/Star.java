package org.spacebits.universe.celestialobjects;

import java.util.List;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.Coordinates;

public class Star extends AbstractCelestialObject {
	
	public static TypeInfo typeID = new TypeInfo("Star");
	
	protected List<Asteroid> asteroids;
	protected List<Planet> planets;

	public Star(int id, String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(id, name, coordinates, sensorSignalResponseProfile);
	}
	
	public Star(int id, String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(id, name, coordinates, relativeTo, sensorSignalResponseProfile);
	}
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	
}
