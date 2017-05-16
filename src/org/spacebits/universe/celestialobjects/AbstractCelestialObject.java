package org.spacebits.universe.celestialobjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.sensors.SignalResponse;
import org.spacebits.universe.AbstractLocation;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Location;
import org.spacebits.universe.SimpleLocation;

public abstract class AbstractCelestialObject extends AbstractLocation implements CelestialObject {

	public static TypeInfo category() {
		return new TypeInfo("CelestialObject ");
	}
	
	protected Location location;
	protected List<CelestialObject> celestialObjects;
	protected SensorSignalResponseProfile sensorSignalResponseProfile;
	
	
	public AbstractCelestialObject(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates);
		celestialObjects = new ArrayList<CelestialObject>();
		this.sensorSignalResponseProfile = sensorSignalResponseProfile;
		this.location = new SimpleLocation(name, coordinates);
	}
	
	public AbstractCelestialObject(String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates);
		celestialObjects = new ArrayList<CelestialObject>();
		this.sensorSignalResponseProfile = sensorSignalResponseProfile;
		this.location = new SimpleLocation(name, coordinates.add(relativeTo.getLocation().getCoordinates()));
	}
	
	@Override
	public int getId() {
		return this.hashCode();
	}
	
	@Override
	public final TypeInfo getCategory() {
		return CelestialObject.categoryID;
	}
	
	


	@Override
	public SensorSignalResponseProfile getSensorSignalResponse() {
		return sensorSignalResponseProfile;
	}


	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String getName() {
		return this.location.getName();
	}
	
	

	@Override
	public String toString() {
		return getName();
	}

	public SensorSignalResponseProfile getSensorSignalResponseProfile() {
		return sensorSignalResponseProfile;
	}

	public void setSensorSignalResponseProfile(
			SensorSignalResponseProfile sensorSignalResponseProfile) {
		this.sensorSignalResponseProfile = sensorSignalResponseProfile;
	}

	
	@Override
	public SignalResponse getSignalResponse(TypeInfo sensorType, BigDecimal distance) {
		SignalResponse signalResponse = sensorSignalResponseProfile.getSignalResponse(sensorType, distance);
		return signalResponse;
	}
	

	public Coordinates getCoordinates() {
		return this.location.getCoordinates();
	}
	
	public BigDecimal getCoordinate(int index) {
		return this.location.getCoordinates().get(index);
	}

}
