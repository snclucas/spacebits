package org.spacebits.universe.celestialobjects;

import java.util.List;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.Coordinates;

public class Star extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("Star");
	}
	
	protected List<Asteroid> asteroids;
	protected List<Planet> planets;

	public Star(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, sensorSignalResponseProfile);
	}
	
	public Star(String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, relativeTo, sensorSignalResponseProfile);
	}
	
	@Override
	public TypeInfo getTypeId() {
		return new TypeInfo("Star");
	}

	@Override
	public String describe() {
		return "A luminous sphere of plasma large enough to be held together by its own gravity.";
	}
	
}
