package org.spacebits.universe.celestialobjects;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.Coordinates;

public class Region extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("Region");
	}

	private double sizeOfRegion;

	public Region(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile, double sizeOfRegion) {
		super(name, coordinates, sensorSignalResponseProfile);
		this.sizeOfRegion = sizeOfRegion;
	}

	public Region(String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile, double sizeOfRegion) {
		super(name, coordinates, relativeTo, sensorSignalResponseProfile);
		this.sizeOfRegion = sizeOfRegion;
	}
	
	
	@Override
	public TypeInfo getType() {
		return type();
	}


	public double getRegionSize() {
		return sizeOfRegion;
	}


	@Override
	public String describe() {
		return "An area of space defined by a location and extent.";
	}


}
