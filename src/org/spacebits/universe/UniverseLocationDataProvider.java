package org.spacebits.universe;

import java.math.BigDecimal;
import java.util.List;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.sensors.SensorProfile;
import org.spacebits.universe.celestialobjects.CelestialObject;

public interface UniverseLocationDataProvider {
	int addLocation(CelestialObject location);
	CelestialObject getLocationById(String locationID);
	CelestialObject getLocationByName(String locationProperName);
	List<CelestialObject> getLocationsByType(TypeInfo type);
	List<CelestialObject> getLocationsByCategory(TypeInfo category);
	List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance);
	List<CelestialObject> getLocationsByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance);
	double getSignalPropagationSpeed(SensorProfile sensorProfile);
	void populate();
}
