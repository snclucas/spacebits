package org.spacebits.universe.celestialobjects;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.Coordinates;

public class Region extends AbstractCelestialObject {
	
	public static TypeInfo typeID = new TypeInfo("Region");

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
	public TypeInfo getTypeId() {
		return typeID;
	}


	public double getRegionSize() {
		return sizeOfRegion;
	}

	public void setRegionSize(double sizeOfRegion) {
		this.sizeOfRegion = sizeOfRegion;
	}

	@Override
	public String describe() {
		return "An area of space defined by a location and extent.";
	}


}
