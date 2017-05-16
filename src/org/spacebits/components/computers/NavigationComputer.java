package org.spacebits.components.computers;

import java.math.BigDecimal;
import java.util.List;

import org.spacebits.universe.Coordinates;
import org.spacebits.universe.celestialobjects.CelestialObject;

public interface NavigationComputer extends Computer  {

	public Coordinates getCurrentLocation();
	
	public BigDecimal getDistanceToCoordinates(Coordinates location);
	
	//CelestialObject getCelestialObjectFromDatabase(int indent);
	
	void processCelestialObjects(List<CelestialObject> objects);

}
