package org.spacebits.universe;

import java.math.BigDecimal;
import java.util.List;

import org.spacebits.components.TypeInfo;
import org.spacebits.universe.celestialobjects.CelestialObject;

public interface UniverseDataProvider {
	int addLocation(CelestialObject location);
	CelestialObject getLocation(int locationID);
	CelestialObject getLocation(String locationProperName);
	List<CelestialObject> getLocationsByType(TypeInfo type);
	List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance);
	List<CelestialObject> getLocationsByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance);
}
