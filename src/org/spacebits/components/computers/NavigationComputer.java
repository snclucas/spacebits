package org.spacebits.components.computers;

import java.math.BigDecimal;

import org.spacebits.universe.Coordinates;

public interface NavigationComputer extends SystemComputer  {
	
	public Coordinates getCurrentLocation();
	
	public BigDecimal getDistanceToCoordinates(Coordinates location);
	
	//CelestialObject getCelestialObjectFromDatabase(int indent);

}
