package org.braincycles.spacebits.universe.celestialobjects;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.universe.Coordinates;

public class Planet extends AbstractCelestialObject {
	
	public static TypeInfo typeID = new TypeInfo("Planet");

	public static String HABITABLE_CLASS_M = "M";

	private double radius;
	private String habitatClass;

	public Planet(int id, String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile, double radius, String habitatClass) {
		super(id, name, coordinates, sensorSignalResponseProfile);
		this.radius = radius;
		this.habitatClass = habitatClass;
	}

	public Planet(int id, String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile, double radius, String habitatClass) {
		super(id, name, coordinates, relativeTo, sensorSignalResponseProfile);
		this.radius = radius;
		this.habitatClass = habitatClass;
	}

	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getHabitatClass() {
		return habitatClass;
	}

	public void setHabitatClass(String habitatClass) {
		this.habitatClass = habitatClass;
	}


}
