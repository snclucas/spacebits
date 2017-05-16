package org.spacebits.universe.celestialobjects;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.Coordinates;

public class Planet extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("Planet");
	}

	public static String HABITABLE_CLASS_M = "M";

	private double radius;
	private String habitatClass;

	public Planet(String name, Coordinates coordinates, 
			SensorSignalResponseProfile sensorSignalResponseProfile, double radius, String habitatClass) {
		super(name, coordinates, sensorSignalResponseProfile);
		this.radius = radius;
		this.habitatClass = habitatClass;
	}

	public Planet(String name, Coordinates coordinates, 
			CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile, double radius, String habitatClass) {
		super(name, coordinates, relativeTo, sensorSignalResponseProfile);
		this.radius = radius;
		this.habitatClass = habitatClass;
	}

	
	@Override
	public TypeInfo getType() {
		return getType();
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

	@Override
	public String describe() {
		return "A planet is a large body orbiting one or more stars.";
	}

}
