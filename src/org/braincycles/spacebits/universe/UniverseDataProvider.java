package org.braincycles.spacebits.universe;

import java.math.BigDecimal;
import java.util.List;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.universe.celestialobjects.CelestialObject;

public interface UniverseDataProvider {
	int addLocation(CelestialObject location);
	CelestialObject getLocation(int locationID);
	CelestialObject getLocation(String locationProperName);
	List<CelestialObject> getLocationsByType(TypeInfo type);
	List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance);
	List<CelestialObject> getLocationsByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance);
}
