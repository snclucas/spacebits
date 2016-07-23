package org.braincycles.spacebits.universe.celestialobjects;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.universe.Coordinates;

public class Region extends AbstractCelestialObject {
	
	public static TypeInfo typeID = new TypeInfo("Region");

	private double sizeOfRegion;

	public Region(int id, String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile, double sizeOfRegion) {
		super(id, name, coordinates, sensorSignalResponseProfile);
		this.sizeOfRegion = sizeOfRegion;
	}

	public Region(int id, String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile, double sizeOfRegion) {
		super(id, name, coordinates, relativeTo, sensorSignalResponseProfile);
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


}
